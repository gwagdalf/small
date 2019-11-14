package com.small.repository;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.small.domain.Member;
import com.small.domain.MemberRole;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository
        extends JpaRepository<Member, Long> {
    // jpql - Member엔티티들중에서 조회하라.
    // fetch join
    @Query("select distinct m from Member m join fetch m.memberRoles")
    List<Member> getMembers();

    @Query("select m from Member m join fetch m.memberRoles where m.id = :id")
//    @Query(value = "SELECT m.id, m.email, m.name, m.passwd, m.reg_date FROM member m WHERE m.id = 1",nativeQuery = true)
    Member getMember(@Param("id") Long id);


    Member getMemberByEmail(String email);



}
