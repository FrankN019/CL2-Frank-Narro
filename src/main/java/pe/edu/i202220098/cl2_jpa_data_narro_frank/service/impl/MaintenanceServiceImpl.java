package pe.edu.i202220098.cl2_jpa_data_narro_frank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.dto.FilmData;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.dto.FilmDetailDto;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.dto.FilmDto;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.entity.Film;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.entity.Language;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.repository.FilmRepository;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.repository.LanguageRepository;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.service.MaintenanceService;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private final FilmRepository filmRepository;
    private final LanguageRepository languageRepository;

    @Autowired
    public MaintenanceServiceImpl(FilmRepository filmRepository, LanguageRepository languageRepository) {
        this.filmRepository = filmRepository;
        this.languageRepository = languageRepository;
    }

    @Cacheable(value = "films")
    @Override
    public List<FilmDto> findAllFilms() {
        return ((List<Film>) filmRepository.findAll())
                .stream()
                .map(this::convertToFilmDto)
                .collect(Collectors.toList());
    }

    @Override
    public FilmDetailDto findDetailById(Integer id) {
        return filmRepository.findById(id)
                .map(this::convertToFilmDetailDto)
                .orElse(null);
    }

    @CacheEvict(value = "films", allEntries = true)
    @Override
    public void createFilm(FilmData filmData) {
        Language language = findLanguageById(filmData.languageId());

        Film film = buildFilmFromData(filmData, language);
        filmRepository.save(film);
    }

    @CacheEvict(value = "films", allEntries = true)
    @Override
    public Boolean updateFilm(FilmDetailDto filmDetailDto) {
        return filmRepository.findById(filmDetailDto.filmId())
                .map(film -> {
                    updateFilmFromDetailDto(film, filmDetailDto);
                    filmRepository.save(film);
                    return true;
                })
                .orElse(false);
    }

    @CacheEvict(value = "films", allEntries = true)
    @Override
    public Boolean deleteFilm(Integer id) {
        return filmRepository.findById(id)
                .map(film -> {
                    filmRepository.delete(film);
                    return true;
                })
                .orElse(false);
    }

    // Métodos auxiliares

    private Language findLanguageById(Integer languageId) {
        return languageRepository.findById(languageId)
                .orElseThrow(() -> new IllegalArgumentException("El lenguaje con ID " + languageId + " no existe"));
    }

    private Film buildFilmFromData(FilmData filmData, Language language) {
        Film film = new Film();
        film.setTitle(filmData.title());
        film.setDescription(filmData.description());
        film.setReleaseYear(filmData.releaseYear());
        film.setRentalDuration(filmData.rentalDuration());
        film.setRentalRate(filmData.rentalRate());
        film.setLength(filmData.length());
        film.setReplacementCost(filmData.replacementCost());
        film.setRating(filmData.rating());
        film.setSpecialFeatures(filmData.specialFeatures());
        film.setLanguage(language);
        film.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        return film;
    }

    private void updateFilmFromDetailDto(Film film, FilmDetailDto filmDetailDto) {
        film.setTitle(filmDetailDto.title());
        film.setDescription(filmDetailDto.description());
        film.setReleaseYear(filmDetailDto.releaseYear());
        film.setRentalDuration(filmDetailDto.rentalDuration());
        film.setRentalRate(filmDetailDto.rentalRate());
        film.setLength(filmDetailDto.length());
        film.setReplacementCost(filmDetailDto.replacementCost());
        film.setRating(filmDetailDto.rating());
        film.setSpecialFeatures(filmDetailDto.specialFeatures());
        film.setLastUpdate(new Timestamp(System.currentTimeMillis()));
    }

    private FilmDto convertToFilmDto(Film film) {
        return new FilmDto(
                film.getFilmId(),
                film.getTitle(),
                film.getLanguage().getName(),
                film.getRentalDuration(),
                film.getRentalRate()
        );
    }

    private FilmDetailDto convertToFilmDetailDto(Film film) {
        return new FilmDetailDto(
                film.getFilmId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating(),
                film.getSpecialFeatures(),
                film.getLastUpdate()
        );
    }
}
