package repository;

import entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    Optional<Cliente> findByNome(String nome);

    Optional<Cliente> findByTelefone(String telefone);
    boolean existsByTelefone(String telefone);

    List<Cliente> findByEnderecoContainingIgnoreCase(String endereco);

    List<Cliente> findAllByOrderByNomeAsc();

}
