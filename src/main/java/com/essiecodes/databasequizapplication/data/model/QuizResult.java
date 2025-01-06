package com.essiecodes.databasequizapplication.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Quiz quiz; // The quiz taken

    @ManyToOne
    private User user; // The user who took the quiz

    private int score; // The user's score

    private int totalMarks; // Total marks for the quiz

    private double percentage; // The user's percentage score
}
