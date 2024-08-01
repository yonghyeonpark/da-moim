package community.da_moim.service.post;

import community.da_moim.domain.post.Post;
import community.da_moim.domain.post.PostRepository;
import community.da_moim.domain.user.User;
import community.da_moim.domain.user.UserRepository;
import community.da_moim.util.mapper.PostMapper;
import community.da_moim.web.post.dto.request.PostSaveDto;
import community.da_moim.web.post.dto.request.PostUpdateDto;
import community.da_moim.web.post.dto.response.PostAlarmDto;
import community.da_moim.web.post.dto.response.PostShowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long post(
            PostSaveDto postSaveDto,
            String loginId
    ) {
        User user = userRepository.findByLoginId(loginId);
        return postRepository
                .save(PostMapper.toEntity(postSaveDto, user))
                .getId();
    }

    public PostShowDto getPostDetail(Long postId) {
        Post post = postRepository.findById(postId).get();
        return PostMapper.toPostDetailDto(post);
    }

    // 삭제 여부도 판단
    @Transactional
    public PostAlarmDto update(
            PostUpdateDto postUpdateDto,
            Long postId
    ) {
        Post post = postRepository.findById(postId).get();
        if (post.isUpdatable()) {
            post.update(
                    postUpdateDto.getTitle(),
                    postUpdateDto.getContent()
            );
            if (ChronoUnit.DAYS.between(LocalDateTime.now(), post.getCreatedAt()) == 9) {
                return new PostAlarmDto(PostAlarmDto.UPDATE_DISABLE_WARNING_MESSAGE);
            }
            return new PostAlarmDto(PostAlarmDto.UPDATE_AVAILABLE_MESSAGE);
        }
        throw new RuntimeException();
    }

    @Transactional
    public void updatePostUpdatability() {
        postRepository.findAllUpdatablePosts(LocalDateTime.now().minusDays(10))
                .forEach(Post::disableUpdate);
    }
}
