package TaskHub.TaskHub.service;


import TaskHub.TaskHub.model.User;
import TaskHub.TaskHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createNewUser(User user) {
            return userRepository.save(user);
    }
    public Optional<User> findByCF(String cf) {
        return userRepository.findByCF(cf);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
