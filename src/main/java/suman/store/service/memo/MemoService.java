package suman.store.service.memo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suman.store.domain.memo.Memo;
import suman.store.domain.memo.MemoDto;
import suman.store.repository.memo.MemoRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public void save(MemoDto memoParam){
        Memo memo = new Memo();
    }
}
