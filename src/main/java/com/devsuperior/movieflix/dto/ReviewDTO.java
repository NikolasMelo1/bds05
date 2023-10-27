package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ReviewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @Size(min = 5, message = "Deve ter no mínimo 5 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String text;

    private Movie movie;
    private User user;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id, String text) {
        super();
        this.id = id;
        this.text = text;
    }

    public ReviewDTO(Review entity) {
        id = entity.getId();
        text = entity.getText();
        movie = entity.getMovie();
        user = entity.getUser();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
