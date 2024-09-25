package api.model.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientErrorResponse {

    public String ref;

    @JsonProperty(required = true)
    public String code;

    @JsonProperty(required = true)
    public String message;
}
