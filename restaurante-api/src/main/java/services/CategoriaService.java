package services;

import DTO.categoria.CategoriaDTO;
import Mapper.ObjectMapper;
import entity.Categoria;
import entity.Prato;
import jakarta.transaction.Transactional;
import org.hibernate.sql.exec.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CategoriaRepository;
import repository.PratoRepository;

import java.util.List;

@Service
public class CategoriaService {

    private static final Logger log = LoggerFactory.getLogger(CategoriaService.class);

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PratoRepository pratoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public List<CategoriaDTO> buscarTodasAsCategorias() {
        log.info("Buscando todos os usuarios");

        List<Categoria> categorias = categoriaRepository.findAllByOrderByNomeAsc();
        List<CategoriaDTO> categoriasDTO = ObjectMapper.parseListObjects(categorias, CategoriaDTO.class);

        return categoriasDTO;
    }

    @Transactional
    public CategoriaDTO BuscarCategoriaPorID(Long Id) {
        log.info("Buscando Categoria Por Id");

        Categoria categoria = categoriaRepository.findById(Id).orElseThrow(() -> new ExecutionException("exception id"));

        CategoriaDTO categoriaDTO = ObjectMapper.parseObject(categoria, CategoriaDTO.class);

        return categoriaDTO;
    }

    @Transactional
    public CategoriaDTO criarCategoria(CategoriaDTO categoriaDTO) {

        Categoria categoria = ObjectMapper.parseObject(categoriaDTO, Categoria.class);
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return ObjectMapper.parseObject(categoriaSalva, CategoriaDTO.class);
    }

    @Transactional
    public CategoriaDTO atualizarCategoria(Long Id, CategoriaDTO categoriaDTO) {
        log.info("atualizando Categoria");

        Categoria categoriaExistente = categoriaRepository.findById(Id).orElseThrow(
                () -> new IllegalArgumentException()
        );

        categoriaExistente.setNome(categoriaDTO.getNome());
        categoriaExistente.setDescricao(categoriaDTO.getDescricao());
        categoriaExistente.setPratos(ObjectMapper.parseListObjects(categoriaDTO.getPratosDTO(), Prato.class));

        Categoria categoriaSalva = categoriaRepository.save(categoriaExistente);
        CategoriaDTO categoriaSalvaDTO = ObjectMapper.parseObject(categoriaSalva, CategoriaDTO.class);

        return categoriaSalvaDTO;
    }

    @Transactional
    public void excluirCategoria(Long Id) {
        log.info("excluindo usuario");

        categoriaRepository.deleteById(Id);
    }
}
