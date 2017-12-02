package org.graphflow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"handler"})
public class Role {

    @Id
    @JsonIgnore
    private String id;
    @Version
    @JsonIgnore
    private Long version;
    private String name;
    private String description;
    @ManyToMany
    private Set<Permission> permissions = new HashSet<>();


}
