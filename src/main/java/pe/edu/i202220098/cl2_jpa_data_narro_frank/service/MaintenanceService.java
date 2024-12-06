package pe.edu.i202220098.cl2_jpa_data_narro_frank.service;


import pe.edu.i202220098.cl2_jpa_data_narro_frank.dto.FilmData;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.dto.FilmDetailDto;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.dto.FilmDto;

import java.util.List;

public interface MaintenanceService {

    List<FilmDto> findAllFilms();

    FilmDetailDto findDetailById(Integer id);

    void createFilm(FilmData filmData);

    Boolean updateFilm(FilmDetailDto filmDetailDto);

    Boolean deleteFilm(Integer id);
}
