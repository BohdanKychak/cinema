package com.cinema.app.model;

public class Page {
    private int total;
    private int position;
    private int pageSize;
    private int numberOfPages;

    public int getTotal() {
        return total;
    }

    public int getPosition() {
        return position;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public static class Builder {
        private final Page newPage;

        public Builder() {
            newPage = new Page();
        }

        public Page.Builder withTotal(int total) {
            newPage.total = total;
            return this;
        }

        public Page.Builder withPosition(int position) {
            newPage.position = position;
            return this;
        }

        public Page.Builder withPageSize(int pageSize) {
            newPage.pageSize = pageSize;
            return this;
        }

        public Page.Builder withNumberOfPages(int total, int pageSize){
            newPage.numberOfPages = (total + pageSize - 1)/pageSize;
            return this;
        }

        public Page build() {
            return newPage;
        }
    }
}
