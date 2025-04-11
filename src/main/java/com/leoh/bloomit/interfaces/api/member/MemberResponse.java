package com.leoh.bloomit.interfaces.api.member;

import com.leoh.bloomit.domain.member.Member;

public record MemberResponse(Long id, String username, String name, String nickname) {

    public static MemberResponse fromEntity(Member member) {
        return new MemberResponse(member.getId(), member.getUsername(), member.getName(), member.getNickname());
    }
}
