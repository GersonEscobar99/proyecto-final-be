package proyecto_final.dw.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto_final.dw.modelos.Departamento;

public interface DepartamentoRepositorio extends JpaRepository<Departamento, Long> {

}
