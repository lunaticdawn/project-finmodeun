package com.project.cmn.http.util;

import com.project.cmn.http.WebCmnConstants;
import com.project.cmn.util.JsonUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {
    private ResponseUtils() {}

    public static void sendResponse(HttpServletResponse response, HttpStatus httpStatus, int resCode) throws IOException {
        Map<String, Object> result = new HashMap<>();

        result.put(WebCmnConstants.ResponseKeys.TIMESTAMP.code(), LocalDateTime.now().toString());
        result.put(WebCmnConstants.ResponseKeys.RES_STATUS.code(), httpStatus.value());
        result.put(WebCmnConstants.ResponseKeys.RES_CODE.code(), String.valueOf(resCode));
        result.put(WebCmnConstants.ResponseKeys.RES_MSG.code(), MessageUtils.getMessage(resCode));

        response.setStatus(httpStatus.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().print(JsonUtils.toJsonStr(result));
    }
}
