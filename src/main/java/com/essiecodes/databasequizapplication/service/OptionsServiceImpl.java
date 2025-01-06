package com.essiecodes.databasequizapplication.service;

import com.essiecodes.databasequizapplication.data.model.Options;
import com.essiecodes.databasequizapplication.data.repository.OptionsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OptionsServiceImpl implements OptionsService{
    private final OptionsRepo optionsRepo;
    @Override
    public void saveOption(Options option) {
        optionsRepo.save(option);
    }

    @Override
    public void saveOption(List<Options> optionsList) {
        optionsRepo.saveAll(optionsList);
    }
}
