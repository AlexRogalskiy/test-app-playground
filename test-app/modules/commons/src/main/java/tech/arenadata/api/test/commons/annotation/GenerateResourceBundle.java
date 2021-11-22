package tech.arenadata.api.test.commons.annotation;

import java.lang.annotation.*;

/**
* Annotation to flag the generation of resource bundles for stages and related classes that have
* labels or messages that need to be localized.
*/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GenerateResourceBundle {}
