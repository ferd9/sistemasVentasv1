/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simple.app.vistas.models;

import com.simple.app.modelo.DetalleVenta;
import com.simple.app.modelo.Producto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lynkos
 */
public class DetalleVentaTableModel extends AbstractTableModel {
    
    private List<DetalleVenta> listaDetalleVenta;
    private List<Producto> listaProducto;
    private final String[] nombreColumnas = {"Nro", "Nombre", "Cantidad", "P. Unitario", "Subtotal", "Descuento", "Impuesto", "Total a pagar", "Accion"};

    public DetalleVentaTableModel(List<DetalleVenta> listaDetalleVenta, List<Producto> listaProducto) {
        this.listaDetalleVenta = listaDetalleVenta;
        this.listaProducto = listaProducto;
    }

    @Override
    public int getRowCount() {
        return listaDetalleVenta.size();
    }

    @Override
    public int getColumnCount() {
        return nombreColumnas.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return nombreColumnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DetalleVenta detalleVenta = this.listaDetalleVenta.get(rowIndex);
        Producto producto = this.listaProducto.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return producto.getNombre();
            case 2:
                return detalleVenta.getCantidad();
            case 3:
                return detalleVenta.getPrecioUnitario();
            case 4:
                return detalleVenta.getSubTotal();
            case 5:
                return detalleVenta.getDescuento();
            case 6:
                return detalleVenta.getIva();
            case 7:
                return detalleVenta.getTotalPagar();                           
            default:
                return null;
        }
    }   
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:    
                return Double.class;
            case 0:
                return Integer.class; 
            default:
                return String.class;
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Decide which cells should be editable
        return columnIndex == 8;
        //return false;
    }
    
    public void addDetalleVenta(DetalleVenta detalleVenta, Producto producto){
        this.listaDetalleVenta.add(detalleVenta);
        this.listaProducto.add(producto);
    }
    
    public void removeDetalleVenta(int rowIndex){
        this.listaDetalleVenta.remove(rowIndex);
        this.listaProducto.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    
    public DetalleVenta getProductoAt(int row){
        return this.listaDetalleVenta.get(row);
    }

    public List<DetalleVenta> getListaDetalleVenta() {
        return listaDetalleVenta;
    }

    public void setListaDetalleVenta(List<DetalleVenta> listaDetalleVenta) {
        this.listaDetalleVenta = listaDetalleVenta;
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }    
    
}
