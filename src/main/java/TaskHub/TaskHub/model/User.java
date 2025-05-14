package TaskHub.TaskHub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "cf", nullable = false)
    private String CF;
    @Column(name = "dateAdded", nullable = false)
    private Timestamp dateAdded;

    @ManyToMany
    @JoinTable(
            name = "User_Project",
            joinColumns = { @JoinColumn(name = "userId") },
            inverseJoinColumns = { @JoinColumn(name = "projectId")})
    private List<Project> projects;

    @ManyToMany(mappedBy = "users")
    @JsonBackReference
    private List<Task> tasks;

}
