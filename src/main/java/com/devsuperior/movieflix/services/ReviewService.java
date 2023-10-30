package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    @PreAuthorize("hasRole('MEMBER')")
    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        Review entity = new Review();

        Movie movie = movieRepository.getOne(dto.getMovieId());
        User user = authService.authenticated();

        entity.setText(dto.getText());
        entity.setMovie(movie);

        repository.save(entity);
        return new ReviewDTO(entity, user);
    }
}