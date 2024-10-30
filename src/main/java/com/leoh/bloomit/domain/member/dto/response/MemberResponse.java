package com.leoh.bloomit.domain.member.dto.response;

import com.leoh.bloomit.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {

    private Long id;

    private String username;

    private String name;

    private String nickname;

    public static MemberResponse fromEntity(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getUsername(),
                member.getName(),
                member.getNickname()
        );
    }
}
