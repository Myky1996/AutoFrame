package testcase;

import api.RestResponse;
import api.applicationApi.CreateUserApi;
import api.applicationApi.GetUserApi;
import api.model.responses.GetUserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserTest extends APIBaseTest {
    @Test
    public void GetUserTest_Success() {
        String expectedUserId = "1";

        logback.info("Get user by api.");
        RestResponse response = new GetUserApi().getUser(expectedUserId);

        logback.info("Verify status code is 200");
        Assert.assertTrue(response.isSuccessful());

        logback.info("Verify userID in response is correct");
        GetUserResponse userResponse = (GetUserResponse) response.getBody();
        String actualUserId = userResponse.getUserId();
        Assert.assertEquals(actualUserId, expectedUserId, String.format("Expected user is %s but found %s", expectedUserId, actualUserId));
    }

}
