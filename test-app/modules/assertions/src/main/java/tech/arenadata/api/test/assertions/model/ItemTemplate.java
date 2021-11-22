package tech.arenadata.api.test.assertions.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Item template model
 */
@Getter
@Setter
@ToString
public class ItemTemplate {
	/**
	 * Item id
	 */
	private String id;
	/**
	 * Item label
	 */
	private String label;
	/**
	 * Item target link
	 */
	private String link;
	/**
	 * Item parent id
	 */
	private String parentId;
}
