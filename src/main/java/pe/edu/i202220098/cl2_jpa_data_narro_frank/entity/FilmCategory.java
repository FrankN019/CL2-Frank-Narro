package pe.edu.i202220098.cl2_jpa_data_narro_frank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmCategory {
    @EmbeddedId
    private FilmCategoryId id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    private Film film;
}
