package community.da_moim.service.auth;

import community.da_moim.jwt.JwtUtil;
import community.da_moim.service.auth.dto.OAuth2UserImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2UserImpl oAuth2User = (OAuth2UserImpl) authentication.getPrincipal();
        response.addCookie(createCookie(jwtUtil.createJwt(oAuth2User.getName(), 30 * 60 * 1000L)));
        response.sendRedirect("http://localhost:8080/");
    }

    private Cookie createCookie(String value) {
        Cookie cookie = new Cookie("Authorization", value);
        cookie.setMaxAge(30 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }
}
