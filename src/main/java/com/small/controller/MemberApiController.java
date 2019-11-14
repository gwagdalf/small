package com.small.controller;

import com.small.domain.Member;
import com.small.dto.MyHeader;
import com.small.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberApiController {
    @Autowired
    MemberService memberService;

    @GetMapping
    public List<Member> getMembers(){
        return memberService.getMembers();
    }



    @GetMapping(path = "/{id}")
    public Member getMember(MyHeader myHeader,
        @PathVariable(name="id") Long id){
        System.out.println(myHeader.getAccept());
        System.out.println(myHeader.getHost());
        return memberService.getMember(id);
    }
}
