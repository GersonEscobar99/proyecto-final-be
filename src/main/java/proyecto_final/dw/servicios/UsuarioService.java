package proyecto_final.dw.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import proyecto_final.dw.dtos.UsuarioDTO;
import proyecto_final.dw.modelos.Departamento;
import proyecto_final.dw.modelos.Horario;
import proyecto_final.dw.modelos.Rol;
import proyecto_final.dw.modelos.Usuario;
import proyecto_final.dw.repositorios.DepartamentoRepository;
import proyecto_final.dw.repositorios.HorarioRepository;
import proyecto_final.dw.repositorios.MarcajeRepository;
import proyecto_final.dw.repositorios.RolRepository;
import proyecto_final.dw.repositorios.UsuarioRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;



@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    DepartamentoRepository departamentoRepository;

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    MarcajeRepository marcajeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public Usuario crearUsuarioConRol(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefono(usuarioDTO.getTelefono());

        Rol rol = rolRepository.findById(usuarioDTO.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + usuarioDTO.getRolId()));

        Horario horario = horarioRepository.findById(usuarioDTO.getIdHorario())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado con ID: " + usuarioDTO.getIdHorario()));


        Departamento departamento = departamentoRepository.findById(usuarioDTO.getIdDepartamento())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado con ID: " + usuarioDTO.getIdDepartamento()));

        usuario.getRoles().add(rol);
        usuario.setHorario(horario);
        usuario.setDepartamento(departamento);

        return usuarioRepository.save(usuario);
    }


    public Optional<Usuario> obtenerUsuarioPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }
    


    public Optional<Usuario> obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }



    public UsuarioDTO actualizarUsuario(Long idUsuario, Usuario usuarioActualizado, Long idDepartamento, Long idHorario, Set<Long> idsRoles) {
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idUsuario));

        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setApellido(usuarioActualizado.getApellido());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        usuarioExistente.setTelefono(usuarioActualizado.getTelefono());

        Optional<Departamento> departamentoOpt = departamentoRepository.findById(idDepartamento);
        Optional<Horario> horarioOpt = horarioRepository.findById(idHorario);

        if (departamentoOpt.isPresent() && horarioOpt.isPresent()) {
            usuarioExistente.setDepartamento(departamentoOpt.get());
            usuarioExistente.setHorario(horarioOpt.get());

            Set<Rol> roles = new HashSet<>(rolRepository.findAllById(idsRoles));
            usuarioExistente.setRoles(roles);

            usuarioRepository.save(usuarioExistente);
            return convertirAUsuarioDTO(usuarioExistente);
        } else {
            throw new RuntimeException("Departamento o horario no encontrados");
        }
    }

    private UsuarioDTO convertirAUsuarioDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getRoles() != null && !usuario.getRoles().isEmpty() 
                    ? usuario.getRoles().iterator().next().getId() 
                    : null,
                usuario.getHorario() != null 
                    ? usuario.getHorario().getIdHorario() 
                    : null,
                usuario.getDepartamento() != null 
                    ? usuario.getDepartamento().getIdDepartamento() 
                    : null,
                usuario.getDepartamento() != null 
                    ? usuario.getDepartamento().getNombreDepartamento() 
                    : null
        );
    }
    
    public void eliminarUsuario(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(idUsuario);
    }




}
