package proyecto_final.dw.controladores;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
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
import proyecto_final.dw.repositorios.UsuarioRepositorio;

@RestController
public class MainController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

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

        List<Rol> roles = crearUsuarioDTO.getRoles().stream()
                .map(role -> Rol.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toList());

        Usuario usuario = Usuario.builder()
                .username(crearUsuarioDTO.getUsername())
                .password(crearUsuarioDTO.getPassword())
                .nombre(crearUsuarioDTO.getNombre())
                .apellido(crearUsuarioDTO.getApellido())
                .email(crearUsuarioDTO.getEmail())
                .telefono(crearUsuarioDTO.getTelefono())
                .enabled(true)
                .roles(roles)
                .build();

        usuarioRepositorio.save(usuario);

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/deleteUser")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@RequestParam String id) {
        usuarioRepositorio.deleteById(Long.parseLong(id));
        return "Usuario eliminado";
    }
}
