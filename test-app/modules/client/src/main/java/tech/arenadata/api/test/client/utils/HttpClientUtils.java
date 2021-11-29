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
package tech.arenadata.api.test.client.utils;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;

/** Http client utilities for accessing remote apis */
@UtilityClass
public class HttpClientUtils {

    /** Default supported content types */
    public static final ContentType APPLICATION_YAML =
            ContentType.create("application/x-yaml", UTF_8);

    public static final ContentType APPLICATION_HTML = ContentType.create("text/html", UTF_8);

    /** Default http headers */
    private static final Header[] NO_CACHE_HEADERS = {
        new BasicHeader(HttpHeaders.PRAGMA, "no-cache"),
        new BasicHeader(HttpHeaders.EXPIRES, "0"),
        new BasicHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
    };

    /**
     * Returns {@link HttpGet} request by input parameters
     *
     * @param endpointUrl initial input {@link String} endpoint url
     * @param headers initial input {@link Header} collection
     * @return http get request
     */
    public static HttpGet createGetRequest(final String endpointUrl, final Header... headers) {
        final var request = new HttpGet(endpointUrl);
        request.setHeaders(NO_CACHE_HEADERS);
        Stream.ofNullable(headers).forEach(request::setHeaders);

        return request;
    }

    /**
     * Returns {@link HttpPost} request by input parameters
     *
     * @param endpointUrl initial input {@link String} endpoint url
     * @param data initial input {@link String} data
     * @param headers initial input {@link Header} collection
     * @return http post request
     */
    public static HttpPost createPostDataRequest(
            final String endpointUrl, final String data, final Header... headers) {
        final var request = new HttpPost(endpointUrl);
        Stream.ofNullable(headers).forEach(request::setHeaders);

        final var entity = new StringEntity(data, UTF_8);
        request.setEntity(entity);

        return request;
    }

    /**
     * Returns {@link HttpPost} file request by input parameters
     *
     * @param endpointUrl initial input {@link String} endpoint url
     * @param filename initial input {@link String} file name
     * @param headers initial input {@link Header} collection
     * @return http post request
     */
    public static HttpPost createPostFileRequest(
            final String endpointUrl, final String filename, final Header... headers) {
        final var request = new HttpPost(endpointUrl);
        Stream.ofNullable(headers).forEach(request::setHeaders);

        final var entity = new FileEntity(new File(filename));
        request.setEntity(entity);

        return request;
    }
}
