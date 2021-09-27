package jm.security.example.service;

import jm.security.example.model.Role;
import jm.security.example.repo.RoleRepo;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepo roleRepo;

    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<Role> findAll() {
        return roleRepo.findAll();
    }


    @PostConstruct
    private void addRoles(){
        roleRepo.save(new Role(1L,"ROLE_ADMIN"));
        roleRepo.save(new Role(2L,"ROLE_USER"));
    }
}
