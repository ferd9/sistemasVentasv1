/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simple.app.vistas.models;

/**
 *
 * @author Lynkos
 */
import com.simple.app.modelo.Producto;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProductoTableModel extends AbstractTableModel {
    private List<Producto> productos;
    private String[] columnNames = {"ID", "Nombre", "Cantidad", "Precio", "Descripción", "% IVA", "ID Categoría", "Estado", "Accion"};

    public ProductoTableModel(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public int getRowCount() {
        return productos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto producto = productos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return producto.getIdProducto();
            case 1:
                return producto.getNombre();
            case 2:
                return producto.getCantidad();
            case 3:
                return producto.getPrecio();
            case 4:
                return producto.getDescripcion();
            case 5:
                return producto.getPorcentajeIva();
            case 6:
                return producto.getIdCategoria();
            case 7:
                return producto.getEstado();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 2:
            case 5:
            case 6:
            case 7:
                return Integer.class;
            case 3:
                return Double.class;
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

    public void addProducto(Producto producto) {
        productos.add(producto);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void removeProducto(int rowIndex) {
        productos.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Producto getProductoAt(int rowIndex) {
        return productos.get(rowIndex);
    }
}
