package jpabook.jpashop;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.items.Movie;

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
            Movie movie = new Movie();
            movie.setDirector("Director");
            movie.setActor("Actor");
            movie.setName("Movie");
            movie.setPrice(1000);

            em.persist(movie);

            em.flush();
            em.clear();

            em.find(Movie.class, movie.getId());
            System.out.println("movie = " + movie.getName());

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
