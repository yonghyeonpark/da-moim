package community.da_moim.web.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostSaveDto {

    @NotBlank
    @Size(max = 200)
    private final String title;

    @NotBlank
    @Size(max = 1000)
    private final String content;
}
