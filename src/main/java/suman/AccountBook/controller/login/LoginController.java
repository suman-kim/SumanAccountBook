package suman.AccountBook.controller.login;


import org.springframework.web.bind.annotation.RequestMapping;

import suman.AccountBook.domain.user.User;
import suman.AccountBook.jsondata.JsonData;
import suman.AccountBook.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class LoginController {

    private final UserService userService;


    @PostMapping("/login")
    public Object login(@RequestBody JSONObject loginInfo){

        List<User> userCheck = userService.loginCheck(loginInfo);
        System.out.println(userCheck);
        JsonData error = new JsonData();
        JSONObject json = new JSONObject();

        if(!userCheck.isEmpty()){
            json.put("data",userCheck.get(0));
            error.setError_msg("정상적으로 로그인되었습니다.");
            error.setError_code(1);
            log.info("로그인 성공");
        }
        else{
            json.put("data","");
            error.setError_msg("없는 아이디거나 비밀번호가 틀립니다.");
            error.setError_code(8);
            log.info("로그인 실패");
        }

        json.put("code",error);
        return json;
    }

    @PostMapping("/logout")
    public Object logout(@RequestBody JSONObject loginInfo){

        List<User> userCheck = userService.loginCheck(loginInfo);
        System.out.println(userCheck);
        JsonData error = new JsonData();


        if(!userCheck.isEmpty()){
            error.setError_msg("정상적으로 로그아웃되었습니다.");
            error.setError_code(1);
            log.info("로그아웃 성공");
        }
        else{
            error.setError_msg("없는 아이디거나 비밀번호가 틀립니다.");
            error.setError_code(8);
            log.info("로그아웃 실패");
        }
        return error;
    }
}
