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

            // 비영속 상태
            //Member member = new Member();
            //member.setId(100L);
            //member.setName("HelloJPA");

            // 영속상태 != DB 저장
            //em.persist(member);
            // 준영속 상태 => 영속성 컨텍스트에서 지
            //em.detach(member);
            // 삭제 => 영구적으로 삭제 요청 (DB에서도!)
            //em.remove(member);

            // 1차 캐시에 저장된 회원조회
            //Member findMember = em.find(Member.class, 100L);
            // DB에 select 쿼리 안 날 == 1차 캐시에 저장되었음을 알 수 있음
            //System.out.println("findMember.getId() = " + findMember.getId());
            //System.out.println("findMember.getName() = " + findMember.getName());

            // DB에 저장된 회원조회
            Member findMember1 = em.find(Member.class, 100L); // 이때는 DB 에서 가져와야 하기 때문에 쿼리가 나가야하지만
            Member findMember2 = em.find(Member.class, 100L); // 여기서는 1차캐시에서 가져와야 하기 때문에 쿼리가 나가면 안됨
            // 직접 돌려보면 select 쿼리 한 번만 나가는 것 확인 가능

            // Member 객체를 대상으로 조회
            // m -> entity
            // setFirstResult + setMaxResults => paging 기능
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.getName() = " + member.getName());
//            }



            // 회원수정
            //findMember.setName("HelloJPA");

            //em.persist() 안 해도 됨 -> Java 컬렉션처럼 작동
            //JPA 를 통해서 entity를 불러오면 그 순간부터 JPA 가 관리 => tx.commit 시점에 변동된 사항 있는지 없는지 확인 => 있으면 update 쿼리 자동 생성 후 실행

            // 회원삭제
            // em.remove(findMember);

            // 여기서 쿼리 날림
            tx.commit(); // 안 하면 DB에 반영 안 됨
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
