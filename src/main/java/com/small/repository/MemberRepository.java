package com.small.repository;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.small.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository
        extends JpaRepository<Member, Long> {
    // jpql - Member엔티티들중에서 조회하라.
    // fetch join
    @HystrixCommand
    @Query("select distinct m from Member m join fetch m.memberRoles")
    public List<Member> getMembers();

    @HystrixCommand
    @Query("select m from Member m join fetch m.memberRoles where m.id = :id")
    public Member getMember(@Param("id") Long id);

    @HystrixCommand
    public Member getMemberByEmail(String email);
}
