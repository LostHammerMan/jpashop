package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.MemberTest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberTestRepositoryTest {

    @Autowired
    MemberRepositoryTest memberRepositoryTest;

    @Test
    @Transactional // 테스트 클래스에 해당 어노테이션 있으면 테스트 후 commit 하지 않고, rollback
    public void testMember() throws Exception {
        // given
        MemberTest memberTest = new MemberTest();
        memberTest.setUsername("memberA");

        // when
        Long savedId = memberRepositoryTest.save(memberTest);
        MemberTest findMemberTest = memberRepositoryTest.find(savedId);
        // then
        Assertions.assertThat(findMemberTest.getId()).isEqualTo(memberTest.getId());
        Assertions.assertThat(findMemberTest.getUsername()).isEqualTo(memberTest.getUsername());
        Assertions.assertThat(findMemberTest).isEqualTo(memberTest);

        System.out.println("");
        // 에러 발생 -> ENtityManager 내 모든 작업은 transaction에서 이뤄져야 하기 때문
//        org.springframework.dao.InvalidDataAccessApiUsageException: No EntityManager with actual transaction available for current thread
    }
}