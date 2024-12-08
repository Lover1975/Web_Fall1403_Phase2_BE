package com.example.webbackend.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer1;

    @Column(nullable = false)
    private String answer2;

    @Column(nullable = false)
    private String answer3;

    @Column(nullable = false)
    private String answer4;

    @Column(nullable = false)
    private Integer correctAnswer;

    @Column(nullable = false)
    private Integer hardness;

    @ManyToOne
    @JoinColumn(name = "categoryName", referencedColumnName = "category_name", nullable = false)
    private Category category;
}
