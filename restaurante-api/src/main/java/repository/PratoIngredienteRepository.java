package repository;

import entity.PratoIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PratoIngredienteRepository extends JpaRepository<PratoIngrediente, Long> {
}
