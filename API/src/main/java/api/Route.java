package api;

public class Route {
    private static final String BASE_PATH = "/v1";
    private static final String PROFILE = "/profile";
  public static String getProfileEndpoint(){
      return BASE_PATH + PROFILE;
  }
}
