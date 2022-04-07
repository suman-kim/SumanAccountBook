package suman.store.controller.memo;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;
import suman.store.domain.memo.Memo;
import suman.store.domain.memo.MemoDto;
import suman.store.domain.user.User;
import suman.store.jsondata.JsonData;
import suman.store.service.memo.MemoService;
import suman.store.service.user.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class MemoController {

    private final UserService userService;
    private final MemoService memoService;

    @PostMapping("/memo")
    public Object create(@RequestBody MemoDto memoParam){
        JsonData error = new JsonData();
        User user = userService.findone(memoParam.getUserId());
        System.out.println(user);

        if(user == null){

            error.setError_msg("없는 유저입니다.");
            error.setError_code(10);
            return error;
        }
        System.out.println(memoParam);
        memoService.save(memoParam);


        error.setError_msg("메모가 추가되었습니다.");
        error.setError_code(1);

        return error;
    }

}
