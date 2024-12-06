package pe.edu.i202220098.cl2_jpa_data_narro_frank.dto;

public record FilmDto(Integer filmId,
                      String title,
                      String language,
                      Integer rentalDuration,
                      Double rentalRate) {
}
