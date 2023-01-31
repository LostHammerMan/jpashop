package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


// 클래스 단위로 @Transactional -> 클래스 내 public 메서드는 Transactional 에 걸림
@Service
@Transactional(readOnly = true) // 데이터 변경시 항상 @Transactional 필요
//@AllArgsConstructor // 아래 생성자 인젝션에 필요한 모든 필드에 존재하는 생성자 생성
@RequiredArgsConstructor // final 이 있는 필드만으로 생성자 생성
public class MemberService {

    // 필드 인젝션 -> 단점 : MemberRepository 바꿀 수 없음
   /* @Autowired
    private MemberRepository memberRepository;*/


    private final MemberRepository memberRepository; // MemberRepository 를 변경할 일이 없으므로 final 선언

    // setter 인젝션 사용 -> 장점 : 테스트 코드 작성시 유리, 단점 : 실제 서비스 운영시 set 메서드 변경 가능
   /* @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }*/

    // 생성자 인젝션 -> 권장 방법
    /*@Autowired // @Autowired 생략 가능
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }*/


    // 회원 가입
    @Transactional // 읽기가 아닌 쓰기의 경우 readOnly = true 사용 X, 데이터 변경 안됨, 따로 설정한 경우 우선권 가짐
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member); // 실무에서 동명이인이 동시에 가입하는 경우 문제 발생 가능 -> name을 unique 제약조건으로 설정하여 방지
        return member.getId();
    }

    // 회원 전체 조회
    //@Transactional(readOnly = true) // 읽기 전용으로 해서 조회시 성능 최적화 옵션(단순 조회시 사용)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 회원 단수 조회
    //@Transactional(readOnly = true)
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);

    }


    // 중복 회원 검증 메서드
    private void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }
}
