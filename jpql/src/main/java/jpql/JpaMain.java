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

            em.flush();
            em.clear();

            String query = "select m from Member m";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();

            System.out.println("\n---------------------------\n");

            // 현재는 team은 프록시 객체임
            for (Member member : result) {
                System.out.println("member.getUsername() = " + member.getUsername() + ", " + member.getTeam().getName());
                // 회원1, 팀A(SQL)
                // 회원2, 팀A(1차 캐시)
                // 회원3, 팀B(SQL)
                // => 총 쿼리가 3번 전송됨
                // => 회원 N명 -> 최대 N + 1발생
            }

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
