package com.regofix.spannzangen.repository;

import com.regofix.spannzangen.model.Collet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ColletRepository {

    private final List<Collet> collets = new ArrayList<>();

    // using Atomic Integer because it is thread safe
    private final AtomicInteger idGenerator = new AtomicInteger();

    public List<Collet> getAllCollets() {
        return collets;
    }

    public Optional getColletById(int id) {
        for (Collet collet : collets) {
            if (collet.getId() == id) {
                return Optional.of(collet);
            }
        }
        return Optional.empty();
    }

    public Collet addCollet(Collet collet) {
        collet.setId(idGenerator.incrementAndGet());
        collets.add(collet);
        return collet;
    }

    public boolean deleteCollet(int id) {
        for (Collet collet : collets) {
            if (collet.getId() == id) {
                collets.remove(collet);
                return true;
            }
        }
        return false;
    }
}
