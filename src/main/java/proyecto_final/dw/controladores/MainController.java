package proyecto_final.dw.controladores;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import proyecto_final.dw.controladores.request.CrearUsuarioDTO;
import proyecto_final.dw.modelos.ERole;
import proyecto_final.dw.modelos.Rol;
import proyecto_final.dw.modelos.Usuario;
import proyecto_final.dw.repositorios.RolRepositorio;
import proyecto_final.dw.repositorios.UsuarioRepositorio;
import proyecto_final.dw.servicios.UsuarioServicio;

@RestController
public class MainController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/hello")
    public String hello() {
        return "Hola no Seguro";
    }

    @GetMapping("/helloSecured")
    public String helloSecure() {
        return "Hola Seguro";
    }



    @PostMapping("/createUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@Valid @RequestBody CrearUsuarioDTO crearUsuarioDTO) {
        // Busca los roles en la base de datos a partir de los nombres que vienen en el DTO
        List<Rol> roles = crearUsuarioDTO.getRoles().stream()
                .map(roleName -> rolRepositorio.findByNombreRol(roleName)
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + roleName)))
                .collect(Collectors.toList());

        // Crea el usuario
        Usuario usuario = Usuario.builder()
                .username(crearUsuarioDTO.getUsername())
                .password(crearUsuarioDTO.getPassword())
                .nombre(crearUsuarioDTO.getNombre())
                .apellido(crearUsuarioDTO.getApellido())
                .email(crearUsuarioDTO.getEmail())
                .telefono(crearUsuarioDTO.getTelefono())
                .enabled(true)
                .roles(new HashSet<>(roles)) // Asegúrate de que roles sea un Set
                .build();

        usuarioRepositorio.save(usuario);

        return ResponseEntity.ok(usuario);
    }


    @PostMapping("/create/user")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario,
                                                @RequestParam Long idDepartamento,
                                                @RequestParam Long idHorario,
                                                @RequestParam Set<Long> idsRoles) {
        try {
            Usuario nuevoUsuario = usuarioServicio.crearUsuario(usuario, idDepartamento, idHorario, idsRoles);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/deleteUser")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@RequestParam String id) {
        usuarioRepositorio.deleteById(Long.parseLong(id));
        return "Usuario eliminado";
    }



}
