package community.da_moim.service.post;

import community.da_moim.domain.post.Post;
import community.da_moim.domain.post.PostRepository;
import community.da_moim.domain.user.User;
import community.da_moim.domain.user.UserRepository;
import community.da_moim.util.mapper.PostMapper;
import community.da_moim.web.post.dto.request.PostSaveDto;
import community.da_moim.web.post.dto.response.PostShowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long post(PostSaveDto postSaveDto, String loginId) {
        User user = userRepository.findByLoginId(loginId);
        return postRepository
                .save(PostMapper.toEntity(postSaveDto, user))
                .getId();
    }

    public PostShowDto getPostDetail(Long postId) {
        Post post = postRepository.findById(postId).get();
        return PostMapper.toPostDetailDto(post);
    }
}
