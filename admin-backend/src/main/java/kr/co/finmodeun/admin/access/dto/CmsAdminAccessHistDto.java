package kr.co.finmodeun.admin.access.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.cmn.http.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * mysql.cms_admin_access_hist
 */
@Getter
@Setter
@ToString
public class CmsAdminAccessHistDto extends BaseDto {
    /**
     * 일련번호
     */
    @JsonProperty("access_hist_seq")
    private Long accessHistSeq;

    /**
     * HTTP 메소드
     */
    @JsonProperty("http_method")
    private String httpMethod;

    /**
     * URI 주소
     */
    @JsonProperty("uri_addr")
    private String uriAddr;

    /**
     * 쿼리 문자열
     */
    @JsonProperty("query_string")
    private String queryString;

    /**
     * 요청 Body
     */
    @JsonProperty("request_body")
    private String requestBody;

    /**
     * OS 종류
     */
    @JsonProperty("os_type")
    private String osType;

    /**
     * 브라우저 종류
     */
    @JsonProperty("browser_type")
    private String browserType;

    /**
     * 디바이스 종류
     */
    @JsonProperty("device_type")
    private String deviceType;

    /**
     * 클라이언트 주소
     */
    @JsonProperty("client_addr")
    private String clientAddr;

    /**
     * 결과 상태 코드
     */
    @JsonProperty("res_status")
    private int resStatus;

    /**
     * 결과 코드
     */
    @JsonProperty("res_code")
    private String resCode;

    /**
     * 결과 갯수
     */
    @JsonProperty("res_cnt")
    private int resCnt = 0;

    /**
     * 처리 시간(밀리초)
     */
    @JsonProperty("duration_time")
    private long durationTime;

    /**
     * 로그 키
     */
    @JsonProperty("log_key")
    private String logKey;

    /**
     * 메뉴 아이디
     */
    @JsonProperty("menu_id")
    private String menuId;
}
