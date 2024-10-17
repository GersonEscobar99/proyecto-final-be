package proyecto_final.dw.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto_final.dw.modelos.Rol;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long> {
}
