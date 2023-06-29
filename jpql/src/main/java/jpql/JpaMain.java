package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member("회원1", 10, teamA);
            em.persist(member1);

            Member member2 = new Member("회원2", 20, teamA);
            em.persist(member2);

            Member member3 = new Member("회원3", 30, teamB);
            em.persist(member3);

            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();
            // Flush로 엔티티 DB에 적용 후 벌크 연산(executeUpdate()) 실행됨

            System.out.println("resultCount = " + resultCount);

            System.out.println("member1.getAge() = " + member1.getAge());

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
