package kr.co.finmodeun.admin.code.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.cmn.http.validate.groups.Create;
import com.project.cmn.http.validate.groups.Modify;
import com.project.cmn.http.validate.groups.Retrieve;
import com.project.cmn.http.validate.groups.RetrieveList;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * mysql.cmn_code_info
 */
@Getter
@Setter
@ToString
public class CmnCodeInfoDto {
    /**
     * 공통 코드
     */
    @NotBlank(groups = {Retrieve.class, Create.class, Modify.class})
    @JsonProperty("cmn_code")
    private String cmnCode;

    /**
     * 코드 이름
     */
    @NotBlank(groups = {Create.class})
    @JsonProperty("code_name")
    private String codeName;

    /**
     * 부모 코드 아이디
     */
    @NotBlank(groups = {RetrieveList.class, Retrieve.class, Create.class, Modify.class})
    @JsonProperty("parent_code")
    private String parentCode;

    /**
     * 코드 설명
     */
    @JsonProperty("code_desc")
    private String codeDesc;

    /**
     * 정렬순서
     */
    @JsonProperty("sort_order")
    private Integer sortOrder;

    /**
     * 표현 여부
     */
    @Pattern(regexp = "Y|N", groups = {Create.class, Modify.class})
    @JsonProperty("display_yn")
    private String displayYn;

    /**
     * 등록자 아이디
     */
    @JsonProperty("reg_id")
    private String regId;

    /**
     * 등록일시
     */
    @JsonProperty("reg_dt")
    private LocalDateTime regDt;

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