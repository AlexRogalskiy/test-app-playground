package tech.arenadata.api.test.app.playground.common.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Optional;
import java.util.function.Function;

/**
 * General service utilities.
 */
@Slf4j
@UtilityClass
public class ServiceUtils {

	/**
	 * Returns binary flag whether input {@link T} value is positive/negative
	 *
	 * @param value initial input {@link T} to validate
	 * @param <T>   type of configurable number
	 * @return true - if input value is positive or zero, false - otherwise
	 */
	public static <T extends Number> boolean isPositive(final T value) {
		return value.doubleValue() >= 0d;
	}

	/**
	 * Returns parsed {@link Optional} {@link String} value
	 *
	 * @return {@link Optional} {@link String} value
	 */
	public static Optional<String> getPropertyValueAsString(final String value) {
		return getPropertyValue(value, v -> v);
	}

	/**
	 * Returns parsed {@link Optional} {@link Long} value
	 *
	 * @return {@link Optional} {@link Long} value
	 */
	public static Optional<Long> getPropertyValueAsLong(final String value) {
		return getPropertyValue(value, NumberUtils::toLong);
	}

	/**
	 * Returns parsed {@link Optional} {@link Integer} value
	 *
	 * @return {@link Optional} {@link Integer} value
	 */
	public static Optional<Integer> getPropertyValueAsInt(final String value) {
		return getPropertyValue(value, NumberUtils::toInt);
	}

	/**
	 * Returns {@link Optional} of computed input {@link String} property value by {@link Function} validator
	 *
	 * @param value     initial input {@link String} property value to compute
	 * @param validator initial input {@link Function} validator
	 * @param <T>       type of computed value
	 * @return computed {@link T} property value
	 */
	private static <T> Optional<T> getPropertyValue(final String value,
													final Function<String, T> validator) {
		try {
			return Optional.ofNullable(value)
				.map(StringUtils::trimToNull)
				.filter(StringUtils::isNotEmpty)
				.map(validator);
		} catch (Exception ex) {
			log.error("Cannot process input property name: {}", value, ex);
		}

		return Optional.empty();
	}
}
