package cl.sophic.sophic_cliente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
public class Cliente {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank(message = "El rut no puede estar vacío")
    private String rut;

    @Column
    @NotBlank(message = "El campo no puede estar vacío")
    private String nombre;

    @Column
    @NotBlank(message = "El campo no puede estar vacío")
    private String apellidos;

    @Column
    @NotBlank(message = "El campo no puede estar vacío")
    private String correo;

    @Column
    @NotBlank(message = "El campo no puede estar vacío")
    private String direccion;

    @Column
    @NotBlank(message = "El campo no puede estar vacío")
    private String telefono;


}
