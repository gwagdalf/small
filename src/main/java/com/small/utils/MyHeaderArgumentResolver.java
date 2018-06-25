package com.small.utils;

import com.small.dto.MyHeader;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class MyHeaderArgumentResolver
        implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class clazz = methodParameter.getParameterType();
        if(clazz == MyHeader.class){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        MyHeader myHeader = new MyHeader();
        String host = nativeWebRequest.getHeader("host");
        String accept = nativeWebRequest.getHeader("accept");
        myHeader.setAccept(accept);
        myHeader.setHost(host);
        return myHeader;
    }
}
