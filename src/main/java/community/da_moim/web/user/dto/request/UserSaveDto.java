package community.da_moim.web.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserSaveDto {

    @NotBlank
    @Size(min = 8, max = 16)
    @Pattern(regexp = "^[a-z0-9]+$", message = "아이디는 영문 대소문자와 숫자로만 구성되어야 합니다.")
    private final String loginId;

    @NotBlank
    @Size(min = 12, max = 16)
    @Pattern(
            regexp = "^(?=.*[a-zA-Z0-9]{5,})(?=.*[!@#$%^&*(),.?\":{}|<>]{2,}).*$",
            message = "비밀번호는 영문 대소문자와 숫자 구성으로 5자 이상이어야 하며, 추가로 특수문자를 2개 이상 포함해야 합니다."
    )
    private final String password;

    @NotBlank
    @Size(max = 10)
    @Pattern(regexp = "^[a-z0-9가-힣]+$", message = "닉네임은 한글과 영문 대소문자, 숫자로만 구성되어야 합니다.")
    private final String nickname;

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "핸드폰 번호는 000-0000-0000 형식이어야 합니다.")
    private final String phoneNumber;
}
