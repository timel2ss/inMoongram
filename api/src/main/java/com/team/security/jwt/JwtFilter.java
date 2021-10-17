package com.team.security.jwt;

import com.team.util.CookieUtil;
import com.team.util.RedisUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;
    private final RedisUtil redisUtil;
    private final CookieUtil cookieUtil;

    public JwtFilter(TokenProvider tokenProvider, RedisUtil redisUtil, CookieUtil cookieUtil) {
        this.tokenProvider = tokenProvider;
        this.redisUtil = redisUtil;
        this.cookieUtil = cookieUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        String accessToken = getTokenFromCookie(request, "accessToken");
        String refreshToken = null;

        try {
            if (StringUtils.hasText(accessToken) && tokenProvider.validateToken(accessToken)) {
                Authentication authentication = getAuthenticationFromToken(accessToken);
                log.info("Security Context에 '{}' 인증 정보를 저장했습니다. URI: {}", authentication.getName(), requestURI);
            } else {
                log.info("유효한 액세스 토큰이 없습니다. URI: {}", requestURI);
            }
        } catch (ExpiredJwtException e) {
            refreshToken = getTokenFromCookie(request, "refreshToken");
        }

        try {
            if (refreshToken != null) {
                log.info("액세스 토큰을 재발급합니다. URI: {}", requestURI);
                String data = redisUtil.getData(refreshToken);
                if (data.equals(tokenProvider.getUsername(refreshToken))) {
                    Authentication authentication = getAuthenticationFromToken(refreshToken);
                    Cookie newAccessTokenCookie = createNewAccessTokenCookie(authentication);
                    response.addCookie(newAccessTokenCookie);
                }
            }
        } catch (ExpiredJwtException e) {
            log.info("유효한 리프레시 토큰이 없습니다. URI: {}", requestURI);
        }

        filterChain.doFilter(request, response);
    }

    private Cookie createNewAccessTokenCookie(Authentication authentication) {
        String newAccessToken = tokenProvider.createAccessToken(authentication);
        long accessTokenExpireTimeInSeconds = TokenProvider.ACCESS_TOKEN_VALID_TIME / 1000;
        return cookieUtil.createCookie("accessToken", newAccessToken, accessTokenExpireTimeInSeconds);
    }

    private Authentication getAuthenticationFromToken(String token) {
        Authentication authentication = tokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    private String getTokenFromCookie(HttpServletRequest request, String cookieName) {
        Cookie tokenCookie = cookieUtil.getCookie(request, cookieName);
        if (tokenCookie != null) {
            return tokenCookie.getValue();
        }
        return null;
    }
}
