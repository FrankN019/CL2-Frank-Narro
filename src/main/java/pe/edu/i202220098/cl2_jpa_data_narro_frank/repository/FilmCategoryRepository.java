package pe.edu.i202220098.cl2_jpa_data_narro_frank.repository;


import org.springframework.data.repository.CrudRepository;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.entity.FilmCategory;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.entity.FilmCategoryId;

public interface FilmCategoryRepository extends CrudRepository<FilmCategory, FilmCategoryId> {
}
