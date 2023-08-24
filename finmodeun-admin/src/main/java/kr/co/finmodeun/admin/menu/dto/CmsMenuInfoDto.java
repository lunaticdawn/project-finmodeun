package kr.co.finmodeun.admin.menu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.cmn.http.validate.groups.Create;
import com.project.cmn.http.validate.groups.Modify;
import com.project.cmn.util.tree.TreeDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * mysql.cms_menu_info
 */
@Getter
@Setter
@ToString
public class CmsMenuInfoDto extends TreeDto {
    /**
     * 메뉴 아이디
     */
    @NotBlank(groups = { Create.class, Modify.class })
    @JsonProperty("menu_id")
    private String menuId;

    /**
     * 메뉴 이름
     */
    @NotBlank(groups = { Create.class })
    @JsonProperty("menu_name")
    private String menuName;

    /**
     * 상위 메뉴 아이디
     */
    @JsonProperty("parent_menu_id")
    private String parentMenuId;

    /**
     * URI 주소
     */
    @JsonProperty("uri_addr")
    private String uriAddr;

    /**
     * 메뉴레벨
     */
    @JsonProperty("menu_level")
    private Integer menuLevel;

    /**
     * 정렬순서
     */
    @JsonProperty("sort_order")
    private Integer sortOrder;

    /**
     * 사용 여부
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