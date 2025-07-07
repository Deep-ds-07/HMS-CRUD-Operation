package com.my.demosms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "studentInfo")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private int age;

    @Column(nullable = false,unique = true)
    private String email;

    @Column
    private String course;

    @Column
    private String address;

}
