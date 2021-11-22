//package tech.arenadata.api.test.app.playground.common.configuration;
//
//import com.api.sensiblemetrics.garderobe.procheks.health.enumeration.HealthCheckCategory;
//import com.api.sensiblemetrics.garderobe.procheks.health.model.HealthCheckResult;
//import lombok.EqualsAndHashCode;
//import lombok.ToString;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//
//import java.io.IOException;
//
//@Slf4j
//@EqualsAndHashCode(callSuper = true)
//@ToString(callSuper = true)
//public class HttpHealthCheckIndicator extends AbstractHealthCheckIndicator {
//    private final CloseableHttpClient httpClient;
//    private final HttpUriRequest request;
//
//    public HttpHealthCheckIndicator(String name, HttpUriRequest request) {
//        super(HealthCheckCategory.HTTP, name);
//        this.request = request;
//        this.httpClient = HttpClientBuilder.create().build();
//    }
//
//    /**
//     * The indicator checks that the target is available via HTTP get request.
//     *
//     * @return Health check result
//     */
//    @Override
//    public HealthCheckResult doHealthCheck() {
//        try (CloseableHttpResponse response = httpClient.execute(request)) {
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode < HttpStatus.SC_INTERNAL_SERVER_ERROR) {
//                return HealthCheckResult.up(healthCheckInfo);
//            }
//            log.debug("Response received with status code: {}", statusCode);
//            return HealthCheckResult.down(healthCheckInfo, String.format("Status code is %s.", statusCode));
//        } catch (IOException e) {
//            log.debug("There was an exception during HTTP health checking", e);
//            return HealthCheckResult.down(healthCheckInfo, "HTTP connection is missing. Exception: " + e.getMessage());
//        }
//    }
//}
