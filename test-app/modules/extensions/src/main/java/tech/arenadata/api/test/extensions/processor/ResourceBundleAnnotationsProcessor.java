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
package tech.arenadata.api.test.extensions.processor;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.errorprone.annotations.FormatMethod;
import com.google.errorprone.annotations.FormatString;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.StandardLocation;
import org.apache.commons.lang3.StringUtils;
import tech.arenadata.api.test.extensions.annotation.GenerateResourceBundle;

@SupportedAnnotationTypes("tech.arenadata.api.test.extensions.annotation.GenerateResourceBundle")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
@SupportedOptions(ResourceBundleAnnotationsProcessor.SKIP_PROCESSOR)
public class ResourceBundleAnnotationsProcessor extends AbstractProcessor {

    public static final String SKIP_PROCESSOR = "resource.bundle.annotations.processor.skip";

    public static final String BUNDLES_FILE = "resource-bundles.json";

    private final List<String> bundleClasses;
    private boolean skipProcessor;
    private boolean error;

    public ResourceBundleAnnotationsProcessor() {
        this.bundleClasses = new ArrayList<>();
    }

    private static String toJson(final List<String> elements) {
        final var sb = new StringBuilder();
        sb.append("[");

        for (final var e : elements) {
            sb.append(StringUtils.LF).append("  \"").append(e).append('"');
        }
        sb.append("\n]\n");

        return sb.toString();
    }

    @Override
    public synchronized void init(final ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.skipProcessor = processingEnv.getOptions().containsKey(SKIP_PROCESSOR);
    }

    @Override
    public boolean process(
            final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        if (this.skipProcessor) {
            return false;
        }

        for (final var e : roundEnv.getElementsAnnotatedWith(GenerateResourceBundle.class)) {
            if (e.getKind().isClass()) {
                this.bundleClasses.add(((TypeElement) e).getQualifiedName().toString());
            } else {
                this.printError(
                        "'{%s}' is not a class, cannot be @GenerateResourceBundle annotated", e);
                this.error = true;
            }
        }

        // Generate files only if this is the last round and there is no error
        if (roundEnv.processingOver() && !this.error) {
            this.generateFile(this.bundleClasses);
        }

        return false;
    }

    @FormatMethod
    private void printError(@FormatString final String template, final Object... args) {
        this.processingEnv
                .getMessager()
                .printMessage(Diagnostic.Kind.ERROR, format(template, args));
    }

    private void generateFile(final List<String> elements) {
        try {
            final var resource =
                    this.processingEnv
                            .getFiler()
                            .createResource(StandardLocation.CLASS_OUTPUT, "", BUNDLES_FILE);
            try (final var writer = new OutputStreamWriter(resource.openOutputStream(), UTF_8)) {
                writer.write(toJson(elements));
            }
        } catch (IOException e) {
            this.printError(
                    "Could not create/write '{%s}' file: {%s}",
                    e.toString(), ResourceBundleAnnotationsProcessor.BUNDLES_FILE);
        }
    }
}
