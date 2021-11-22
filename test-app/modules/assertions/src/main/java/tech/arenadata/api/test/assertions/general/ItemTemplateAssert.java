package tech.arenadata.api.test.assertions.general;

import org.assertj.core.api.AbstractAssert;
import tech.arenadata.api.test.assertions.model.ItemTemplate;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Item template {@link AbstractAssert} implementation provided with following validation rules:
 * - item id
 * - item label
 * - item link
 * - item parent id
 */
public class ItemTemplateAssert extends AbstractAssert<ItemTemplateAssert, ItemTemplate> {

	protected ItemTemplateAssert(final ItemTemplate template) {
		super(template, ItemTemplateAssert.class);
	}

	public static ItemTemplateAssert assertThat(final ItemTemplate template) {
		return new ItemTemplateAssert(template);
	}

	public ItemTemplateAssert hasId(final String expectedId) {
		final var id = this.actual.getId();
		if (!Objects.equals(id, expectedId)) {
			failWithMessage("expected item template id to be <%s> but was <%s>", expectedId, id);
		}
		return this;
	}

	public ItemTemplateAssert hasId(final Predicate<? super String> predicate) {
		final var id = this.actual.getId();
		if (predicate.negate().test(id)) {
			failWithMessage("expected item template id should match predicate but was <%s>", id);
		}
		return this;
	}

	public ItemTemplateAssert hasLabel(final String expectedLabel) {
		final var label = this.actual.getLabel();
		if (!Objects.equals(label, expectedLabel)) {
			failWithMessage("expected item template label to be <%s> but was <%s>", expectedLabel, label);
		}
		return this;
	}

	public ItemTemplateAssert hasLabel(final Predicate<? super String> predicate) {
		final var label = this.actual.getLabel();
		if (predicate.negate().test(label)) {
			failWithMessage("expected item template label should match predicate but was <%s>", label);
		}
		return this;
	}

	public ItemTemplateAssert hasLink(final String expectedLink) {
		final var link = this.actual.getLink();
		if (!Objects.equals(link, expectedLink)) {
			failWithMessage("expected item template link to be <%s> but was <%s>", expectedLink, link);
		}
		return this;
	}

	public ItemTemplateAssert hasLink(final Predicate<? super String> predicate) {
		final var link = this.actual.getLink();
		if (predicate.negate().test(link)) {
			failWithMessage("expected item template link should match predicate but was <%s>", link);
		}
		return this;
	}

	public ItemTemplateAssert hasParentId(final String expectedParentId) {
		final var parentId = this.actual.getParentId();
		if (!Objects.equals(parentId, expectedParentId)) {
			failWithMessage("expected item template parent id to be <%s> but was <%s>", expectedParentId, parentId);
		}
		return this;
	}

	public ItemTemplateAssert hasParentId(final Predicate<? super String> predicate) {
		final var parentId = this.actual.getParentId();
		if (predicate.negate().test(parentId)) {
			failWithMessage("expected item template parent id should match predicate but was <%s>", parentId);
		}
		return this;
	}
}
