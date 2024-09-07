package com.regofix.spannzangen.service;

import com.regofix.spannzangen.model.Collet;
import com.regofix.spannzangen.repository.ColletRepository;

import java.util.List;

public class ColletService {

    private final ColletRepository colletRepository = new ColletRepository();

    private final List<Integer> validSizes = List.of(6, 10, 15, 25, 32);
    private final List<String> validTypes = List.of("MB", "Std", "CF", "L", "S", "T", "SG", "TAP", "MQL");

    public List<Collet> getAllCollets() {
        return colletRepository.getAllCollets();
    }

    public Collet getColletById(int id) {
        Collet collet = colletRepository.getColletById(id);
        if (collet == null) {
            throw new IllegalArgumentException("Collet with ID " + id + " not found");
        }
        return collet;
    }

    public Collet addCollet(Collet collet) {
        if (!validSizes.contains(collet.getSize())) {
            throw new IllegalArgumentException("Invalid size: " + collet.getSize() + ". Valid sizes are " + validSizes);
        }

        if (!validTypes.contains(collet.getType())) {
            throw new IllegalArgumentException("Invalid type: " + collet.getType() + ". Valid types are " + validTypes);
        }

        if (!collet.getArticleNumber().matches("\\d{4}\\.\\d{5}")) {
            throw new IllegalArgumentException("Invalid article number: " + collet.getArticleNumber() + ". It should follow the format xxxx.xxxxx containing only Numbers");
        }

        return colletRepository.addCollet(collet);
    }

    public void deleteCollet(int id) {
        boolean deleted = colletRepository.deleteCollet(id);
        if (!deleted) {
            throw new IllegalArgumentException("Collet with ID " + id + " not found");
        }

    }
}
