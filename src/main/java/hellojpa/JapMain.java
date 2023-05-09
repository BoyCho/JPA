package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JapMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 문제 시 롤백을 위해 try-catch 사용
        try {

            Member findMember = em.find(Member.class, 1L);
            findMember.setName("C");

            // JPA를 통해 엔티티를 가져오면, JPA가 관리함.
            // 트랜잭션 커밋 시, 엔티티 상태를 확인 -> 변경 확인 시 UPDATE 쿼리 전송

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // 꼭 닫아줘야 함
            em.close();
        }

        emf.close();
    }
}
