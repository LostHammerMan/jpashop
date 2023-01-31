package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    // 스프링이 엔티티 매니저를 만들어 인젝션(스프링이 아니라면 엔티티 매니저를 따로 생성해 호출해야 함)
   /* @PersistenceContext
    private EntityManager em;*/

    @Autowired // 스프링 DataJPA 에서 제공, 기본적으로 사용시 아래와 같이 사용
    private final EntityManager em;


    // 위와 같이 사용하는 것이 편함
    // 엔티티 매니저 팩토리를 사용하려는 경우
    /* @PersistenceUnit
    private EntityManagerFactory emf;*/

    // 회원 저장 로직
    public void save(Member member){
        em.persist(member); // 트랜잭션이 커밋되는 시점에 DB에 반영
    }

    // 회원 조회(한명), 단건 조회
    public Member findOne(Long id){
        return em.find(Member.class, id);
        //            (타입, PK)
    }


    public List<Member> findAll(){
        // em.createQuery("쿼리문", 반환타입);
        // SQL과 차이점 : SQL 테이블의 column을 중심으로 쿼리작성
        // JPQL 는 entity 객체 중심으로 쿼리 작성, from 의 대상이 table이 아닌 entity
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        return result;
    }

    // 이름에 의한 회원조회
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name) // 데이터 바인딩
                .getResultList(); // 결과를 예제로 반환, 결과가 없을 경우 빈 컬렉션 반환
    }
}
