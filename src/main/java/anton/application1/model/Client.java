package anton.application1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Client implements Serializable {
    @JsonProperty(value = "status")
    private String status;
    @JsonProperty(value = "FIO")
    private String FIO;
}
