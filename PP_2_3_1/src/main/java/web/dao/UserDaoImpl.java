package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(long id) {
        return entityManager.getReference(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        String hql = "delete from User where id = :userId";
        entityManager.createQuery(hql).setParameter("userId",id).executeUpdate();
    }

//        TypedQuery<User> query = entityManager.createQuery("select user from User user where user.id = :id", User.class);
//        query.setParameter("id", id);
//        return query.getResultList().stream().findAny().orElse(null);
}
