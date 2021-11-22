package tech.arenadata.api.test.assertions.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Page template model
 */
@Getter
@Setter
@ToString
public class PageTemplate {
	/**
	 * Collection of page items
	 */
	private List<ItemTemplate> items;
}
