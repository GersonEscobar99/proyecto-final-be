package proyecto_final.dw.modelos;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Horario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHorario;

    @Column(name = "hora_entrada", nullable = false)
    private LocalTime horaEntrada;

    @Column(name = "hora_salida", nullable = false)
    private LocalTime horaSalida;

    @Column(name = "tolerancia_entrada", nullable = false)
    private int toleranciaEntrada;

    @Column(name = "tolerancia_salida", nullable = false)
    private int toleranciaSalida;

    public Long getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
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

    public int getToleranciaEntrada() {
        return toleranciaEntrada;
    }

    public void setToleranciaEntrada(int toleranciaEntrada) {
        this.toleranciaEntrada = toleranciaEntrada;
    }

    public int getToleranciaSalida() {
        return toleranciaSalida;
    }

    public void setToleranciaSalida(int toleranciaSalida) {
        this.toleranciaSalida = toleranciaSalida;
    }
}
