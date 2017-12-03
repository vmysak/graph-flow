package org.graphflow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@JsonIgnoreProperties(
        ignoreUnknown = true,
        value = {"handler"})
public abstract class AbstractEntity implements Serializable {

    public static final String TYPE = "ENTITY_TYPE";

    @JsonIgnore
    private String id;
    @JsonIgnore
    private Long version;
    @JsonIgnore
    @CreatedDate
    private Long created;

    private String name;

    private Map<String, String> metadata = new HashMap<>();

}
