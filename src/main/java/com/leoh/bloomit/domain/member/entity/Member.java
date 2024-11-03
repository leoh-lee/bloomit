package com.leoh.bloomit.domain.member.entity;

import com.leoh.bloomit.auth.dto.SignUpDto;
import com.leoh.bloomit.common.entity.BaseEntity;
import com.leoh.bloomit.domain.library.entity.Library;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "library_id")
    private Library library;

    public static Member create(String username, String password, String name, String nickname) {
        Member member = Member.builder()
                .name(name)
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();
        member.library = Library.create(member);

        return member;
    }

    public static Member from(SignUpDto signUpDto) {
        Member member = Member.builder()
                .name(signUpDto.getName())
                .username(signUpDto.getUsername())
                .password(signUpDto.getPassword())
                .nickname(signUpDto.getNickname())
                .build();
        member.library = Library.create(member);

        return member;
    }

}
