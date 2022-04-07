package suman.store.repository.memo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import suman.store.domain.memo.Memo;
import suman.store.domain.user.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemoRepository {

    private final EntityManager em;

    public List<Memo> findMemos(User user){

         return em.createQuery("select m from Memo m where m.user =:user",Memo.class)
                 .setParameter("user",user)
                 .getResultList();

    }

    public void save(Memo memo){
        em.persist(memo);
    }

    public Memo findOne(User user, Long memoId){
        try {
            return em.createQuery("select m from Memo m where m.user =:user and m.memoId =:memoId", Memo.class)
                    .setParameter("user", user)
                    .setParameter("memoId", memoId)
                    .getSingleResult();
        }catch (NoResultException e ){
            return null;
        }
    }
    public Memo findId(Long memoId){
        try {
            return em.createQuery("select m from Memo m where m.memoId =:memoId", Memo.class)
                    .setParameter("memoId", memoId)
                    .getSingleResult();
        }catch (NoResultException e ){
            return null;
        }
    }

    public void delete(Memo memo){
        em.remove(memo);
    }

}
