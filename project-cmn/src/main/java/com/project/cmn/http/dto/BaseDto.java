package com.project.cmn.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BaseDto {
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

    /**
     * 페이지 번호
     */
    @JsonProperty("page_num")
    private Integer pageNum;

    /**
     * 페이지 당 목록 수
     */
    @JsonProperty("page_size")
    private Integer pageSize;
}