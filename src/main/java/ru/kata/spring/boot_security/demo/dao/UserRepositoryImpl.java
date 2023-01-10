//package ru.kata.spring.boot_security.demo.dao;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import ru.kata.spring.boot_security.demo.models.User;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import javax.persistence.TypedQuery;
//import java.util.List;
//
//@Repository
//public class UserRepositoryImpl implements UserRepository {
//    @PersistenceContext(unitName = "entityManagerFactory")
//    private EntityManager entityManager;
//
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<User> getAllUsers() {
//        return entityManager.createQuery("select u from User u", User.class).getResultList();
//    }
//
//    @Override
//    @Transactional
//    public void create(User user) {
//        entityManager.persist(user);
//    }
//
//    @Override
//    @Transactional
//    public void delete(long id) {
//        Query query = entityManager.createQuery("delete from User u where u.userId =:id ");
//        query.setParameter("id", id);
//        query.executeUpdate();
//    }
//
//    @Override
//    @Transactional
//    public void update(User user) {
//        entityManager.merge(user);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public User getById(long id) {
//        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.userId =:id", User.class);
//        query.setParameter("id", id);
//        return query.getResultList().stream().findAny().orElse(null);
//    }
//}
