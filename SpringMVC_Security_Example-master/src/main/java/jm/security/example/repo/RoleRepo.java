package jm.security.example.repo;


import jm.security.example.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface RoleRepo extends JpaRepository<Role,Long> {

    }


