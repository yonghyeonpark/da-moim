package community.da_moim.jwt;

import community.da_moim.domain.user.User;
import community.da_moim.service.auth.dto.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        // Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.debug("token is null or invalid");
            filterChain.doFilter(request, response); // 다음 필터로 전달
            return;
        }
        String token = authorization.split(" ")[1];
        if (jwtUtil.isExpired(token)) {
            log.debug("token has expired");
            filterChain.doFilter(request, response);
            return;
        }

        UserDetailsImpl userDetails = new UserDetailsImpl(
                new User(
                        jwtUtil.getLoginId(token),
                        null,
                        null,
                        null,
                        null
                )
        );

        // Spring Security 인증 토큰 생성 및 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null));
        filterChain.doFilter(request, response);
    }
}
