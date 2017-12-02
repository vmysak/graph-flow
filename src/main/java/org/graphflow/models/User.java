package org.graphflow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"handler"})
public class User {

    @Id
    @JsonIgnore
    private String id;
    @Version
    @JsonIgnore
    private Long version;
    @JsonIgnore
    @CreatedDate
    private Long created;
    private String email;
    private String name;
    private Boolean active;
    private String password;
    @OneToMany
    private Set<Role> roles = new HashSet<>();

}
