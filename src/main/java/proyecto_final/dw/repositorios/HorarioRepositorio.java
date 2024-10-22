package proyecto_final.dw.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto_final.dw.modelos.Horario;

public interface HorarioRepositorio extends JpaRepository<Horario, Long> {
}
