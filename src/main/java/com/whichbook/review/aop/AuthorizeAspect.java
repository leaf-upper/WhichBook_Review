package com.whichbook.review.aop;

import com.whichbook.review.review.provider.UserProvider;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthorizeAspect {

    private final UserProvider userProvider;

    public AuthorizeAspect(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    @Around("@annotation(IsAuthenticated)")
    public Object checkAuthority(ProceedingJoinPoint joinPoint) throws Throwable {
        userProvider.authorizeByToken(getToken());
        return joinPoint.proceed();
    }

    private String getToken() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        if(token == null || token.isBlank()) {
            throw new TokenNotValidException("토큰이 존재하지 않습니다.");
        }
        return request.getHeader("Authorization");
    }
}
