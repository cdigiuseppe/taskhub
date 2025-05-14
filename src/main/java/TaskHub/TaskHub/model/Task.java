package TaskHub.TaskHub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "task")
@NoArgsConstructor
@Getter
@Setter
public class Task extends BaseEntity {

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Task_Users",
            joinColumns = { @JoinColumn(name = "taskId") },
            inverseJoinColumns = { @JoinColumn(name = "userId")})

    private List<User> users;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", description='" + description + '\'' +
                ", dateAdded=" + dateAdded +
                ", project=" + project +
                '}';
    }
}
