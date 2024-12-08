package com.example.webbackend.repository.entity.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private String username;
    private int numberOfDesignedQuestions;
    private int numberOfFollowers;
}
