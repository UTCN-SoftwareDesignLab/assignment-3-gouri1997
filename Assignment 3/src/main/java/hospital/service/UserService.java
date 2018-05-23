package hospital.service;

import hospital.dto.UserDto;
import hospital.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User findByUsername(String username);
    List<User> getDoctors();
    User create (UserDto userDto);
    User update (UserDto userDto);
    void delete(String username);
}
