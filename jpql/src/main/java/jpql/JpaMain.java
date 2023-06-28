package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("team");
            em.persist(team);

            Member member1 = new Member("member1", 10, team);
            em.persist(member1);

            Member member2 = new Member("member2", 20, team);
            em.persist(member2);

            em.flush();
            em.clear();

            String query = "select t.members from Team t";
            Collection result = em.createQuery(query, Collection.class)
                    .getResultList();

            System.out.println("result = " + result);

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
