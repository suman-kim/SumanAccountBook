package suman.AccountBook.controller.memo;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;
import suman.AccountBook.domain.memo.Memo;
import suman.AccountBook.domain.memo.MemoDto;
import suman.AccountBook.domain.user.User;
import suman.AccountBook.jsondata.JsonData;
import suman.AccountBook.service.memo.MemoService;
import suman.AccountBook.service.user.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class MemoController {

    private final UserService userService;
    private final MemoService memoService;

    @GetMapping("/memo/list/{userId}")
    public Object list(@PathVariable Long userId){
        JsonData error = new JsonData();
        JSONObject json = new JSONObject();
        User user = userService.findone(userId);

        if(user == null){
            error.setError_code(10);
            error.setError_msg("없는 유저입니다.");
            json.put("code",error);
            return json;
        }


        List<Memo> memo = memoService.findMemos(user);
        error.setError_code(1);
        error.setError_msg("가계부가 정상적으로 조회되었습니다.");
        json.put("code",error);
        json.put("data",memo);
        return json;
    }

    @GetMapping("/memo")
    public Object one(@RequestParam(value = "memoId") Long memoId, @RequestParam(value = "userId") Long userId){
        JsonData error = new JsonData();
        JSONObject json = new JSONObject();
        User user = userService.findone(userId);

            Memo memo = memoService.findOne(user, memoId);
            if(memo == null){
                error.setError_msg("해당하는 메모가 없습니다.");
                error.setError_code(10);

            }else {
                error.setError_msg("정상적으로 조회되었습니다.");
                error.setError_code(1);
            }
            json.put("code",error);
            json.put("data",memo);
            return json;
    }



    @PostMapping("/memo")
    public Object create(@RequestBody MemoDto memoParam){
        JsonData error = new JsonData();
        JSONObject json = new JSONObject();

        User user = userService.findone(memoParam.getUserId());

        if(user == null){

            error.setError_msg("없는 유저입니다.");
            error.setError_code(10);
            return error;
        }

        memoService.save(user,memoParam);
        error.setError_msg("메모가 추가되었습니다.");
        error.setError_code(1);
        json.put("code",error);
        return json;
    }

    @PutMapping("/memo/{memoId}")
    public Object update(@PathVariable("memoId") Long memoId,@RequestBody MemoDto memoDto){
        JsonData error = new JsonData();
        JSONObject json = new JSONObject();
        memoService.update(memoId,memoDto);
        error.setError_code(1);
        error.setError_msg("메모가 정상적으로 수정되었습니다.");
        json.put("code",error);
        return json;
    }

    @DeleteMapping("/memo/{memoId}")
    public Object delete(@PathVariable("memoId") Long memoId){
        JsonData error = new JsonData();
        JSONObject json = new JSONObject();
        Memo memo = memoService.findId(memoId);
        memoService.delete(memo);

        error.setError_msg("메모가 정상적으로 삭제되었습니다.");
        error.setError_code(1);
        json.put("code",error);
        return json;
    }


}
