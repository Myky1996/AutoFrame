package api.applicationApi;

import api.RestResource;
import api.RestResponse;
import api.Route;
import api.model.requests.CreateUserRequest;
import api.model.responses.CreateUserResponse;
import api.model.responses.GetUserResponse;
import io.restassured.response.Response;

public class GetUserApi {

    public RestResponse getUser(String userId){
        Response response = RestResource.get((Route.getProfileEndpoint()+ "/" + userId));
        return new RestResponse(GetUserResponse.class, response);
    }

}