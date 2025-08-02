package repository;

import backup.Mesa;
import enums.StatusMesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MesaRepository extends JpaRepository<Mesa, Long> {

    Optional<Mesa> findByNumero(Integer numero);
    boolean existsByNumero(Integer numero);

    List<Mesa> findByCapacidade(Integer capacidade);
    List<Mesa> findByCapacidadeGreaterThanEqual(Integer minCapacidade);

    List<Mesa> findByStatus(StatusMesa status);
    List<Mesa> findByStatusIn(List<StatusMesa> statuses);

    @Query("SELECT m FROM Mesa m WHERE m.status = 'OCUPADA'")
    List<Mesa> findMesasOcupadas();

    List<Mesa> findByCapacidadeBetween(Integer min, Integer max);

    List<Mesa> findAllByOrderByNumeroAsc();
    List<Mesa> findAllByOrderByCapacidadeAsc();

    @Query("SELECT m FROM Mesa m WHERE m.capacidade >= :pessoas AND m.status = 'LIVRE'")
    List<Mesa> findMesasDisponiveisParaPessoas(@Param("pessoas") Integer pessoas);

}
