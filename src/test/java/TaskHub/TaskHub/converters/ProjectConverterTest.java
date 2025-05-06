package TaskHub.TaskHub.converters;

import static org.junit.jupiter.api.Assertions.*;

import TaskHub.TaskHub.dto.ProjectDto;
import TaskHub.TaskHub.model.Project;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

class ProjectConverterTest {

    @Test
    void convertiDaEntity_shouldMapAllFieldsCorrectly() {
        Project project = new Project();
        project.setId(1);
        project.setName("Test Project");
        project.setDateAdded(new Timestamp(System.currentTimeMillis()));

        ProjectConverter converter = new ProjectConverter();
        ProjectDto projectDto = converter.convertiDaEntity(project);

        assertEquals(project.getId(), projectDto.getId());
        assertEquals(project.getName(), projectDto.getName());
        assertEquals(project.getDateAdded(), projectDto.getDateAdded());
    }

    @Test
    void convertiDaEntity_shouldHandleNullEntity() {
        ProjectConverter converter = new ProjectConverter();
        ProjectDto projectDto = converter.convertiDaEntity(null);

        assertNull(projectDto);
    }

    @Test
    void convertiDaDto_shouldMapAllFieldsCorrectly() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(1);
        projectDto.setName("Test Project");
        projectDto.setDateAdded(new Timestamp(System.currentTimeMillis()));

        ProjectConverter converter = new ProjectConverter();
        Project project = converter.convertiDaDto(projectDto);

        assertEquals(projectDto.getId(), project.getId());
        assertEquals(projectDto.getName(), project.getName());
        assertEquals(projectDto.getDateAdded(), project.getDateAdded());
    }

    @Test
    void convertiDaDto_shouldHandleNullDto() {
        ProjectConverter converter = new ProjectConverter();
        Project project = converter.convertiDaDto(null);

        assertNull(project);
    }
}