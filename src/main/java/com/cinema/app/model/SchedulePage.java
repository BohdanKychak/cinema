package com.cinema.app.model;

import java.util.List;

public class SchedulePage {

    private Page page;
    private SortBy sortBy;
    private List<Movies> movies;

    public Page getPage() {
        return page;
    }

    public SortBy getSortBy() {
        return sortBy;
    }

    public List<Movies> getMovies() {
        return movies;
    }

    public static class Builder {
        private final SchedulePage newSchedulePage;

        public Builder() {
            newSchedulePage = new SchedulePage();
        }

        public SchedulePage.Builder withPage(Page page) {
            newSchedulePage.page = page;
            return this;
        }

        public SchedulePage.Builder withSortBy(SortBy sortBy) {
            newSchedulePage.sortBy = sortBy;
            return this;
        }

        public SchedulePage.Builder withMovies(List<Movies> movies) {
            newSchedulePage.movies = movies;
            return this;
        }

        public SchedulePage build() {
            return newSchedulePage;
        }
    }
}
