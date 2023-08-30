package com.project.cmn.http.exception;

import com.project.cmn.http.util.MessageUtils;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidValueException extends RuntimeException {
    private static final String DEFAULT_MSG_CODE = "invalid.value";

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
     * 필드명
     */
    private final String fieldName;

    /**
     * 생성자
     *
     * @param fieldName 유효하지 않은 값을 받은 필드명
     */
    public InvalidValueException(String fieldName) {
        super(MessageUtils.getMessage(InvalidValueException.DEFAULT_MSG_CODE, MessageUtils.getMessage(fieldName)));

        this.status = HttpStatus.BAD_REQUEST.value();
        this.resCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
        this.resMsg = StringUtils.defaultIfBlank(MessageUtils.getMessage(InvalidValueException.DEFAULT_MSG_CODE, MessageUtils.getMessage(fieldName)), HttpStatus.BAD_REQUEST.toString());
        this.fieldName = fieldName;
    }
}
