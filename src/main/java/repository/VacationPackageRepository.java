package repository;

import model.DestinationEntity;
import model.VacationpackageEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.apache.commons.beanutils.PropertyUtils.copyProperties;

public class VacationPackageRepository {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");


    public List<VacationpackageEntity> getAllVacationPackages() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String sql = "SELECT u FROM VacationpackageEntity u";
        List<VacationpackageEntity> vacationpackageEntities = entityManager.createQuery(sql, VacationpackageEntity.class).getResultList();

        for (VacationpackageEntity x :
                vacationpackageEntities) {
            System.out.println(x.getIdvacationpackage() + " " + x.getDestinationId() + " " + x.getName());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return vacationpackageEntities;
    }

    public void modifyVacation(VacationpackageEntity vacationpackageEntity) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        VacationpackageEntity vacationpackageEntityFromDB = entityManager.find(VacationpackageEntity.class, vacationpackageEntity.getIdvacationpackage());

        copyProperties(vacationpackageEntityFromDB, vacationpackageEntity);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void addVacation(VacationpackageEntity vacationpackageEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(vacationpackageEntity);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void deleteVacationPackage(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        VacationpackageEntity vpToDelete = entityManager.find(VacationpackageEntity.class, id);
        entityManager.remove(vpToDelete);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void deleteByDestination(int id)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        String sql = "SELECT u FROM VacationpackageEntity u";
        List<VacationpackageEntity> vacationpackageEntities = entityManager.createQuery(sql, VacationpackageEntity.class).getResultList();

        for (VacationpackageEntity x :
                vacationpackageEntities) {
            if(x.getDestinationId() == id)
            {
                VacationpackageEntity vpToDelete;
                vpToDelete = entityManager.find(VacationpackageEntity.class, x.getIdvacationpackage());
                entityManager.remove(vpToDelete);
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void refreshIds()
    {

    }

//    public static void main(String[] args)
//    {
//        getAllVacationPackages();
//    }
}
