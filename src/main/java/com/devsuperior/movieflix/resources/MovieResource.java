package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<Page<MovieMinDTO>> find(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable){

        Page<MovieMinDTO> page = service.find(genreId, pageable);

        return ResponseEntity.ok().body(page);
    }
    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findAll(@RequestParam(value = "genreId", defaultValue = "0") Long genreId,
                                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                                                  @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                  @RequestParam(value = "orderBy", defaultValue = "title") String orderBy) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<MovieDTO> list = service.findAllPaged(genreId, pageRequest);

        return ResponseEntity.ok().body(list);
    }

    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> findById(@Valid @PathVariable Long id) {
        MovieDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }


    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> findByIdWithReviews(@PathVariable Long id) {
        List<ReviewDTO> reviewDTO = service.findByIdWithReviews(id);

        return ResponseEntity.ok().body(reviewDTO);
    }
}


