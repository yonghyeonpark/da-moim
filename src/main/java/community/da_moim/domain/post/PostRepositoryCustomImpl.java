package community.da_moim.domain.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static community.da_moim.domain.post.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> findAllUpdatablePosts(LocalDateTime tenDaysAgo) {
        return jpaQueryFactory
                .selectFrom(post)
                .where(
                        post.isUpdatable.eq(true),
                        post.createdAt.loe(tenDaysAgo)
                )
                .fetch();
    }
}
