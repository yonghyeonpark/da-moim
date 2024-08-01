package community.da_moim.domain.post;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepositoryCustom {

    List<Post> findAllUpdatablePosts(LocalDateTime tenDaysAgo);
}
