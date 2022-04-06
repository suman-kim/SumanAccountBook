package suman.store.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import suman.store.config.JwtConfig;
import suman.store.domain.User;
import suman.store.jsondata.JsonData;
import suman.store.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class LoginController {

    private final UserService userService;
    private final JwtConfig jwtConfig;


    @PostMapping("/login")
    public Object login(@RequestBody JSONObject loginInfo){

        List<User> userCheck = userService.loginCheck(loginInfo);
        System.out.println(userCheck);
        JsonData error = new JsonData();
        JSONObject json = new JSONObject();

        if(!userCheck.isEmpty()){
            json.put("token",jwtConfig.createToken(userCheck.get(0).getUserId(), Arrays.asList(userCheck.get(0).getUserRole().getValue())));
            error.setError_msg("정상적으로 로그인되었습니다.");
            error.setError_code(1);
        }
        else{
            error.setError_msg("없는 아이디거나 비밀번호가 틀립니다.");
            error.setError_code(8);
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
        }
        else{
            error.setError_msg("없는 아이디거나 비밀번호가 틀립니다.");
            error.setError_code(8);
        }
        return error;
    }
}
