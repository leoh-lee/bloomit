package com.leoh.bloomit.domain.librarybook.repository;

import com.leoh.bloomit.domain.librarybook.entity.LibraryBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {
}
