package community.da_moim.web.post.dto.response;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PostShowDto {

    private String title;
    private String content;
    private Long userId;
    private String nickname;
    private int daysRemainingForEdit;

    public PostShowDto(
            String title,
            String content,
            Long userId,
            String nickname,
            LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.nickname = nickname;
        this.daysRemainingForEdit = (int) ChronoUnit.DAYS.between(LocalDateTime.now(), createdAt);
    }
}
