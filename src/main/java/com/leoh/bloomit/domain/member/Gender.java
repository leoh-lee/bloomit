package com.leoh.bloomit.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {

    MALE("male"), FEMALE("description");

    private final String description;
}
