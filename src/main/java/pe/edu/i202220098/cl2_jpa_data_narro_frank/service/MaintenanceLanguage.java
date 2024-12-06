package pe.edu.i202220098.cl2_jpa_data_narro_frank.service;


import pe.edu.i202220098.cl2_jpa_data_narro_frank.dto.LanguageDto;

import java.util.List;

public interface MaintenanceLanguage {
    List<LanguageDto> findAllLanguages();
}
