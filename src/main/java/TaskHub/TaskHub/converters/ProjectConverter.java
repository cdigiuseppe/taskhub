package TaskHub.TaskHub.converters;

import TaskHub.TaskHub.dto.ProjectDto;
import TaskHub.TaskHub.model.Project;

public class ProjectConverter implements Converter<ProjectDto, Project> {
    @Override
    public ProjectDto convertiDaEntity(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDateAdded(project.getDateAdded());
        return projectDto;
    }

    @Override
    public Project convertiDaDto(ProjectDto projectDto) {
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setDateAdded(projectDto.getDateAdded());
        return project;
    }

}
