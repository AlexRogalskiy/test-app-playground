package tech.arenadata.api.test.assertions.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Item template model
 */
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemTemplate {
	/**
	 * Item id
	 */
	private String id;
	/**
	 * Item label
	 */
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
	private String label;
	/**
	 * Item target link
	 */
	private String link;
	/**
	 * Item parent id
	 */
	@JsonAlias("depends")
	private String parentId;
}
