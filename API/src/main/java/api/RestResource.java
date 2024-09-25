package api;

import com.amy.Environment;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testcase.APIBaseTest;

public class RestResource {
    private static Environment environment = APIBaseTest.getEnvironment();

    private static RequestSpecification requestSpec(){
        return RestAssured.given().when().contentType(ContentType.JSON).baseUri(environment.getBaseURL());
    }
    public static Response get(String endpoint) {
       return requestSpec().get(endpoint);
    }
    public static Response get(String endpoint, String queryParam) {
       return requestSpec().queryParam(queryParam).get(endpoint);
    }
    public static Response post(String endpoint) {
       return requestSpec().post(endpoint);
    }
    public static Response post(String endpoint, Object payload) {
        return requestSpec().body(payload).post(endpoint);
    }
}
