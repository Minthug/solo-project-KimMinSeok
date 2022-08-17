package com.section4.Section4SoloProject.member.Dto;

import com.section4.Section4SoloProject.member.entity.Member;
import lombok.Getter;

import java.awt.print.Pageable;
import java.util.List;

@Getter
public class MemberResponse {
    private List<Member> members;
    private Pageable pageInfo;
    public MemberResponse(List<Member> Members, Pageable pageInfo) {
     this.members = Members;
     this.pageInfo = pageInfo;
    }
}
