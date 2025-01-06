package com.essiecodes.databasequizapplication.service;

import com.essiecodes.databasequizapplication.data.model.Options;

import java.util.List;

public interface OptionsService {
    void saveOption(Options option);

    void saveOption(List<Options> optionsList);

}
