package com.regofix.spannzangen.service;

import com.regofix.spannzangen.model.Collet;
import com.regofix.spannzangen.repository.ColletRepository;

import java.util.List;
import java.util.Optional;

public class ColletService {

    private final ColletRepository colletRepository = new ColletRepository();


    public List<Collet> getAllCollets() {
        return colletRepository.getAllCollets();
    }

    public Optional<Collet> getColletById(int id) {
        return colletRepository.getColletById(id);
    }


    public Collet addCollet(Collet collet) {
        return colletRepository.addCollet(collet);
    }


    public boolean deleteCollet(int id) {
        return colletRepository.deleteCollet(id);
    }
}
