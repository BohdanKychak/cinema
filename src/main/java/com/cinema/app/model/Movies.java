package com.cinema.app.model;

public class Movies {
    private long id;
    private String movieTitle;
    private String access;
    private String movieTime;
    private String movieDate;
    private double price;
    private int freePlaces;
    private long hallId;

    public long getId() {
        return id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getAccess() {
        return access;
    }

    public String getMovieTime() {
        return movieTime;
    }

    public String getMovieDate() {
        return movieDate;
    }

    public double getPrice() {
        return price;
    }

    public int getFreePlaces() {
        return freePlaces;
    }

    public long getHallId() {
        return hallId;
    }

    public static class Builder {
        private final Movies newMovies;

        public Builder() {
            newMovies = new Movies();
        }

        public Builder withId(long id) {
            newMovies.id = id;
            return this;
        }

        public Builder withMovieTitle(String movieTitle) {
            newMovies.movieTitle = movieTitle;
            return this;
        }

        public Builder withAccess(String access) {
            newMovies.access = access;
            return this;
        }

        public Builder withMovieTime(String movieTime) {
            newMovies.movieTime = movieTime;
            return this;
        }

        public Builder withMovieDate(String movieDate) {
            newMovies.movieDate = movieDate;
            return this;
        }

        public Builder withPrice(double price) {
            newMovies.price = price;
            return this;
        }

        public Builder withFreePlaces(int freePlaces) {
            newMovies.freePlaces = freePlaces;
            return this;
        }

        public Builder withHallId(long hallId) {
            newMovies.hallId = hallId;
            return this;
        }

        public Movies build() {
            return newMovies;
        }

    }
}


