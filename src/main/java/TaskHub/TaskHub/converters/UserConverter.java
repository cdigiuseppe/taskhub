package TaskHub.TaskHub.converters;

import TaskHub.TaskHub.dto.UserDto;
import TaskHub.TaskHub.model.User;

public class UserConverter implements Converter<UserDto, User>{
    @Override
    public UserDto convertiDaEntity(User user) {
        if (user == null) {
            return null;
        }

        return new UserDto(user.getId(),
        user.getName(),
        user.getSurname(),
        user.getCF(),
        user.getDateAdded());
    }

    @Override
    public User convertiDaDto(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setCF(userDto.getCF());
        user.setDateAdded(userDto.getDateAdded());
        return user;
    }
}
