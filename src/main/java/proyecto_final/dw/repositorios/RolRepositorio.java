package proyecto_final.dw.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto_final.dw.modelos.Rol;

import java.util.Optional;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long> {

    Optional<Rol> findByNombreRol(String nombreRol);
}
