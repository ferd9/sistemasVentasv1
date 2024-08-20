/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simple.app.vistas.custom;

import com.simple.app.vistas.interfaces.TableActionEvent;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author Lynkos
 */
public class DeleteCellEditor extends DefaultCellEditor {
    
     private TableActionEvent event;

    public DeleteCellEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        DeletePanel action = new DeletePanel();
        action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());        
        return action;
    }
    
}
