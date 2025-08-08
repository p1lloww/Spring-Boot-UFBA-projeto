    package com.tomorrowproject.restaurante_api.repository;

    import com.tomorrowproject.restaurante_api.entity.Role;
    import com.tomorrowproject.restaurante_api.entity.User;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.util.UUID;

    @Repository
    public interface RoleRepository extends JpaRepository<Role, Long> {

        Role findByName(String name);
    }
