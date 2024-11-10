package com.leoh.bloomit.domain.member.dto.response;

import com.leoh.bloomit.domain.member.entity.Member;

public record MemberResponse(Long id, String username, String name, String nickname) {

    public static MemberResponse fromEntity(Member member) {
        return new MemberResponse(member.getId(), member.getUsername(), member.getName(), member.getNickname());
    }
}
