package com.larry.fc.finalproject.api.config;

import com.larry.fc.finalproject.api.dto.AuthUser;
import com.larry.fc.finalproject.core.exception.CalendarException;
import com.larry.fc.finalproject.core.exception.ErrorCode;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static com.larry.fc.finalproject.api.service.LoginService.LOGIN_SESSION_KEY;

/**
 * @author Larry
 */
public class AuthUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return AuthUser.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final Long userId = (Long) webRequest.getAttribute(LOGIN_SESSION_KEY,
                WebRequest.SCOPE_SESSION);
        if (userId != null) {
            return AuthUser.of(userId);
        } else {
            throw new CalendarException(ErrorCode.BAD_REQUEST);
        }
    }
}
