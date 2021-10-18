package com.cinema.app.model;

import java.util.List;

public class SchedulePage {

    private Page page;
    private List<Movies> movies;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Movies> getMovies() {
        return movies;
    }

    public void setMovies(List<Movies> movies) {
        this.movies = movies;
    }
}
