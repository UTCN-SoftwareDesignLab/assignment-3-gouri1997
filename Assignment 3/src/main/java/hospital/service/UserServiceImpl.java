package hospital.service;

import hospital.dto.UserDto;
import hospital.model.User;
import hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getDoctors() {
        return userRepository.findByRole("DOCTOR");
    }

    @Override
    public User create(UserDto userDto) {

        User user = new User(userDto.username, userDto.password, userDto.role);
        return userRepository.save(user);
    }

    @Override
    public User update(UserDto userDto) {
        User user = userRepository.getOne(userDto.username);
        user.setPassword(userDto.password);
        user.setRole(userDto.role);
        return userRepository.save(user);
    }

    @Override
    public void delete(String username) {
        userRepository.delete(username);
    }
}