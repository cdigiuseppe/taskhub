package TaskHub.TaskHub.converters;

import TaskHub.TaskHub.dto.ProjectDto;
import TaskHub.TaskHub.model.Project;

public class ProjectConverter implements Converter<ProjectDto, Project> {
    @Override
    public ProjectDto convertiDaEntity(Project project) {
        if (project == null) {
            return null;
        }

        return new ProjectDto(project.getId(),
        project.getName(),
        project.getDateAdded());
    }

    @Override
    public Project convertiDaDto(ProjectDto projectDto) {
        if (projectDto == null) {
            return null;
        }
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setDateAdded(projectDto.getDateAdded());
        return project;
    }

}
