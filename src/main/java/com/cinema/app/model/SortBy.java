package com.cinema.app.model;

public class SortBy {
    private String sort;
    private String sortOrder;
    private String filterAge;

    public String getSort() {
        return sort;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public String getFilterAge() {
        return filterAge;
    }

    public static class Builder {
        private final SortBy newSortBy;

        public Builder() {
            newSortBy = new SortBy();
        }

        public SortBy.Builder withSort(String sort) {
            newSortBy.sort = sort;
            return this;
        }

        public SortBy.Builder withSortOrder(String sortOrder) {
            newSortBy.sortOrder = sortOrder;
            return this;
        }

        public SortBy.Builder withFilterAge(String filterAge){
            newSortBy.filterAge = filterAge;
            return this;
        }

        public SortBy build() {
            return newSortBy;
        }
    }
}
