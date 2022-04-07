package suman.store.controller.user;

import suman.store.domain.user.User;
import suman.store.domain.user.UserDto;
import suman.store.jsondata.JsonData;
import suman.store.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/user/list")
    public Object list(){

        List<User> users = userService.findUsers();
        JsonData error = new JsonData();
        if(users.isEmpty()){

            error.setError_msg("유저를 추가하십시오.");
            error.setError_code(10);
            return error;
        }
        else {
            JSONObject json = new JSONObject();
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
            return error;
        }
    }

    @PostMapping("/user")
    public Object create(@RequestBody UserDto userDto){

        userService.save(userDto);

        JSONObject json = new JSONObject();
        JsonData error = new JsonData();

        error.setError_msg("성공적으로 추가되었습니다.");
        error.setError_code(1);
        json.put("code",error);
        return json;
    }

    @DeleteMapping("/user/{userId}")
    public Object delete(@PathVariable("userId") Long userId){

        Object userCheck = userService.findone(userId);
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
            return error;
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
            return error;
        }
    }
}

