package pe.edu.i202220098.cl2_jpa_data_narro_frank.dto;

public record FilmData(
        String title,
        String description,
        Integer releaseYear,
        Integer rentalDuration,
        Double rentalRate,
        Integer length,
        Double replacementCost,
        String rating,
        String specialFeatures,
        Integer languageId
) {
}
