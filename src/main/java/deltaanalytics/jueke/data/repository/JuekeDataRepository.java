package deltaanalytics.jueke.data.repository;

import deltaanalytics.jueke.data.entity.JuekeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public abstract class JuekeDataRepository<S extends JuekeEntity> {
    private static final Logger logger = LoggerFactory.getLogger(JuekeDataRepository.class);

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public long createOrUpdate(S entity) {
        if (exists(entity)) {
            startDBOperation();
            entityManager.merge(entity);
            endDBOperation();
        } else {
            startDBOperation();
            entityManager.persist(entity);
            endDBOperation();
        }
        return entity.getId();
    }

    public S read(long id) {
        startDBOperation();
        S entity = entityManager.find(getEntityClass(), id);
        endDBOperation();
        return entity;
    }

    public abstract boolean exists(S entity);

    public List<S> findAll() {
        startDBOperation();
        List list = entityManager.createQuery("Select t from " + getEntityClass().getSimpleName() + " t").getResultList();
        endDBOperation();
        return list;
    }

    protected abstract Class<S> getEntityClass();

    protected void startDBOperation() {
        createEntityManagerFactoryIfNotSet();
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    private void createEntityManagerFactoryIfNotSet() {
        try {
            if (entityManagerFactory == null) {
                entityManagerFactory = Persistence.createEntityManagerFactory("jueke-data-pu");
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new RuntimeException(e.toString());
        }
    }

    protected void endDBOperation() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteAll() {
        startDBOperation();
        List<S> list = entityManager.createQuery("Select t from " + getEntityClass().getSimpleName() + " t").getResultList();
        for (S entity : list) {
            entityManager.remove(entity);
        }
        endDBOperation();
    }
}