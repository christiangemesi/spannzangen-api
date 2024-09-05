package com.regofix.spannzangen.model;

public class Collet {

    private int id;
    private int size;
    private String type;
    private String articleNumber;

    // Empty constructor for Flexibility to create an object
    public Collet() {}

    public Collet(int id, int size, String type, String articleNumber) {
        this.id = id;
        this.size = size;
        this.type = type;
        this.articleNumber = articleNumber;
    }

    // Getter and Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getArticleNumber() {
        return articleNumber;
    }
    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }


}
