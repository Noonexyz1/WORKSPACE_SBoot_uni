package com.accounts.model;

import com.accounts.commons.enums.Moneda;

import jakarta.persistence.*;

import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_creation")
    @CreationTimestamp                          //crea la fecha de creacion automaticamente
    private LocalDateTime creacion;
    @Column(name = "date_update")
    @UpdateTimestamp
    private LocalDateTime actualizacion;
    private String numero;
    @Enumerated(EnumType.STRING)                //en la BD se va a guardar en formato String
    private Moneda moneda;
    private BigDecimal saldo = BigDecimal.ZERO; //solo es para inizializar
    @Column(name = "saldo_disponible")
    private BigDecimal saldoDisponible = BigDecimal.ZERO; //solo es para inizializar
    private boolean habilitado;
    @Column(name = "cliente_id")
    private Long clienteId;




}
