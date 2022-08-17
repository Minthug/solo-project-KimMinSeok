package com.section4.Section4SoloProject.member.service;

import com.section4.Section4SoloProject.member.Dto.TestDto;
import com.section4.Section4SoloProject.member.entity.Member;
import com.section4.Section4SoloProject.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
public class MemberService {
    public static String query = "SELECT * FROM MEMBER m WHERE m.id = 1l";

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager entityManager;

    public Page<Member> getMemberList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Member> MemberList = memberRepository.findAll(pageable);

        return MemberList;
    }

    public TestDto test(int page, int size) {
        return new TestDto(page,size);
    }

    public void init() {
        memberRepository.addDummy();
        memberRepository.addDummy2();
    }
    public List<Member> findMember(String query, int size, int page) {
        System.out.println("service : " + query);

        List<Member> memberList = memberRepository.test(entityManager, query, size, page);

        for(Member mm: memberList) {
            System.out.println(mm.getName());
        }
        return memberList;
    }
}
