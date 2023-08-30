package kr.co.finmodeun.admin.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.cmn.http.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * mysql.cms_auth_admin_rel
 */
@Getter
@Setter
@ToString
public class CmsAuthAdminRelDto extends BaseDto {
    /**
     * 권한 코드
     */
    @JsonProperty("auth_code")
    private String authCode;

    /**
     * 어드민 아이디
     */
    @JsonProperty("admin_id")
    private String adminId;

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
     * 어드민 이름
     */
    @JsonProperty("admin_name")
    private String adminName;

    /**
     * 어드민 아이디들
     */
    @JsonProperty("admin_ids")
    private List<String> adminIds;
}
