package com.regofix.spannzangen;

import com.regofix.spannzangen.model.Collet;
import com.regofix.spannzangen.repository.ColletRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StartupBean {

    private final ColletRepository colletRepository;

    // Inject the repository
    public StartupBean() {
        this.colletRepository = new ColletRepository();
    }

    // This method will run when the application starts
    @PostConstruct
    public void init() {

        Collet collet1 = new Collet(0, 10, "ER", "ER11");
        Collet collet2 = new Collet(0, 20, "ER", "ER16");

        colletRepository.addCollet(collet1);
        colletRepository.addCollet(collet2);

        System.out.println("Initialized Collets:");
        colletRepository.getAllCollets().forEach(collet ->
                System.out.println("ID: " + collet.getId() + ", Size: " + collet.getSize() + ", Type: " + collet.getType() + ", Article: " + collet.getArticleNumber())
        );
    }

    public ColletRepository getColletRepository() {
        return colletRepository;
    }
}
