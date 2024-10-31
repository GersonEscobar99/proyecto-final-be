package proyecto_final.dw.controladores;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import proyecto_final.dw.dtos.UsuarioDTO;
import proyecto_final.dw.modelos.Usuario;
import proyecto_final.dw.servicios.UsuarioService;


@RestController
@RequestMapping("api/usuarios")
@CrossOrigin("http://localhost:4200")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;


    @PostMapping("/nuevo")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario nuevoUsuario = usuarioService.crearUsuarioConRol(usuarioDTO);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id,
                                                        @RequestBody Usuario usuario,
                                                        @RequestParam Long idDepartamento,
                                                        @RequestParam Long idHorario,
                                                        @RequestParam Set<Long> idsRoles) {
        try {
            UsuarioDTO usuarioActualizadoDTO = usuarioService.actualizarUsuario(id, usuario, idDepartamento, idHorario, idsRoles);
            return new ResponseEntity<>(usuarioActualizadoDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    
}
