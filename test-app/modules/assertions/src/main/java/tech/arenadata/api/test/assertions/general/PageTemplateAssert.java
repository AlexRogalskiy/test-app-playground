package tech.arenadata.api.test.assertions.general;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.AbstractAssert;
import tech.arenadata.api.test.assertions.model.ItemTemplate;
import tech.arenadata.api.test.assertions.model.PageTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.apache.commons.lang3.StringUtils.join;

/**
 * Page template {@link AbstractAssert} implementation provided with following validation rules:
 * - has items
 * - has all items
 * - has no items
 * - has any items
 * - has all unique
 */
public class PageTemplateAssert extends AbstractAssert<PageTemplateAssert, PageTemplate> {

	protected PageTemplateAssert(final PageTemplate template) {
		super(template, PageTemplateAssert.class);
	}

	public static PageTemplateAssert assertThat(final PageTemplate template) {
		return new PageTemplateAssert(template);
	}

	public PageTemplateAssert hasItems(final ItemTemplate... itemTemplates) {
		final var items = this.actual.getItems();
		if (!items.containsAll(Arrays.asList(itemTemplates))) {
			failWithMessage("expected page template contains <%s> but was <%s>", join(itemTemplates), StringUtils.join(items));
		}
		return this;
	}

	public PageTemplateAssert hasAllItems(final Predicate<? super ItemTemplate> predicate) {
		final var items = this.actual.getItems();
		if (!items.stream().allMatch(predicate)) {
			failWithMessage("expected page template should match item template predicate");
		}
		return this;
	}

	public PageTemplateAssert hasNoItems(final Predicate<? super ItemTemplate> predicate) {
		final var items = this.actual.getItems();
		if (items.stream().anyMatch(predicate)) {
			failWithMessage("expected page template should match item template predicate");
		}
		return this;
	}

	public PageTemplateAssert hasAnyItems(final Predicate<? super ItemTemplate> predicate) {
		final var items = this.actual.getItems();
		if (items.stream().noneMatch(predicate)) {
			failWithMessage("expected page template should match item template predicate");
		}
		return this;
	}

	public <T> PageTemplateAssert hasAllUnique(final Function<ItemTemplate, T> mapper) {
		final var items = this.actual.getItems();
		if (!items.stream().map(mapper).allMatch(new HashSet<>()::add)) {
			failWithMessage("expected page template should contains unique item templates");
		}
		return this;
	}

	/**
	 * Returns {@link ItemTemplate} object by input index.
	 *
	 * @param index initial input index to fetch by.
	 * @return item template
	 */
	public ItemTemplate getItem(final int index) {
		return this.actual.getItems().get(index);
	}
}
