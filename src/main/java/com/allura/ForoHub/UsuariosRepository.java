package com.allura.ForoHub;

import com.allura.ForoHub.users.UsuariosTable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
public interface UsuariosRepository extends JpaRepository<UsuariosTable,Long> {
    UserDetails findByEmail(String username);
}
