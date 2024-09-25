package testcase;

import api.RestResponse;
import api.applicationApi.CreateUserApi;
import api.model.requests.CreateUserRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserTest extends APIBaseTest {
    @Test(groups = "smoke")
    public void CreateUserTest_Success() {
        logback.info("Build request body for api.");

        CreateUserRequest createUserReqBody = CreateUserRequest.builder()
                .username("An")
                .dateOfBirth("22/06/1997")
                .gender("FEMALE")
                .subscribedMarketing(true).build();

        logback.info("Create user by api.");
        RestResponse response = new CreateUserApi().createUser(createUserReqBody);

        logback.info("Verify status code is 200");
        Assert.assertTrue(response.isSuccessful());

    }

}
