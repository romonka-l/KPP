package com.solution.lab1.entity;

import javax.persistence.*;

@Entity
@Table(name="results")
public class DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column (name = "result")
    private Integer result;



    public DatabaseEntity(Integer result) {
        this.result = result;
    }

    public DatabaseEntity() {

    }
}