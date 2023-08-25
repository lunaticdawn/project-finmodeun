package com.project.cmn.util.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 트리 구조의 자료구조를 만들기 위한 DTO 객체
 */
@Getter
@Setter
@ToString
public class TreeDto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 노드의 아이디
	 */
	private String id;

	/**
	 * 부모 노드의 아이디
	 */
	private String parentId;

	/**
	 * 자식 노드들
	 */
	private List<TreeDto> children;

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
	 * 자식 노드를 추가
	 *
	 * @param treeDto {@link TreeDto}
	 */
	public void add(TreeDto treeDto) {
		if (children == null) {
			children = new ArrayList<>();
		}

		children.add(treeDto);
	}
}