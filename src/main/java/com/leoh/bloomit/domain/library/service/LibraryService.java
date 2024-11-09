package com.leoh.bloomit.domain.library.service;

import com.leoh.bloomit.domain.library.dto.response.LibrarySearchResponse;
import com.leoh.bloomit.domain.library.entity.Library;
import com.leoh.bloomit.domain.library.entity.collection.Libraries;
import com.leoh.bloomit.domain.library.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public List<LibrarySearchResponse> findByMemberId(long memberId) {
        List<Library> library = libraryRepository.findByMemberId(memberId);
        Libraries libraries = Libraries.of(library);

        return libraries.toLibrarySearchResponseList();
    }

}
