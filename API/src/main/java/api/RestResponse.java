package api;

import api.model.responses.ClientErrorResponse;
import com.amy.utils.LogUtils;
import io.restassured.response.Response;

import java.lang.reflect.Array;

public class RestResponse<T> {
    private static final LogUtils logback = LogUtils.getInstance();
    private T data;
    private Response response;
    private ClientErrorResponse error;

    public RestResponse(Class<T> t, Response response) {
        this.response = response;
        try {
            if (t.isArray()) {
                this.data = (T) Array.newInstance(t.getComponentType(), 0);
            } else {
                this.data = t.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error instantiating the Response POJO", e);
        }
    }

//    public RestResponse(Class<T> t, Response response) {
//        this.response = response;
//        try {
//            this.data = t.newInstance();
//        } catch (Exception e) {
//            throw new RuntimeException("There should be a default constructor in the Response POJO");
//        }
//    }
    public Response getResponse() {
        return this.response;
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }


    public boolean isSuccessful() {
        int code = getStatusCode();
        return code == 200 || code == 201 || code == 202 || code == 203 || code == 204 || code == 205;
    }

    public boolean isBadRequest() {
        int code = getStatusCode();
        return code == 400;
    }

    public Object getBody() {
        try {
            if (isSuccessful()) {
                return (T) getResponse().then().extract().body().as(data.getClass());
            } else {
                return getResponse().then().extract().body().as(ClientErrorResponse.class);
            }
        } catch (Exception ex) {
            logback.fail("Failed to get body" + ex.getMessage());
            return null;
        }
    }

}
