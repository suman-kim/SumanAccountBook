package suman.store.service;


import suman.store.domain.User;
import suman.store.domain.UserDto;
import suman.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findone(Long userId){
        return userRepository.findOne(userId);
    }

    @Transactional
    public void save(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setUserRole(userDto.getUserRole());

        userRepository.save(user);
    }

    @Transactional
    public void delete(Object user){

        userRepository.delete(user);
    }

    @Transactional
    public List<User> loginCheck(JSONObject loginInfo){
        List<User> user = userRepository.loginCheck(loginInfo);
        System.out.println(user);

        return user;
    }

    @Transactional
    public void update(Long userId,UserDto userDto){
        User user = userRepository.findOne(userId);
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
    }

}
