package TaskHub.TaskHub.service;

import TaskHub.TaskHub.dto.ProjectRequest;
import TaskHub.TaskHub.exceptions.ProjectAlreadyExistsException;
import TaskHub.TaskHub.model.Project;
import TaskHub.TaskHub.model.User;
import TaskHub.TaskHub.repository.ProjectRepository;
import TaskHub.TaskHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

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

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public boolean assignUserToProject(int projectId, int userId){
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalProject.isPresent() && optionalUser.isPresent()){
            Project project = optionalProject.get();
            User user = optionalUser.get();
            project.getUsers().add(user);
            projectRepository.save(project);
            return true;
        }
        return false;
    }
}
