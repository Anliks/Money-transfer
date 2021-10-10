package com.anliks.transaction.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private double bank_account;
    private int number_ofOperations;


    public Post() {
    }

    public Post(String name, double bank_account, int number_ofOperations) {
        this.name = name;
        this.bank_account = bank_account;
        this.number_ofOperations = number_ofOperations;
    }

}
