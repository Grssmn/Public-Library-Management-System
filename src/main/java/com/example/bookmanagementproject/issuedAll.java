package com.example.bookmanagementproject;

import java.util.Date;

public class issuedAll {
    private String username;
    private String ISBN;
    private Date checkoutDate;
    private Date returnDate;

    public issuedAll(String username, String ISBN, Date checkoutDate, Date returnDate) {
        this.username = username;
        this.ISBN = ISBN;
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
