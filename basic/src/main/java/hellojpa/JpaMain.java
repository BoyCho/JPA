package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Address address1 = new Address("city", "street", "10000");

            Member member1 = new Member();
            member1.setName("member1");
            member1.setHomeAddress(address1);
            em.persist(member1);

            Address address2 = new Address(address1.getCity(), address1.getStreet(), address1.getZipcode());

            Member member2 = new Member();
            member2.setName("member2");
            member2.setHomeAddress(address2);
            em.persist(member2);

            member1.getHomeAddress().setCity("newCity");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
