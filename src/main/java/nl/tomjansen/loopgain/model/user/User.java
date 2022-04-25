package nl.tomjansen.loopgain.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.tomjansen.loopgain.model.project.Project;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Getter
//@Setter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

//    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
//    private List<FeedbackString> feedbackStrings;

    @OneToMany(mappedBy = "projectOwner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Project> projectList = new ArrayList<>();

    // Ik gebruik hier een Set ipv een List. Een list kan duplicate waardes bevatten en een set alleen unieke waardes.
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER,
            mappedBy = "username"
    )
    private Set<Authority> authorities = new HashSet<>();

    // Ik gebruik hier geen Lombok voor het maken van getters en setters omdat ik voor het veld authorities mijn eigen
    // implementatie gebruiken wil.
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Project> getProjectList() {
        return projectList;
    }
    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }


}
