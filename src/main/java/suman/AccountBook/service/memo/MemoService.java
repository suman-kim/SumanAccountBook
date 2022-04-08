package suman.AccountBook.service.memo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suman.AccountBook.domain.memo.Memo;
import suman.AccountBook.domain.memo.MemoDto;
import suman.AccountBook.domain.user.User;
import suman.AccountBook.repository.memo.MemoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional(readOnly = true)
    public List<Memo> findMemos(User user){
        List<Memo> memo =  memoRepository.findMemos(user);
        return memo;
    }

    @Transactional
    public void save(User user , MemoDto memoParam){
        Memo memo = Memo.builder()
                .date(memoParam.getDate())
                .content(memoParam.getContent())
                .name(memoParam.getName())
                .price(memoParam.getPrice())
                .user(user)
                .memoType(memoParam.getMemoType())
                .build();
        memoRepository.save(memo);
    }

    @Transactional
    public Memo findOne(User user , Long memoId){

            return memoRepository.findOne(user, memoId);

    }

    @Transactional
    public Memo findId(Long memoId){

        return memoRepository.findId(memoId);

    }

    @Transactional
    public void update(Long memoId,MemoDto memoDto){
        Memo memo = Optional.ofNullable(memoRepository.findId(memoId)).orElseThrow(()->new NullPointerException("null"));
        memo.UpdateMemo(memoDto.getPrice(), memoDto.getName(),memoDto.getMemoType(),memoDto.getDate(),memoDto.getContent());

    }

    @Transactional
    public void delete(Memo memo){
        memoRepository.delete(memo);
    }

}
