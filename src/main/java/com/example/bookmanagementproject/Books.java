package com.example.bookmanagementproject;

public class Books {
    private String bookName;
    private String author;
    private String ISBN;
    private String category;
    private int inventory;
    private String user;

    public Books(String bookName, String author, String ISBN, String category, int inventory) {
        this.bookName = bookName;
        this.author = author;
        this.ISBN = ISBN;
        this.category = category;
        this.inventory = inventory;
    }
    public Books(String bookName, String author, String category){
        this.bookName = bookName;
        this.author = author;
        this.category = category;
    }
    public Books(String bookName, String author, String category, String user){
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.user = user;
    }


    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void setUser(String user){ this.user = user;}

    public String getUser(){return this.user;}

}
