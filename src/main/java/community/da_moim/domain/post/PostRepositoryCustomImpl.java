package community.da_moim.domain.post;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import community.da_moim.web.post.dto.response.PostShowDto;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static community.da_moim.domain.post.QPost.post;
import static community.da_moim.domain.user.QUser.user;

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

    @Override
    public List<PostShowDto> findAllByOrderByCreatedAtDesc() {
        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                PostShowDto.class,
                                post.title,
                                post.content,
                                user.id,
                                user.nickname,
                                post.createdAt
                        )
                )
                .from(post)
                .leftJoin(user)
                .fetchJoin()
                .where(post.deletedAt.isNull())
                .orderBy(post.createdAt.desc())
                .fetch();
    }
}
