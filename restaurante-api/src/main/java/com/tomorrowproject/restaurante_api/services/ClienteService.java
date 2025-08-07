package com.tomorrowproject.restaurante_api.services;

import com.tomorrowproject.restaurante_api.DTO.cliente.ClienteDTO;
import com.tomorrowproject.restaurante_api.DTO.prato.PratoDTO;
import com.tomorrowproject.restaurante_api.Mapper.ObjectMapper;
import com.tomorrowproject.restaurante_api.entity.Categoria;
import com.tomorrowproject.restaurante_api.entity.Cliente;
import com.tomorrowproject.restaurante_api.exception.NotFoundException;
import com.tomorrowproject.restaurante_api.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private static final Logger log = LoggerFactory.getLogger(com.tomorrowproject.restaurante_api.services.PedidoService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public List<ClienteDTO> buscarTodosOsClientes() {
        List<Cliente> clientes = clienteRepository.findAllByOrderByNomeAsc();
        List<ClienteDTO> clientesDTO = ObjectMapper.parseListObjects(clientes, ClienteDTO.class);

        return clientesDTO;
    }

    @Transactional
    public ClienteDTO buscarClientePorID(Long Id) {
        Cliente cliente = clienteRepository.findById(Id).orElseThrow(
                () -> new NotFoundException("Id do cliente inexistente")
        );

        ClienteDTO clienteDTO = ObjectMapper.parseObject(cliente, ClienteDTO.class);

        return clienteDTO;
    }

    @Transactional
    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = ObjectMapper.parseObject(clienteDTO, Cliente.class);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        ClienteDTO clienteDTOSalvo = ObjectMapper.parseObject(clienteSalvo, ClienteDTO.class);

        return clienteDTOSalvo;
    }

    @Transactional
    public ClienteDTO atualizarCliente(Long Id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(Id).orElseThrow(
                () -> new NotFoundException("Id do cliente inexistente")
        );
        cliente.setNome(clienteDTO.getNome());
        cliente.setEndereco(cliente.getEndereco());
        cliente.setTelefone(cliente.getTelefone());
        ClienteDTO clienteDTOAtualizado = ObjectMapper.parseObject(cliente, ClienteDTO.class);

        return clienteDTOAtualizado;
    }

    @Transactional
    public void excluirCliente(Long Id) {
        Cliente cliente = clienteRepository.findById(Id).orElseThrow(
                () -> new NotFoundException("Id do cliente não localizado ou inexistente")
        );
        clienteRepository.deleteById(Id);
    }
}
