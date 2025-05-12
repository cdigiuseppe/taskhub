package TaskHub.TaskHub.service;

import TaskHub.TaskHub.dto.UserDto;
import TaskHub.TaskHub.request.UserRequest;
import TaskHub.TaskHub.exceptions.UserAlreadyExistsException;
import TaskHub.TaskHub.model.User;
import TaskHub.TaskHub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createNewUser(UserRequest request) throws UserAlreadyExistsException {
        if (userRepository.findByCF(request.getCF()).isPresent()) {
            throw new UserAlreadyExistsException("User with this name already exists");
        }
        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setCF(request.getCF());
        user.setDateAdded(new Timestamp(System.currentTimeMillis()));

        return userRepository.save(user);
    }

    public Optional<User> findByCF(String cf) {return userRepository.findByCF(cf);}

    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream().map(user -> new UserDto(user.getId(),user.getName(),user.getSurname(),user.getCF(),user.getDateAdded()))
                .toList();
    }
}
