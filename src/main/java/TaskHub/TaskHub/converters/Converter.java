package TaskHub.TaskHub.converters;

import TaskHub.TaskHub.dto.Dto;
import TaskHub.TaskHub.model.BaseEntity;
import jakarta.persistence.Entity;

public interface Converter<D extends Dto, E extends BaseEntity> {
    D convertiDaEntity(E e) ;
    E convertiDaDto(D d);
}
