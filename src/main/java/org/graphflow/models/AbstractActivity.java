package org.graphflow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"handler"})
public abstract class AbstractActivity {

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
