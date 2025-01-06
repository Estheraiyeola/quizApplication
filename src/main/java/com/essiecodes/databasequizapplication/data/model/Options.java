package com.essiecodes.databasequizapplication.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "`option`")
    private String option;
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

}
