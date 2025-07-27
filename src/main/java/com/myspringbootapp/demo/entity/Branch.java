package com.myspringbootapp.demo.entity;
import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

}

