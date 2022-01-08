package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 준영속 상태
            /*
            - 영속 상태의 엔티티가 영속성 컨텍스트에서 분리(detached)
            - 영속성 컨텍스트가 제공하는 기능을 사용 못함
             */
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            em.detach(member);  // 엔티티를 분리했기 때문에 update 쿼리가 실행되지 않는다.

            System.out.println("==================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
