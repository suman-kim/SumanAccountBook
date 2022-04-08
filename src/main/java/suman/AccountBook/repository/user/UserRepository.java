package suman.AccountBook.repository.user;

import suman.AccountBook.domain.user.User;
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
        return em.createQuery("select u from User u",User.class)
                .getResultList();
    }

    public User findOne(Long id){
        return em.find(User.class, id);
    }

    public List<User> findById(String userId){
        return em.createQuery("select u from User u where u.userId = :userId" ,User.class)
                .setParameter("userId",userId)
                .getResultList();
    }


    public void save(User userDto){
        em.persist(userDto);
    }

    public void delete(User user){
        em.remove(user);
    }

    public void update(User user){
        em.merge(user);
    }

    public List<User> loginCheck(JSONObject userInfo){

        return em.createQuery("select u from User u where u.userId = :userId and u.password = :password",User.class)
                .setParameter("userId",userInfo.get("userId"))
                .setParameter("password",userInfo.get("password"))
                .getResultList();
    }
}
