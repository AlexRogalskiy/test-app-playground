//package tech.arenadata.api.test.app.playground.steps;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.restassured.http.ContentType;
//import org.junit.Assert;
//
//import static io.restassured.RestAssured.given;
//
//public class CodeReview {
//    private final String githubEmulatorHost = "http://localhost:8089";
//
//    @Given("Get pull request with labeled action")
//    public void getPullRequest() {
//        given().urlEncodingEnabled(true)
//                .header("Accept", ContentType.JSON.getAcceptHeader())
//                .get(githubEmulatorHost + "/webhook/github/pr")
//                .then().statusCode(201);
//    }
//
//    @When("Pull Request invalid action identified")
//    public void pullRequestActionIsInvalid() {
//        final String invalidActionId = "\"action\":\"labeled\"";
//        final String responseBody = given().urlEncodingEnabled(true)
//                .header("Accept", ContentType.JSON.getAcceptHeader())
//                .get(githubEmulatorHost + "/webhook/github/pr")
//                .asString();
//        Assert.assertTrue(responseBody.contains(invalidActionId));
//    }
//
//    @Then("Peer Review Result status is FAIL")
//    public void peerReviewResultStatusIsFAIL() {
//        Assert.fail();
//    }
//
//    @Then("TestCase is PASS")
//    public void testcaseIsPASS() {
//        Assert.assertTrue(true);
//    }
//}
