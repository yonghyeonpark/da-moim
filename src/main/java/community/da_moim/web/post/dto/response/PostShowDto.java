package community.da_moim.web.post.dto.response;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PostShowDto {

    private static final int MAX_DAYS_TO_EDIT = 10;

    private final String title;
    private final String content;
    private final Long userId;
    private final String nickname;
    private final int daysRemainingForEdit;

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
        this.daysRemainingForEdit = MAX_DAYS_TO_EDIT - (int) ChronoUnit.DAYS.between(LocalDateTime.now(), createdAt);
    }
}
