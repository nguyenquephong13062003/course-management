package com.example.course_management.utils;

import com.example.course_management.model.Identifiable;

import java.util.List;

public class IdGenerator<T extends Identifiable> {
    private final List<T> models;

    public IdGenerator(List<T> models) {
        this.models = models;
    }

    public Long next() {
        return models.stream()
                .map(T::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;
    }
}
