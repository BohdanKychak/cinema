package com.cinema.app.model;

public class Movies {
    private long sessionId;
    private String movieTitle;
    private long movieId;
    private String age;
    private String poster;
    private String sessionDate;
    private String sessionTime;
    private double price;
    private int freePlaces;
    private long hallId;

    public long getSessionId() {
        return sessionId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public long getMovieId() {
        return movieId;
    }

    public String getAge() {
        return age;
    }

    public String getPoster() {
        return poster;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public String getSessionTime() {
        return sessionTime;
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

        public Builder withSessionId(long sessionId) {
            newMovies.sessionId = sessionId;
            return this;
        }

        public Builder withMovieTitle(String movieTitle) {
            newMovies.movieTitle = movieTitle;
            return this;
        }

        public Builder withMovieId(long movieId) {
            newMovies.movieId = movieId;
            return this;
        }

        public Builder withAge(String age) {
            newMovies.age = age;
            return this;
        }

        public Builder withPoster(String poster){
            newMovies.poster = poster;
            return this;
        }

        public Builder withSessionDate(String sessionDate) {
            newMovies.sessionDate = sessionDate;
            return this;
        }

        public Builder withSessionTime(String sessionTime) {
            newMovies.sessionTime = sessionTime;
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


