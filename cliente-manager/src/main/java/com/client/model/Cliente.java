package com.client.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;


    /*
    * JPA utiliza los métodos getter y setter para acceder y modificar los valores de los
    *  campos de la clase cuando se está interactuando con la base de datos. Por ejemplo,
    * cuando se está guardando un objeto Cliente en la base de datos, JPA utilizará los
    * métodos setter para establecer los valores de los campos del objeto a partir de los
    * valores en la tabla de la base de datos. De manera similar, cuando se está
    * recuperando un objeto Cliente desde la base de datos, JPA utilizará los métodos
    * getter para obtener los valores de los campos del objeto y almacenarlos en la
    * tabla de la base de datos.
    * */

}
