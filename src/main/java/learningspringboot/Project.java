package learningspringboot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tm1c14 on 18/05/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    private int id;
    private String name;
    private String path;

    @JsonProperty(value = "owner_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ownerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
