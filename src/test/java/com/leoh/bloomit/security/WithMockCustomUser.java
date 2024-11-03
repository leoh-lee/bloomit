package com.leoh.bloomit.security;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WIthMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {
    String userName() default "user";
    String role() default "USER";
}
