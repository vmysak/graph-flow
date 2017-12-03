package org.graphflow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.graphflow.annotations.VertexEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"handler"})
@VertexEntity
public class Permission {

    @JsonIgnore
    private String id;
    @JsonIgnore
    private Long version;
    private String name;
    private String description;

}
