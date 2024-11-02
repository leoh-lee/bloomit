package com.leoh.bloomit.domain.member.entity;

import com.leoh.bloomit.auth.dto.SignUpDto;
import com.leoh.bloomit.common.entity.BaseEntity;
import com.leoh.bloomit.domain.library.entity.Library;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
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

    public static Member from(SignUpDto signUpDto) {
        return Member.builder()
                .name(signUpDto.getName())
                .username(signUpDto.getUsername())
                .password(signUpDto.getPassword())
                .nickname(signUpDto.getNickname())
                .build();
    }

    // Member 에서 Library를 생성하는 게 과연 객체지향 적인가?
    public Library createAndSetLibrary() {
        Library createdLibrary = Library.create(this);
        this.library = createdLibrary;
        return createdLibrary;
    }

}
