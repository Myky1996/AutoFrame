package api.model.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUserResponse {
      public String username;
      public String dateOfBirth;
      public String gender;
      public Boolean subscribedMarketing;
      public Boolean hasSetupPreference;
      public String userId;

}
