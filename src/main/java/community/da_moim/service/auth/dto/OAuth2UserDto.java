package community.da_moim.service.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OAuth2UserDto {

    private final String oAuth2UserName;
}
