package com.leoh.bloomit.config;

import com.leoh.bloomit.domain.member.Member;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (ObjectUtils.isEmpty(authentication) || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        Member member = (Member) authentication.getPrincipal();

        return Optional.of(member.getId());
    }
}
