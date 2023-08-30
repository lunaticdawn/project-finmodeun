package kr.co.finmodeun.admin.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.cmn.http.dto.BaseDto;
import com.project.cmn.http.validate.groups.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * mysql.cms_admin_info
 */
@Getter
@Setter
@ToString
public class CmsAdminInfoDto extends BaseDto {
    /**
     * 어드민 아이디
     */
    @NotBlank(groups = {Retrieve.class, Create.class, Modify.class, Login.class, ChangePwd.class})
    @JsonProperty("admin_id")
    private String adminId;

    /**
     * 어드민 비밀번호
     */
    @NotBlank(groups = {Create.class, Login.class, ChangePwd.class})
    @JsonProperty("admin_pwd")
    private String adminPwd;

    /**
     * 어드민 이름
     */
    @NotBlank(groups = {Create.class})
    @JsonProperty("admin_name")
    private String adminName;

    /**
     * 어드민 유형
     */
    @NotBlank(groups = {Create.class})
    @JsonProperty("admin_type")
    private String adminType;

    /**
     * 휴대폰 번호
     */
    @NotBlank(groups = {Create.class})
    @JsonProperty("hp_no")
    private String hpNo;

    /**
     * 이메일 주소
     */
    @Email(groups = {Create.class, Modify.class})
    @JsonProperty("email_addr")
    private String emailAddr;

    /**
     * 비밀번호 변경일시
     */
    @JsonProperty("pwd_change_dt")
    private LocalDateTime pwdChangeDt;

    /**
     * 로그인 일시
     */
    @JsonProperty("login_dt")
    private LocalDateTime loginDt;

    /**
     * 로그인 실패 횟수
     */
    @JsonProperty("login_failuer_cnt")
    private Integer loginFailuerCnt;

    /**
     * 로그인 실패 일시
     */
    @JsonProperty("login_failuer_dt")
    private LocalDateTime loginFailuerDt;

    /**
     * 사용 여부
     */
    @Pattern(regexp = "Y|N", groups = {Create.class, Modify.class})
    @JsonProperty("use_yn")
    private String useYn;

    /**
     * 기존 어드민 비밀번호
     */
    @NotBlank(groups = {ChangePwd.class})
    @JsonProperty("old_admin_pwd")
    private String oldAdminPwd;

    /**
     * 어드민 비밀번호 확인
     */
    @NotBlank(groups = {Create.class, ChangePwd.class})
    @JsonProperty("admin_pwd_confirm")
    private String adminPwdConfirm;
}
