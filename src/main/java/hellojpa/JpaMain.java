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
            // 플러시(flush)
            /*
            - 영속성 컨텍스트의 변경내용을 DB에 반영하는 것
            플러시 발생 시, - 변경 감지
                          - 수정된 엔티티 쓰기 지연 SQL 저장소에 등록
                          - 쓰기 지연 SQL 저장소의 쿼리를 DB에 전송 (등록,수정,삭제 쿼리)
             */

            Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush();     // 커밋되기전에 쿼리 호출. 원래 플러시는 트랜잭션 커밋 시 자동 호출

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
