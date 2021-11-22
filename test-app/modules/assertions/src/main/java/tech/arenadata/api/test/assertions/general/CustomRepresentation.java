package tech.arenadata.api.test.assertions.general;

import org.apache.http.HttpResponse;
import org.assertj.core.presentation.StandardRepresentation;

/**
 * Assertj custom string representation of validated objects
 */
public class CustomRepresentation extends StandardRepresentation {

	@Override
	public String fallbackToStringOf(final Object o) {
		if (o instanceof HttpResponse) return "HttpResponse";

		return super.fallbackToStringOf(o);
	}

	@Override
	protected String toStringOf(final String str) {
		return "<<<" + str + ">>>";
	}
}
