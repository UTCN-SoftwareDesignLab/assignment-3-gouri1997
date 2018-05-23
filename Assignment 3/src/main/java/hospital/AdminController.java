package hospital;

import hospital.dto.UserDto;
import hospital.model.User;
import hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<User> getUsers(){
        return userService.getAll();
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public User createUser(@RequestBody UserDto userDto){
        System.out.println("create here");
        System.out.println(userDto.username);
        return userService.create(userDto);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public User updateUser(@RequestBody UserDto userDto){
        return userService.update(userDto);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public void deleteUser(@RequestBody UserDto userDto){
        userService.delete(userDto.username);
    }
}