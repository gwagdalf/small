package com.small.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String passwd;
    @Column(name="reg_date")
    private LocalDateTime regdate;

    // Member가 영속성을 가질때 memberRoles도 영속성을 가지도록 한다.
    @JsonManagedReference
    @OneToMany(mappedBy="member", cascade = CascadeType.ALL)
    private List<MemberRole> memberRoles = new ArrayList<>();

    // helper method
    public void addMemberRole(MemberRole memberRole){
        memberRoles.add(memberRole);
        if(memberRole.getMember() != this){
            memberRole.setMember(this);
        }
    }
}
