package com.small.service.impl;

import com.small.domain.Member;
import com.small.domain.MemberRole;
import com.small.repository.MemberRepository;
import com.small.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public Member getMember(Long id) {
        if(id == 10L)
            throw new RuntimeException("id == 10");
        return memberRepository.getMember(id);
    }

    @Override
    @Transactional
    public Member addMember(Member member) {
        MemberRole memberRole = new MemberRole();
        memberRole.setName("USER");
        member.addMemberRole(memberRole);
        member =
                memberRepository.save(member);
        return member;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> getMembers() {
        return memberRepository.getMembers();
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMemberByEmail(String email) {
        return memberRepository.getMemberByEmail(email);
    }
}
