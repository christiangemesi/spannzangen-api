package com.regofix.spannzangen.repository;

import com.regofix.spannzangen.model.Collet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ColletRepository {

    private final List<Collet> collets = new ArrayList<>();

    // using Atomic Integer because it is thread safe
    private final AtomicInteger idGenerator = new AtomicInteger();

    public List<Collet> getAllCollets() {
        return collets;
    }

    public Collet getColletById(int id) {
        return collets.stream()
                .filter(collet -> collet.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Collet addCollet(Collet collet) {
        collet.setId(idGenerator.incrementAndGet());
        collets.add(collet);
        return collet;
    }

    public boolean deleteCollet(int id) {
        return collets.removeIf(collet -> collet.getId() == id);
    }
}
