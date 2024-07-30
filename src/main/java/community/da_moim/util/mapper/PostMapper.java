package community.da_moim.util.mapper;

import community.da_moim.domain.post.Post;
import community.da_moim.domain.user.User;
import community.da_moim.web.post.dto.request.PostSaveDto;

public class PostMapper {

    public static Post toEntity(PostSaveDto postSaveDto, User user) {
        return new Post(
                postSaveDto.getTitle(),
                postSaveDto.getContent(),
                user
        );
    }
}
