package repository;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import model.DestinationEntity;
import model.VacationpackageEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class DestinationRepository {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");


    public static List<DestinationEntity> getAllDestinations() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String sql = "SELECT u FROM DestinationEntity u";
        List<DestinationEntity> destinationEntities =  entityManager.createQuery(sql, DestinationEntity.class).getResultList();

        for (DestinationEntity x :
                destinationEntities) {
            System.out.println(x.getIddestination() + " " + x.getName());
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        return destinationEntities;
    }
    public static ObservableList<String> getAllDestinationNames() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        String sql = "SELECT u FROM DestinationEntity u";
        List<DestinationEntity> destinationEntities =  entityManager.createQuery(sql, DestinationEntity.class).getResultList();
        destinationEntities.sort(Comparator.comparing(DestinationEntity::getIddestination));
        ObservableList<String> observableList = FXCollections.observableArrayList();

        for (DestinationEntity x :
                destinationEntities) {
            observableList.add(x.getName());
        }


        entityManager.getTransaction().commit();
        entityManager.close();

        return observableList;
    }
    public static int getIdByDestination(String destination)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        String sql = "SELECT u FROM DestinationEntity u";
        List<DestinationEntity> destinationEntities =  entityManager.createQuery(sql, DestinationEntity.class).getResultList();

        int id = 0;
        for (DestinationEntity x :
                destinationEntities) {
            if(x.getName().equals(destination)) {
                id = x.getIddestination();
                break;
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        return id;
    }

    public static int getChoiceBoxId(int id)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        String sql = "SELECT u FROM DestinationEntity u";
        List<DestinationEntity> destinationEntities =  entityManager.createQuery(sql, DestinationEntity.class).getResultList();
        String destName = null;
        for (DestinationEntity x:
             destinationEntities) {
            if(x.getIddestination()==id)
                destName = x.getName();
        }

        destinationEntities.sort(Comparator.comparing(DestinationEntity::getIddestination));

        int i=0;
        for (DestinationEntity x :
                destinationEntities) {
            assert destName != null;
            if (destName.equals(x.getName()))
            {
                return i;
            }
            i++;
        }
        System.out.println("nu s-a gasit");

        entityManager.getTransaction().commit();
        entityManager.close();
        return i;
    }

    public static void deleteDestination(int id)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        DestinationEntity destinationToDelete = entityManager.find(DestinationEntity.class, id);
        VacationPackageRepository.deleteByDestination(id);
        entityManager.remove(destinationToDelete);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void addDestination(String destinationName)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        int destinationId = 0;
        String sql = "SELECT u FROM DestinationEntity u";
        List<DestinationEntity> destinationEntities =  entityManager.createQuery(sql, DestinationEntity.class).getResultList();
        for (DestinationEntity x :
                destinationEntities) {
            if(x.getIddestination()>destinationId)
                destinationId = x.getIddestination();
        }

        DestinationEntity destinationEntity = new DestinationEntity(destinationId+1,destinationName);
        entityManager.persist(destinationEntity);
        entityManager.flush();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void main(String[] args)
    {
        getAllDestinations();
    }
}
