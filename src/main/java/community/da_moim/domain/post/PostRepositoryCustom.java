package community.da_moim.domain.post;

import community.da_moim.web.post.dto.response.PostShowDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepositoryCustom {

    List<Post> findAllUpdatablePosts(LocalDateTime tenDaysAgo);

    List<PostShowDto> findAllByOrderByCreatedAtDesc();

    List<PostShowDto> findAllByTitleKeywordByOrderByCreatedAtDesc(String titleKeyword);
}
