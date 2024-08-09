package community.da_moim.jwt;

import community.da_moim.service.auth.dto.OAuth2UserDto;
import community.da_moim.service.auth.dto.OAuth2UserImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilterOAuth2 extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Authorization")) {
                    authorization = cookie.getValue();
                    break;
                }
            }
        }

        if (authorization == null) {
            log.debug("token is null");
            filterChain.doFilter(request, response);
            return;
        }
        if (jwtUtil.isExpired(authorization)) {
            log.debug("token has expired");
            filterChain.doFilter(request, response);
            return;
        }

        String loginId = jwtUtil.getLoginId(authorization);
        OAuth2UserImpl oAuth2User = new OAuth2UserImpl(new OAuth2UserDto(loginId));
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        oAuth2User,
                        null,
                        null
                )
        );
        filterChain.doFilter(request, response);
    }
}
