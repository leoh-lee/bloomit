package com.leoh.bloomit.domain.library.repository;

import com.leoh.bloomit.domain.library.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LibraryRepository extends JpaRepository<Library, Long> {

    @Query("select distinct l from Library l join fetch l.member m left join fetch l.libraryBooks lb left join fetch lb.book b where l.member.id = :memberId")
    Library findByMemberId(@Param("memberId") Long memberId);

}
