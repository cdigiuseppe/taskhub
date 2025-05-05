package TaskHub.TaskHub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "task")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "dateAdded", nullable = false)
    private Timestamp dateAdded;

    @ManyToOne
    @JoinColumn(name = "projectId")
    //annotazione per evitare cicli infiniti nella serilizzazione va messa lato "figlio" è il lato che non verrà serializzato
    @JsonBackReference
    private Project project;

    @ManyToMany
    @JoinTable(
            name = "Task_Users",
            joinColumns = { @JoinColumn(name = "taskId") },
            inverseJoinColumns = { @JoinColumn(name = "userId")})
    private List<User> users;

}
