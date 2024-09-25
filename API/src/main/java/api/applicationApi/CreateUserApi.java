package api.applicationApi;

import api.RestResource;
import api.RestResponse;
import api.Route;
import api.model.requests.CreateUserRequest;
import api.model.responses.CreateUserResponse;
import io.restassured.response.Response;

public class CreateUserApi {
    public RestResponse createUser(CreateUserRequest requestBody){
        Response response = RestResource.post(Route.getProfileEndpoint(),requestBody);
        return new RestResponse(CreateUserResponse.class, response);
    }


}