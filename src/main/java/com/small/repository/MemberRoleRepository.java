package com.small.repository;

import com.small.domain.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository
        extends JpaRepository<MemberRole, Long> {

}
