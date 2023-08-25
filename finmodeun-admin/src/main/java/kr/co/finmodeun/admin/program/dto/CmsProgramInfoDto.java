package kr.co.finmodeun.admin.program.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.cmn.http.dto.BaseDto;
import com.project.cmn.http.validate.groups.Create;
import com.project.cmn.http.validate.groups.Modify;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * mysql.cms_program_info
 */
@Getter
@Setter
@ToString
public class CmsProgramInfoDto extends BaseDto {
    /**
     * 프로그램 일련번호
     */
    @NotNull(groups = { Modify.class })
    @JsonProperty("program_seq")
    private Long programSeq;

    /**
     * 프로그램 이름
     */
    @NotBlank(groups = { Create.class })
    @JsonProperty("program_name")
    private String programName;

    /**
     * HTTP 메소드
     */
    @NotBlank(groups = { Create.class })
    @Pattern(regexp = "GET|POST", groups = { Create.class, Modify.class })
    @JsonProperty("http_method")
    private String httpMethod;

    /**
     * URI 주소
     */
    @NotBlank(groups = { Create.class })
    @JsonProperty("uri_addr")
    private String uriAddr;

    /**
     * CRUD 유형
     */
    @NotBlank(groups = { Create.class })
    @Pattern(regexp = "C|R|U|D|L", groups = { Create.class, Modify.class })
    @JsonProperty("crud_type")
    private String crudType;

    /**
     * 사용여부
     */
    @Pattern(regexp = "Y|N", groups = { Create.class, Modify.class })
    @JsonProperty("use_yn")
    private String useYn;
}