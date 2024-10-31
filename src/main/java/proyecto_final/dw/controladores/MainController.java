package proyecto_final.dw.controladores;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import proyecto_final.dw.controladores.request.CrearUsuarioDTO;
import proyecto_final.dw.modelos.Rol;
import proyecto_final.dw.modelos.Usuario;
import proyecto_final.dw.repositorios.RolRepository;
import proyecto_final.dw.repositorios.UsuarioRepository;
import proyecto_final.dw.servicios.UsuarioService;

@RestController

public class MainController {

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    @Autowired
    private RolRepository rolRepositorio;


    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordEncoder passwordEncoder;

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

                String hashedPassword = passwordEncoder.encode(crearUsuarioDTO.getPassword());

        // Crea el usuario
        Usuario usuario = Usuario.builder()
                .username(crearUsuarioDTO.getUsername())
                .password(hashedPassword)
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


}
