package com.section4.Section4SoloProject.member.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "MemberBuilder")
@Entity
public class Member{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    public static MemberBuilder builder(String name) {
        if(name == null || name.length() == 0) {
            throw new IllegalStateException("필수 파라미터 누락");
        }
        return MemberBuilder().name(name);
    }

    private String name;
    private String password;
    private String company_name;
    private long company_type;
    private long company_location;
}
