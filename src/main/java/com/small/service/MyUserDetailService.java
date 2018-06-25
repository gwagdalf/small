package com.small.service;

import com.small.domain.Member;
import com.small.domain.MemberRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    MemberService memberService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String id)
            throws UsernameNotFoundException {
        Member member = memberService.getMemberByEmail(id);
        if(member == null){
            throw new UsernameNotFoundException("user name not found : " + id);
        }

        // 레이지로딩.
        List<MemberRole> memberRoles = member.getMemberRoles();
        List<GrantedAuthority> list = new ArrayList<>();
        for(MemberRole memberRole: memberRoles) {
            list.add(new SimpleGrantedAuthority(
                    "ROLE_" + memberRole.getName()));
        }
        UserDetails user =
                new User(id, member.getPasswd(),
                   list);
        return user;
    }
}
