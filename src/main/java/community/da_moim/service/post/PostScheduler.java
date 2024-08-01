package community.da_moim.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostScheduler {

    private final PostService postService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updatePostUpdatableStatus() {
        postService.updatePostUpdatability();
    }
}
