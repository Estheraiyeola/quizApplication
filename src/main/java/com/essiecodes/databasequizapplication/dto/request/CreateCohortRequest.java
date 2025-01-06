package com.essiecodes.databasequizapplication.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCohortRequest {
    private String name;
    private long number;
}
