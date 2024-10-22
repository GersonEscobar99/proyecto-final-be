package proyecto_final.dw.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto_final.dw.modelos.Departamento;
import proyecto_final.dw.modelos.Horario;
import proyecto_final.dw.modelos.Usuario;
import proyecto_final.dw.repositorios.DepartamentoRepositorio;
import proyecto_final.dw.repositorios.HorarioRepositorio;
import proyecto_final.dw.repositorios.UsuarioRepositorio;

import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private DepartamentoRepositorio departamentoRepositorio;

    @Autowired
    private HorarioRepositorio horarioRepositorio;

    // Método para crear un nuevo usuario
    public Usuario crearUsuario(Usuario usuario, Long idDepartamento, Long idHorario, Set<Long> idsRoles) {
        // Buscar departamento y horario
        Optional<Departamento> departamentoOpt = departamentoRepositorio.findById(idDepartamento);
        Optional<Horario> horarioOpt = horarioRepositorio.findById(idHorario);

        if (departamentoOpt.isPresent() && horarioOpt.isPresent()) {
            usuario.setDepartamento(departamentoOpt.get());
            usuario.setHorario(horarioOpt.get());

            // Aquí debes manejar la asignación de roles
            // Puedes implementar lógica para obtener roles a partir de los ids proporcionados.
            // Por simplicidad, este código asume que tienes un método `obtenerRolesPorIds`.
            // usuario.setRoles(obtenerRolesPorIds(idsRoles));

            return usuarioRepositorio.save(usuario);
        } else {
            throw new RuntimeException("Departamento u horario no encontrados");
        }
    }
}

