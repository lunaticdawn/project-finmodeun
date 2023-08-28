package kr.co.finmodeun.admin.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * mysql.cms_auth_info
 */
@Getter
@Setter
@ToString
public class CmsAuthInfoDto {
    /**
     * 권한 코드
     */
    @JsonProperty("auth_code")
    private String authCode;

    /**
     * 권한 이름
     */
    @JsonProperty("auth_name")
    private String authName;

    /**
     * 사용여부
     */
    @JsonProperty("use_yn")
    private String useYn;

    /**
     * 등록자 아이디
     */
    @JsonProperty("cre_id")
    private String creId;

    /**
     * 등록일시
     */
    @JsonProperty("cre_dt")
    private LocalDateTime creDt;

    /**
     * 수정자 아이디
     */
    @JsonProperty("mod_id")
    private String modId;

    /**
     * 수정일시
     */
    @JsonProperty("mod_dt")
    private LocalDateTime modDt;

}