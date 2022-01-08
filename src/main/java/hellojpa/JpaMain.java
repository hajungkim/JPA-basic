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
            // 변경 감지
            /*
            영속성 컨텍스트(entityManager)안의 1차 캐시에 엔티티와 스냅샷(값이 처음 들어온 시점)이 저장되어 있는데,
            엔티티를 변경했을때, JPA는 엔티티와 스냅샷을 비교한다.
            값이 바뀌었다면 UPDATE SQL을 생성해 쓰기 지연 SQL 저장소에 만들어둔다.
            그리고 UPDATE를 DB에 반영한뒤, 커밋하게 된다.
            엔티티 값을 바꾸고 em.persist() 를 따로 호출할 필요가 없음~!
             */
            Member member = em.find(Member.class, 150L);    // 영속성 엔티티 조회
            member.setName("ZZZ");  // 영속 엔티티 수정

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
