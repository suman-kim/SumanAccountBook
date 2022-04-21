package suman.AccountBook.controller.user;

import suman.AccountBook.domain.user.User;
import suman.AccountBook.domain.user.UserDto;
import suman.AccountBook.jsondata.JsonData;
import suman.AccountBook.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/user/list")
    public Object list(){
        JSONObject json = new JSONObject();
        JsonData error = new JsonData();
        List<User> users = userService.findUsers();
        String asdf= "ㅁㄴㅇㄹ";
        if(users.isEmpty()){

            error.setError_msg("유저를 추가하십시오.");
            error.setError_code(10);
            json.put("code",error);
            return json;
        }
        else {

            error.setError_code(1);
            error.setError_msg("유저가 정상적으로 조회되었습니다.");

            json.put("data",users);
            json.put("code",error);
            return json;
        }
    }

    @GetMapping("/user/{userId}")
    public Object one(@PathVariable("userId") Long userId){

        User user = userService.findone(userId);

        JSONObject json = new JSONObject();
        JsonData error = new JsonData();

        if(user != null){

            error.setError_code(1);
            error.setError_msg("유저가 정상적으로 조회되었습니다.");

            json.put("data",user);
            json.put("code",error);
            return json;

        }else{
            error.setError_code(10);
            error.setError_msg("없는 유저입니다.");
            json.put("code", error);
            return json;
        }
    }

    @PostMapping("/user")
    public Object create(@RequestBody @Valid UserDto userDto){
        log.info("UserDto Body : {}", userDto);
        JSONObject json = new JSONObject();
        JsonData error = new JsonData();
        Boolean check = userService.validateDuplicateUser(userDto);
        if(check == true) {
            userService.save(userDto);
            error.setError_msg("성공적으로 추가되었습니다.");
            error.setError_code(1);
        }else{
            error.setError_msg("아이디가 중복되었습니다.");
            error.setError_code(10);
        }
        json.put("code",error);
        return json;
    }

    @DeleteMapping("/user/{userId}")
    public Object delete(@PathVariable("userId") Long userId){

        User userCheck = userService.findone(userId);
        JSONObject json = new JSONObject();
        JsonData error = new JsonData();

        if(userCheck != null) {
            userService.delete(userCheck);
            error.setError_msg("성공적으로 삭제되었습니다.");
            error.setError_code(1);
            json.put("code", error);
            return json;

        }else{
            error.setError_code(10);
            error.setError_msg("없는 유저입니다.");
            json.put("code", error);
            return json;
        }
    }

    @PutMapping("/user/{userId}")
    public Object update(@PathVariable("userId") Long userId,@RequestBody UserDto userDto){

        User userCheck = userService.findone(userId);
        JSONObject json = new JSONObject();
        JsonData error = new JsonData();

        if(userCheck != null) {
            userService.update(userId,userDto);
            error.setError_msg("성공적으로 수정되었습니다.");
            error.setError_code(1);
            json.put("code", error);
            return json;

        }else{
            error.setError_code(10);
            error.setError_msg("없는 유저입니다.");
            json.put("code", error);
            return json;
        }
    }
}

