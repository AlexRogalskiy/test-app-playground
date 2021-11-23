package tech.arenadata.api.test.assertions.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageTemplate {
	/**
	 * Collection of page items
	 */
	@JsonUnwrapped
	private List<ItemTemplate> items;
}
