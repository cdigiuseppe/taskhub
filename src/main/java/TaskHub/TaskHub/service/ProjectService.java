package TaskHub.TaskHub.service;

import TaskHub.TaskHub.dto.ProjectDto;
import TaskHub.TaskHub.request.ProjectRequest;
import TaskHub.TaskHub.exceptions.ProjectAlreadyExistsException;
import TaskHub.TaskHub.exceptions.ProjectNotExistException;
import TaskHub.TaskHub.exceptions.UserNotExistException;
import TaskHub.TaskHub.model.Project;
import TaskHub.TaskHub.model.User;
import TaskHub.TaskHub.repository.ProjectRepository;
import TaskHub.TaskHub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {


    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Project createNewProject(ProjectRequest request) throws ProjectAlreadyExistsException {
        if (projectRepository.findByName(request.getName()).isPresent()) {
            throw new ProjectAlreadyExistsException("Project with this name already exists");
        }
        Project project = new Project();
        project.setName(request.getName());
        project.setDateAdded(new Timestamp(System.currentTimeMillis()));
        return projectRepository.save(project);
    }

    public Optional<Project> findByName(String name) {
        return projectRepository.findByName(name);
    }

    public List<ProjectDto> getAllProjects(){
        return projectRepository.findAll().stream()
                .map(project -> new ProjectDto(project.getId(), project.getName(), project.getDateAdded()))
                .toList();
    }

    public void assignUserToProject(int projectId, int userId) throws ProjectNotExistException, UserNotExistException {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalProject.isPresent() && optionalUser.isPresent()) {
            Project project = optionalProject.get();
            User user = optionalUser.get();
            project.getUsers().add(user);
            projectRepository.save(project);
        }
        if (optionalProject.isEmpty()) {
            throw new ProjectNotExistException("Project not found");
        }
        if (optionalUser.isEmpty()) {
            throw new UserNotExistException("User not found");
        }
    }
}
