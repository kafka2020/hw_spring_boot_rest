package ru.netolgy.hw_spring_boot_rest.resolver;

import ru.netolgy.hw_spring_boot_rest.annotation.UserCredentials;
import ru.netolgy.hw_spring_boot_rest.model.User;
import jakarta.validation.Valid;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // Проверяем: есть ли аннотация @UserCredentials на параметре?
        return parameter.hasParameterAnnotation(UserCredentials.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        // Достаём параметры из запроса ?user=...&password=...
        String username = webRequest.getParameter("user");
        String password = webRequest.getParameter("password");

        // Создаём объект User
        User user = new User(username, password);

        // Если есть @Valid, выполняем валидацию
        if (parameter.hasParameterAnnotation(Valid.class)) {
            DataBinder binder = binderFactory.createBinder(webRequest, user, "user");
            binder.validate();

            if (binder.getBindingResult().hasErrors()) {
                throw new BindException(binder.getBindingResult());
            }
        }

        return user;
    }
}

