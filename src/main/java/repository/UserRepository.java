package repository;

import model.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserRepository {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");


    public static List<UserEntity> getAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        String sql = "SELECT u FROM UserEntity u";
        List<UserEntity> userEntities =  entityManager.createQuery(sql, UserEntity.class).getResultList();

        for (UserEntity x :
                userEntities) {
            System.out.println(x.getUsername() +" "+ x.getPassword() +  " " + x.getRole());
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        return userEntities;
    }
    public static void addUser(UserEntity user)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(user);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
    public static void main(String[] args)
    {
        getAllUsers();
    }
}
