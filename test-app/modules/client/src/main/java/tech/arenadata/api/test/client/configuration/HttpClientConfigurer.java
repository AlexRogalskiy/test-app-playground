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
package tech.arenadata.api.test.client.configuration;

import static java.lang.Math.toIntExact;
import static org.apache.http.client.utils.URLEncodedUtils.parsePathSegments;

import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.SocketConfig;
import tech.arenadata.api.test.commons.factory.ConfigurationFactory;

/** Http client service. */
public class HttpClientConfigurer {

    /**
     * Returns base {@link URL} server url.
     *
     * @return base server url
     */
    @SneakyThrows
    public String createBaseServerUrl() {
        final var config = ConfigurationFactory.getInstance();
        return new URIBuilder()
                .setScheme(config.getServerScheme())
                .setHost(config.getServerHost())
                .setPort(config.getServerPort())
                .build()
                .toString();
    }

    /**
     * Returns server {@link URL} by provided configuration.
     *
     * @param pathSegments initial input {@link String} collection of url path segments to operate
     *     by
     * @return server url
     */
    @SneakyThrows
    public String createServerUrl(final String... pathSegments) {
        final var config = ConfigurationFactory.getInstance();
        final var paths =
                Stream.concat(
                                parsePathSegments(config.getServerPath()).stream(),
                                Arrays.stream(pathSegments))
                        .collect(Collectors.toList());

        return new URIBuilder()
                .setScheme(config.getServerScheme())
                .setHost(config.getServerHost())
                .setPort(config.getServerPort())
                .setPathSegments(paths)
                .build()
                .toString();
    }

    /**
     * Returns {@link SocketConfig} instance by provided configuration.
     *
     * @return socket config
     */
    public SocketConfig createSocketConfig() {
        final var config = ConfigurationFactory.getInstance();
        return SocketConfig.custom()
                .setSoTimeout(toIntExact(config.getSocketTimeout().toMillis()))
                .build();
    }

    /**
     * Returns {@link RequestConfig} instance by provided configuration.
     *
     * @return request config
     */
    public RequestConfig createRequestConfig() {
        final var config = ConfigurationFactory.getInstance();
        return RequestConfig.custom()
                .setConnectTimeout(toIntExact(config.getConnectTimeout().toMillis()))
                .setConnectionRequestTimeout(
                        toIntExact(config.getConnectionRequestTimeout().toMillis()))
                .setSocketTimeout(toIntExact(config.getSocketTimeout().toMillis()))
                .build();
    }

    /**
     * Returns {@link HttpClientContext} instance by collection of attributes.
     *
     * @param attributes initial input {@link Pair} collection of attributes to operate by
     * @return http client context
     */
    @SafeVarargs
    public final HttpClientContext createClientContext(final Pair<String, Object>... attributes) {
        final var config = ConfigurationFactory.getInstance();
        final var context = HttpClientContext.create();
        context.setTargetHost(HttpHost.create(config.getServerHost()));
        Stream.ofNullable(attributes)
                .flatMap(Arrays::stream)
                .forEach(attr -> context.setAttribute(attr.getKey(), attr.getValue()));

        return context;
    }
}
