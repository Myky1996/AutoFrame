package api.model.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserRequest {
      @JsonProperty(required = true)
      public String username;
      @JsonProperty(required = true)
      public String dateOfBirth;
      public String gender;
      public Boolean subscribedMarketing;
    
}
