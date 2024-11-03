package com.leoh.bloomit.domain.genre.entity;

import com.leoh.bloomit.domain.bookgenre.entity.BookGenre;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "genre")
    private List<BookGenre> bookGenre = new ArrayList<>();

    public static Genre create(String name) {
        Genre genre = new Genre();
        genre.name = name;
        return genre;
    }

}
