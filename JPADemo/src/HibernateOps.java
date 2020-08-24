import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateOps {
	public static void test() {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory( "JobPortal_JPA" );
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Jobs jobs=new Jobs("Hello","Hello","Hello");
        entityManager.persist(jobs);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
	}
}
