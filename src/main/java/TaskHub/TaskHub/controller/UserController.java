package TaskHub.TaskHub.controller;

import TaskHub.TaskHub.converters.UserConverter;
import TaskHub.TaskHub.dto.UserDto;
import TaskHub.TaskHub.request.UserRequest;
import TaskHub.TaskHub.model.User;
import TaskHub.TaskHub.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @PostMapping()
    public ResponseEntity<UserDto> createNewUser(@RequestBody @Valid UserRequest request){
        User user = userService.createNewUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserConverter().convertiDaEntity(user));
    }

    @GetMapping()
    public List<UserDto> getUsers(){
        return userService.getAllUsers();
    }
}
