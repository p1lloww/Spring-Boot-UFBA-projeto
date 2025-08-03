package com.tomorrowproject.restaurante_api.services;

import com.tomorrowproject.restaurante_api.DTO.prato.PratoDTO;
import com.tomorrowproject.restaurante_api.Mapper.ObjectMapper;
import com.tomorrowproject.restaurante_api.entity.Categoria;
import com.tomorrowproject.restaurante_api.entity.Prato;
import com.tomorrowproject.restaurante_api.repository.PratoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PratoService {
    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

    @Autowired
    private PratoRepository pratoRepository;

    @Transactional
    public List<PratoDTO> buscarTodosOsPratos() {
        List<Prato> pratos = pratoRepository.findAllByOrderByNomeAsc();
        List<PratoDTO> pratosDTO = ObjectMapper.parseListObjects(pratos, PratoDTO.class);

        return pratosDTO;
    }

    @Transactional
    public PratoDTO buscarPratoPorID(Long Id) {
        Prato prato = pratoRepository.findById(Id).orElseThrow(
                () -> new IllegalArgumentException()
        );

        PratoDTO pratoDTO = ObjectMapper.parseObject(prato, PratoDTO.class);

        return pratoDTO;
    }

    @Transactional
    public PratoDTO criarPrato(PratoDTO pratoDTO) {
        Prato prato = ObjectMapper.parseObject(pratoDTO, Prato.class);
        Prato pratoSalvo = pratoRepository.save(prato);
        PratoDTO pratoDTOSalvo = ObjectMapper.parseObject(pratoSalvo, PratoDTO.class);

        return pratoDTOSalvo;
    }

    @Transactional
    public PratoDTO atualizarPrato(Long Id, PratoDTO pratoDTO) {
        Prato prato = pratoRepository.findById(Id).orElseThrow(
                () -> new IllegalArgumentException()
        );
        prato.setNome(pratoDTO.getNome());
        prato.setDescricao(pratoDTO.getDescricao());
        prato.setCategoria(ObjectMapper.parseObject(pratoDTO.getCategoria(), Categoria.class));
        prato.setPreco(pratoDTO.getPreco());
        prato.setTempoDePreparo(pratoDTO.getTempoDePreparo());
        PratoDTO pratoDTOAtualizado = ObjectMapper.parseObject(prato, PratoDTO.class);

        return pratoDTOAtualizado;
    }

    @Transactional
    public void excluirPrato(Long Id) {
        pratoRepository.deleteById(Id);
    }
}
