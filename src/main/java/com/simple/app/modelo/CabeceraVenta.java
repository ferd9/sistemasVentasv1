package com.simple.app.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author ediso
 */
@Entity
@Table(name="tb_cabecera_venta")
public class CabeceraVenta implements Serializable {
    
    //Atributos
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCabeceraVenta")
    private Integer idCabeceraventa;
    @Column
    private int idCliente;
    @Column
    private double valorPagar;
    @Column
    private LocalDate fechaVenta;
    @Column
    private int estado;
    
    //constructor
    public CabeceraVenta(){
        this.idCabeceraventa = 0;
        this.idCliente = 0;
        this.valorPagar = 0.0;        
        this.estado = 0;
    }
    
     //constructor sobrecargado

    public CabeceraVenta(int idCliente, double valorPagar, LocalDate fechaVenta, int estado) {        
        this.idCliente = idCliente;
        this.valorPagar = valorPagar;
        this.fechaVenta = fechaVenta;
        this.estado = estado;
    }
    
    //get and set 

    public Integer getIdCabeceraventa() {
        return idCabeceraventa;
    }

    public void setIdCabeceraventa(Integer idCabeceraventa) {
        this.idCabeceraventa = idCabeceraventa;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    //toString 

    @Override
    public String toString() {
        return "CabeceraVenta{" + "idCabeceraventa=" + idCabeceraventa + ", idCliente=" + idCliente + ", valorPagar=" + valorPagar + ", fechaVenta=" + fechaVenta + ", estado=" + estado + '}';
    }
    
    
    
}
