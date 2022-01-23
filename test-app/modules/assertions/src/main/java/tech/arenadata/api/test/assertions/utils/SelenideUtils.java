/**
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2021-present Alexander Rogalskiy
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package tech.arenadata.api.test.assertions.utils;

import static java.lang.String.format;
import static tech.arenadata.api.test.commons.utils.ServiceUtils.streamOf;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import java.util.Collection;
import java.util.Objects;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SelenideUtils {

    /**
     * Обертка над Selenide waitUntil для произвольного числа элементов
     *
     * @param selenideCondition Selenide.Condition
     * @param timeout максимальное время ожидания в миллисекундах для перехода элементов в заданное
     *     состояние
     * @param selenideElements произвольное количество selenide-элементов
     * @see SelenideElement#shouldBe(Condition, Duration)
     */
    public static void waitElementsUntil(
            final Condition selenideCondition,
            final int timeout,
            final SelenideElement... selenideElements) {
        streamOf(selenideElements)
                .forEach(e -> e.shouldBe(selenideCondition, Duration.ofMillis(timeout)));
    }

    /**
     * Перегрузка метода для работы с ElementsCollection и использования стандартных методов
     * обработки списков
     *
     * @param selenideCondition Selenide.Condition
     * @param timeout максимальное время ожидания в миллисекундах для перехода элементов в заданное
     *     состояние
     * @param selenideElements ElementsCollection
     */
    public static void waitElementsUntil(
            final Condition selenideCondition,
            final int timeout,
            final ElementsCollection selenideElements) {
        selenideElements.shouldBe(
                Objects.requireNonNull(conditionToConditionCollection(selenideCondition)),
                Duration.ofMillis(timeout));
    }

    /**
     * Обертка над Selenide waitUntil для работы с коллекцией элементов
     *
     * @param selenideCondition Selenide.Condition
     * @param timeout максимальное время ожидания в миллисекундах для перехода элементов в заданное
     *     состояние
     * @param selenideElements коллекция selenide-элементов
     * @see SelenideElement#shouldNotBe(Condition, Duration)
     */
    public static void waitElementsUntil(
            final Condition selenideCondition,
            final int timeout,
            final Collection<SelenideElement> selenideElements) {
        selenideElements.forEach(e -> e.shouldBe(selenideCondition, Duration.ofMillis(timeout)));
    }

    private static CollectionCondition conditionToConditionCollection(
            final Condition selenideCondition) {
        if (selenideCondition.equals(Condition.visible)) {
            return CollectionCondition.sizeGreaterThan(0);
        }

        throw new IllegalArgumentException(
                format("Invalid selenide condition: {%s}", selenideCondition));
    }
}
