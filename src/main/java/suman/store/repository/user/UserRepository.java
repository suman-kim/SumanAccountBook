package suman.store.repository.user;

import suman.store.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public List<User> findAll(){      //jpql                 //반환타입
        return em.createQuery("select m from User m",User.class)
                .getResultList();
    }

    public User findOne(Long id){
        return em.find(User.class, id);
    }

    public void save(User userDto){
        em.persist(userDto);
    }

    public void delete(Object user){
        em.remove(user);
    }

    public void update(Object user){
        em.merge(user);
    }

    public List<User> loginCheck(JSONObject userInfo){

        return em.createQuery("select u from User u where u.userId = :userId and u.password = :password",User.class)
                .setParameter("userId",userInfo.get("userId"))
                .setParameter("password",userInfo.get("password"))
                .getResultList();
    }
}
