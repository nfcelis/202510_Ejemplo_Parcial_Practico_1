package co.edu.uniandes.dse.parcialprueba.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class PacienteEntity extends BaseEntity {

    private String nombre;
    private Integer edad;
    private Integer celular;
    private String correo;


    @OneToMany(mappedBy = "paciente")
    private List<ConsultaMedicaEntity> consultas = new ArrayList<>();
    

}