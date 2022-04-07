package suman.store.repository.memo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import suman.store.domain.memo.Memo;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemoRepository {

    private final EntityManager em;


    public void save(Memo memo){
        em.persist(memo);
    }
}
