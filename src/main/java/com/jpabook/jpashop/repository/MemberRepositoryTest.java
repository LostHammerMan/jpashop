package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.MemberTest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepositoryTest {

    // 해당 어노테이션이 있으면 스프링부트에서 엔티티 매니저 만들어줌
    @PersistenceContext
    private EntityManager em;

    //
    public Long save(MemberTest memberTest){
        em.persist(memberTest); // 영속성 컨텍스트를 통해 엔티티를 영속화
        return memberTest.getId();
    }

    public MemberTest find(Long id){
        return em.find(MemberTest.class, id);
    }
}
