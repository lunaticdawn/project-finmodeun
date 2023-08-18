package com.project.cmn.http.result;

import com.project.cmn.http.WebCmnConstants;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ResultAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (String.class.isAssignableFrom(returnType.getParameterType())) {
            return false;
        }

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Map<String, Object> result = new HashMap<>();

        result.put(WebCmnConstants.ResponseKeys.TIMESTAMP.code(), LocalDateTime.now());
        result.put(WebCmnConstants.ResponseKeys.RES_STATUS.code(), HttpStatus.OK.value());
        result.put(WebCmnConstants.ResponseKeys.RES_CODE.code(), String.valueOf(HttpStatus.OK.value()));
        result.put(WebCmnConstants.ResponseKeys.RES_MSG.code(), HttpStatus.OK.toString());
        result.put(WebCmnConstants.ResponseKeys.DATA.code(), body);

        return result;
    }
}