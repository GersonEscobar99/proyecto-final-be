package proyecto_final.dw;

import java.util.List;
import java.util.HashSet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import proyecto_final.dw.modelos.ERole;
import proyecto_final.dw.modelos.Rol;
import proyecto_final.dw.modelos.Usuario;
import proyecto_final.dw.repositorios.RolRepositorio;
import proyecto_final.dw.repositorios.UsuarioRepositorio;

@SpringBootApplication
public class DwApplication {

    public static void main(String[] args) {
        SpringApplication.run(DwApplication.class, args);
        
    }
    
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Autowired
//    UsuarioRepositorio usuarioRepositorio;
//
//    @Autowired
//    RolRepositorio rolRepositorio;
//
//    @Bean
//    CommandLineRunner init() {
//    return args -> {
//        // Crea o busca los roles en la base de datos
//        Rol adminRole = rolRepositorio.findByNombreRol("ADMIN")
//                .orElseThrow(() -> new RuntimeException("Rol no encontrado: ADMIN"));
//
//        Rol userRole = rolRepositorio.findByNombreRol("USER")
//                .orElseThrow(() -> new RuntimeException("Rol no encontrado: USER"));
//
//        // Crea usuarios
//        Usuario usuario1 = Usuario.builder()
//                .enabled(true)
//                .email("gerson@correo.com")
//                .password(passwordEncoder.encode("1234"))
//                .username("gmea")
//                .nombre("Gerson")
//                .apellido("Escobar")
//                .telefono("12345678")
//                .roles(new HashSet<>(List.of(adminRole))) // Usa el rol encontrado
//                .build();
//

//
//        usuarioRepositorio.save(usuario1);
//
//        // Mensaje de bienvenida
//        System.out.println("Usuarios iniciales creados");
//    };
//}

}
    




