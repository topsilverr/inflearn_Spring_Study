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

        // Database connection 하나 받았다고 생각하기 => getTransaction
        EntityTransaction tx = em.getTransaction(); // 데이터 변경이 있을 경우 반드시 선언
        tx.begin();

        try {
            // 회원등록
            //Member member = new Member();
            //member.setId(2L);
            //member.setName("HelloB");
            //em.persist(member);

            // Member 객체를 대상으로 조회
            // m -> entity
            // setFirstResult + setMaxResults => paging 기능
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }

            // 회원조회
            Member findMember = em.find(Member.class, 1L);
            // 회원수정
            findMember.setName("HelloJPA");

            //em.persist() 안 해도 됨 -> Java 컬렉션처럼 작동
            //JPA 를 통해서 entity를 불러오면 그 순간부터 JPA 가 관리 => tx.commit 시점에 변동된 사항 있는지 없는지 확인 => 있으면 update 쿼리 자동 생성 후 실행

            // 회원삭제
            // em.remove(findMember);

            tx.commit(); // 안 하면 DB에 반영 안 됨
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
