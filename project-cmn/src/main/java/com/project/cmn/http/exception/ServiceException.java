package com.project.cmn.http.exception;

import com.project.cmn.http.util.MessageUtils;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {
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
     * @param resMsg 결과 메시지
     */
    public ServiceException(String resMsg) {
        super(resMsg);

        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.resCode = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.resMsg = resMsg;
    }

    /**
     * 생성자
     *
     * @param httpStatus {@link HttpStatus}
     */
    public ServiceException(HttpStatus httpStatus) {
        super(StringUtils.defaultIfBlank(MessageUtils.getMessage(httpStatus.value()), httpStatus.toString()));

        this.status = httpStatus.value();
        this.resCode = String.valueOf(httpStatus.value());
        this.resMsg = StringUtils.defaultIfBlank(MessageUtils.getMessage(httpStatus.value()), httpStatus.toString());
    }

    /**
     * 생성자
     *
     * @param httpStatus {@link HttpStatus}
     * @param resCode 결과 코드
     * @param args 메시지 아규먼트
     */
    public ServiceException(HttpStatus httpStatus, int resCode, Object... args) {
        super(StringUtils.defaultIfBlank(MessageUtils.getMessage(resCode, args), httpStatus.toString()));

        this.status = httpStatus.value();
        this.resCode = String.valueOf(httpStatus.value());
        this.resMsg = StringUtils.defaultIfBlank(MessageUtils.getMessage(resCode, args), httpStatus.toString());
    }
}
