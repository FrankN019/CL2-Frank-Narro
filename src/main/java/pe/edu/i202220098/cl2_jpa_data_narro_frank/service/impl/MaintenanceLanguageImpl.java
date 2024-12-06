package pe.edu.i202220098.cl2_jpa_data_narro_frank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.dto.LanguageDto;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.entity.Language;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.repository.LanguageRepository;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.service.MaintenanceLanguage;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceLanguageImpl implements MaintenanceLanguage {

    private final LanguageRepository languageRepository;

    @Autowired
    public MaintenanceLanguageImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Cacheable(value = "languages")
    @Override
    public List<LanguageDto> findAllLanguages() {
        return ((List<Language>) languageRepository.findAll())
                .stream()
                .map(language -> new LanguageDto(language.getLanguageId(), language.getName()))
                .collect(Collectors.toList());
    }
}
