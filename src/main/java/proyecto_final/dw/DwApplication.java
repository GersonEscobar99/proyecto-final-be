package proyecto_final.dw;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import proyecto_final.dw.modelos.ERole;
import proyecto_final.dw.modelos.Rol;
import proyecto_final.dw.modelos.Usuario;
import proyecto_final.dw.repositorios.UsuarioRepositorio;

@SpringBootApplication
public class DwApplication {

    public static void main(String[] args) {
        SpringApplication.run(DwApplication.class, args);
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Bean
    CommandLineRunner init() {
        return args -> {
            Usuario usuario1 = Usuario.builder()
                    .enabled(true) 
                    .email("ejemplo@correo.com")
                    .password(passwordEncoder.encode("1234"))
                    .username("ejemploUsuario")
                    .nombre("NombreEjemplo")
                    .apellido("ApellidoEjemplo")
                    .telefono("123456789")
                    .roles(List.of(Rol.builder()
                            .name(ERole.valueOf(ERole.ADMIN.name()))
                            .build())) 
                    .build();

            Usuario usuario2 = Usuario.builder()
                    .enabled(true) 
                    .email("otroejemplo@correo.com")
                    .password(passwordEncoder.encode("1234"))
                    .username("otroUsuario")
                    .nombre("OtroNombre")
                    .apellido("OtroApellido")
                    .telefono("987654321")
                    .roles(List.of(Rol.builder()
                            .name(ERole.valueOf(ERole.USER.name()))
                            .build())) 
                    .build();

            Usuario usuario3 = Usuario.builder()
                    .enabled(true) 
                    .email("tercero@correo.com")
                    .password(passwordEncoder.encode("1234"))
                    .username("tercerUsuario")
                    .nombre("TercerNombre")
                    .apellido("TercerApellido")
                    .telefono("456789123")
                    .roles(List.of(Rol.builder()
                            .name(ERole.valueOf(ERole.USER.name()))
                            .build())) 
                    .build();

            usuarioRepositorio.save(usuario1);
            usuarioRepositorio.save(usuario2);
            usuarioRepositorio.save(usuario3);

            //bienvenidos
        };
    }
}
