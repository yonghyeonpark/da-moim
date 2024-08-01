package community.da_moim.domain.post;

import community.da_moim.domain.BaseTimeEntity;
import community.da_moim.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private boolean isUpdatable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Post(
            String title,
            String content,
            User user
    ) {
        this.title = title;
        this.content = content;
        this.user = user;
        isUpdatable = true;
    }

    public void update(
            String title,
            String content
    ) {
        this.title = title;
        this.content = content;
    }

    public void disableUpdate() {
        isUpdatable = false;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}
