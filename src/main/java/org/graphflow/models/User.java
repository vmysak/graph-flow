package org.graphflow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.graphflow.annotations.VertexEntity;
import org.springframework.data.annotation.CreatedDate;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"handler"})
@VertexEntity
public class User {

    @JsonIgnore
    private String id;
    @JsonIgnore
    private Long version;
    @JsonIgnore
    @CreatedDate
    private Long created;
    private String email;
    private String name;
    private Boolean active;
    private String password;
    private Set<Role> roles = new HashSet<>();

}
