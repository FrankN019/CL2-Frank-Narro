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
public class Inventory {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
}
