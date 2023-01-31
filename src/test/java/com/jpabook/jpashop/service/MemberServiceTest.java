package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class) // Test 진행시 Spring 과 엮어서 사용
@SpringBootTest // Spring boot 를 띄운 상태에서 test 실행, 없으면 @Autowired 작동 안함
@Transactional // test case 에 사용하면 기본적으로 commit 이 아닌 rollback 함(영속성 컨텍스트를 flush 하지 않음)
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
//    @Rollback(value = false)
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("Kim");
        // when
        Long savedId =memberService.join(member);
        // then
//        em.flush(); // 영속성 컨테스트 변경사항을 DB에 반영
        Assert.assertEquals(member, memberRepository.findOne(savedId));

    }
    
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_에외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("Kim");

        Member member2 = new Member();
        member2.setName("Kim");
        // when
        memberService.join(member1);
        memberService.join(member2);

        // expected = IllegalStateException.class 와 같은 역할
        /*try {
            memberService.join(member2); // Exception 예상
        } catch (IllegalStateException e){
            return;

        }*/

        // then
        Assert.fail("예외가 발생해야 한다....."); // Assert.fail() : 코드가 정상적으로 작동하지 않는 경우 작동
    }
}