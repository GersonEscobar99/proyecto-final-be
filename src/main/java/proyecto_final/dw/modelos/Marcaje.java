package proyecto_final.dw.modelos;

import jakarta.persistence.*;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Builder
@Table(name = "marcaje")
public class Marcaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMarcaje;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "fecha_marcaje", nullable = false)
    private LocalDate fechaMarcaje;

    @Column(name = "hora_entrada", nullable = false)
    private LocalTime horaEntrada;

    @Column(name = "hora_salida", nullable = false)
    private LocalTime horaSalida;

    @Column(name = "dentro_de_horario", nullable = false)
    private Boolean dentroDeHorario;

    public Long getIdMarcaje() {
        return idMarcaje;
    }

    public void setIdMarcaje(Long idMarcaje) {
        this.idMarcaje = idMarcaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaMarcaje() {
        return fechaMarcaje;
    }

    public void setFechaMarcaje(LocalDate fechaMarcaje) {
        this.fechaMarcaje = fechaMarcaje;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Boolean getDentroDeHorario() {
        return dentroDeHorario;
    }

    public void setDentroDeHorario(Boolean dentroDeHorario) {
        this.dentroDeHorario = dentroDeHorario;
    }
}
