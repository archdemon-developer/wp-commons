package com.wildpulse.commons.configurations.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.wildpulse.commons.constants.WPCommonHeaders;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthenticationAspect {

    private final FirebaseAuth firebaseAuth;
    public static final String AUTHENTICATED_ANNOTATION_PROCESSOR_KEY =
            "@annotation(com.wildpulse.commons.configurations.auth.Authenticated)";

    @Before(AUTHENTICATED_ANNOTATION_PROCESSOR_KEY)
    public void authenticate() throws Exception {
        String token = getTokenFromAuthHeader();

        try {
            firebaseAuth.verifyIdToken(token);
        } catch (Exception ex) {
            throw new SecurityException("Token verification failed", ex);
        }
    }

    private static String getTokenFromAuthHeader() {
        HttpServletRequest httpServletRequest =
                ((ServletRequestAttributes)
                                Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                        .getRequest();
        String authorizationHeader =
                httpServletRequest.getHeader(WPCommonHeaders.WP_HEADER_KEY_AUTHORIZATION);

        if (authorizationHeader == null
                || !authorizationHeader.startsWith(WPCommonHeaders.WP_TOKEN_TYPE_BEARER)) {
            throw new SecurityException("Authorization header missing or malformed");
        }

        return authorizationHeader.substring(7);
    }
}
