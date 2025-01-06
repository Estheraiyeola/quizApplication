package com.essiecodes.databasequizapplication.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
public enum UserRole {
    FACILITATOR,
    STUDENT;
}
