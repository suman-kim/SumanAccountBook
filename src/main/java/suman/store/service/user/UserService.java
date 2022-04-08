package suman.store.service.user;


import suman.store.domain.user.User;
import suman.store.domain.user.UserDto;
import suman.store.repository.user.UserRepository;
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
    @Transactional(readOnly = true)
    public List<User> findById(String userId){
        return userRepository.findById(userId);
    }

    @Transactional
    public void save(UserDto userDto){

        User user = User.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .userRole(userDto.getUserRole())
                .build();

        userRepository.save(user);
    }

    @Transactional
    public void delete(User user){

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
        user.UpdateUser(userDto.getUserId(),userDto.getName(),userDto.getPhone(),userDto.getEmail());

    }

    //회원가입시 중복이름 유효성검사
    public Boolean validateDuplicateUser(UserDto userDto) {
        List<User> findMembers = userRepository.findById(userDto.getUserId());
        if(!findMembers.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

}
