/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simple.app.vistas.models;

import com.simple.app.modelo.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Lynkos
 */
public class CategoriaComboModel implements ComboBoxModel<Categoria>{

    private List<Categoria> items;
    private Categoria selectedItem;    

    public CategoriaComboModel() {
        items = new ArrayList<>();
    }
    
    public void addCategorias(List<Categoria> items){
        this.items = items;
    }
    
    @Override
    public void setSelectedItem(Object anItem) {
        this.selectedItem = (Categoria) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return this.selectedItem;
    }

    @Override
    public int getSize() {
        return this.items.size();
    }

    @Override
    public Categoria getElementAt(int index) {
        return this.items.get(index);
    } 
    

    @Override
    public void addListDataListener(ListDataListener l) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        
    }
    
}
