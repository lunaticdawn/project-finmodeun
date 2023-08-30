package com.project.cmn.http.exception;

import com.project.cmn.http.util.MessageUtils;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class WebClientException extends RuntimeException {
    /**
     * Response Status
     */
    private final int status;

    /**
     * 결과 코드
     */
    private final String resCode;

    /**
     * 결과 메시지
     */
    private final String resMsg;

    /**
     * 생성자
     *
     * @param httpStatusCode {@link HttpStatusCode}
     */
    public WebClientException(HttpStatusCode httpStatusCode) {
        super(StringUtils.defaultIfBlank(MessageUtils.getMessage(httpStatusCode.value()), HttpStatus.valueOf(httpStatusCode.value()).toString()));

        this.status = httpStatusCode.value();
        this.resCode = String.valueOf(httpStatusCode.value());
        this.resMsg = StringUtils.defaultIfBlank(MessageUtils.getMessage(httpStatusCode.value()), HttpStatus.valueOf(httpStatusCode.value()).toString());
    }
}
