package community.da_moim.web.post.dto.response;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostAlarmDto {

    public static final String UPDATE_DISABLE_WARNING_MESSAGE = "하루 후부터는 수정이 불가능합니다.";
    public static final String UPDATE_AVAILABLE_MESSAGE = "현재는 수정이 가능합니다.";

    private final String message;
}
