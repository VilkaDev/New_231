package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;


    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User").getResultList();
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
