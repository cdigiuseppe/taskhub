package TaskHub.TaskHub.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import TaskHub.TaskHub.request.ProjectRequest;
import TaskHub.TaskHub.model.Project;
import TaskHub.TaskHub.repository.ProjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void testCreateNewProject() throws Exception {
        ProjectRequest request = new ProjectRequest();
        request.setName("Test Creazione Progetto");

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Creazione Progetto"));

        assertTrue(projectRepository.findAll().stream().anyMatch(x -> x.getName().equalsIgnoreCase("Test Creazione Progetto")) );
    }

    @Test
    void testGetProjects() throws Exception {
        Project project = new Project();
        project.setName("Progetto H2");
        project.setDateAdded(new Timestamp(System.currentTimeMillis()));
        projectRepository.save(project);

        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Progetto H2"));
    }
}