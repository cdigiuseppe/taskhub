package TaskHub.TaskHub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "project")
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "dateAdded", nullable = false)
    private Timestamp dateAdded;

    @ManyToMany(mappedBy = "projects")
    @JsonBackReference
    private List<User> users;

}
