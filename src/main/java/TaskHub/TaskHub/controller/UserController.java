package TaskHub.TaskHub.controller;

import TaskHub.TaskHub.model.User;
import TaskHub.TaskHub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/createNewUser")
    public ResponseEntity<?> createNewUser(@RequestBody User user){
        if(userService.findByCF(user.getCF()).isEmpty()) {
          User createNewUser = userService.createNewUser(user);
          return new ResponseEntity<User>(createNewUser, HttpStatus.CREATED);
        }else {
            HashMap<String, String> error = new HashMap<>();
            error.put("errore", "Esiste già uno user con questo nome");
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/allUsers")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }
}