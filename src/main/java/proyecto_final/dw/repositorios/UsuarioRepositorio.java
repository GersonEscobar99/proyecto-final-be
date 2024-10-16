package proyecto_final.dw.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto_final.dw.modelos.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}
