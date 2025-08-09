package com.tomorrowproject.restaurante_api.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tomorrowproject.restaurante_api.DTO.cliente.ClienteDTO;
import com.tomorrowproject.restaurante_api.DTO.user.CreateUserDTO;
import com.tomorrowproject.restaurante_api.entity.Role;
import com.tomorrowproject.restaurante_api.entity.User;
import com.tomorrowproject.restaurante_api.repository.RoleRepository;
import com.tomorrowproject.restaurante_api.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

@Transactional
@RestController()
public class UserController {

      private final UserRepository userRepository;
      
      private final RoleRepository roleRepository;
      
      private final BCryptPasswordEncoder passwordEncoder;
      
      public UserController(UserRepository userRepository,
      RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.roleRepository = roleRepository;
            this.passwordEncoder = passwordEncoder;
      }

      @Operation(summary = "Cria um novo usuario", description = "Cria e salva um novo usuario com base no nome e na senha, este mesmo usuario tera o seu destino para ser um cliente ou admim")
      @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso",
            content = @Content(schema = @Schema(implementation = CreateUserDTO.class)))
      @ApiResponse(responseCode = "400", description = "Dados do usuario inválidos")
      @PostMapping("/users")
      public ResponseEntity<Void> newUser(@RequestBody CreateUserDTO dto) {

            var basicRole = roleRepository.findByName(Role.values.BASIC.name());

            var userFromDb = userRepository.findByUsername(dto.userName());

            if(userFromDb.isPresent()) {
                  throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
            }

            var user = new User();

            user.setUsername(dto.userName());
            user.setPassword(passwordEncoder.encode(dto.password()));
            user.setRoles(Set.of(basicRole));

            userRepository.save(user);

            return ResponseEntity.ok().build();
      
      }

      @Operation(summary = "Lista todos os usuarios", description = "Retorna uma lista completa de todos os usuarios cadastrados")
      @ApiResponse(responseCode = "200", description = "Lista de usuarios retornada com sucesso")
      @GetMapping("/users")
      @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
      public ResponseEntity<List<User>> listUsers() {
            var users = userRepository.findAll();
            return ResponseEntity.ok(users);
}
}