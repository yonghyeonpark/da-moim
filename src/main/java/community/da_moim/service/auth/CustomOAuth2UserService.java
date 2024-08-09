package community.da_moim.service.auth;

import community.da_moim.domain.user.User;
import community.da_moim.domain.user.UserRepository;
import community.da_moim.service.auth.dto.KakaoResponse;
import community.da_moim.service.auth.dto.OAuth2Response;
import community.da_moim.service.auth.dto.OAuth2UserDto;
import community.da_moim.service.auth.dto.OAuth2UserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2Response oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());

        String oAuth2UserName = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        String nickname = oAuth2Response.getNickname();

        User user = userRepository.findByLoginId(oAuth2UserName);

        if (user == null) {
            userRepository.save(
                    new User(
                            oAuth2UserName,
                            null,
                            nickname,
                            null,
                            null
                    )
            );
            OAuth2UserDto oAuth2UserDto = new OAuth2UserDto(oAuth2UserName);
            return new OAuth2UserImpl(oAuth2UserDto);
        }

        user.update(
                oAuth2UserName,
                null,
                nickname,
                null,
                null
        );
        OAuth2UserDto oAuth2UserDto = new OAuth2UserDto(oAuth2UserName);
        return new OAuth2UserImpl(oAuth2UserDto);
    }
}
