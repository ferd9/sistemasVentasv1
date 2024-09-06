/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simple.app.documento.boletas;

/**
 *
 * @author Lynkos
 */



import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.simple.app.modelo.CabeceraVenta;
import com.simple.app.modelo.DetalleVenta;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.printing.PDFPageable;

public class BoletaVenta {

    public static void generarBoleta(String fileName, CabeceraVenta cabeceraVenta, 
                                     List<DetalleVenta> listaProductos) {
        try {
            // Crear el escritor del PDF
            PdfWriter writer = new PdfWriter(fileName);
            
            // Crear el documento PDF
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Título de la boleta
            document.add(new Paragraph("Boleta de Venta")
                    .setBold()
                    .setFontSize(18));
            
            // Información básica
            document.add(new Paragraph("Número de Boleta: " + cabeceraVenta.getIdCabeceraventa()));
            document.add(new Paragraph("Fecha: " + cabeceraVenta.getFechaVenta()));
            
            // Crear la tabla de productos
            float[] columnWidths = {1, 3, 1, 1};
            Table table = new Table(columnWidths);
            table.addHeaderCell("Código");
            table.addHeaderCell("Producto");
            table.addHeaderCell("Cantidad");
            table.addHeaderCell("Precio");

            // Agregar productos a la tabla
            for (DetalleVenta detalleVenta: listaProductos) {
                table.addCell(detalleVenta.getIdDetalleVenta().toString()); // Código
                table.addCell(String.valueOf(detalleVenta.getIdProducto())); // Producto
                table.addCell(String.valueOf(detalleVenta.getCantidad())); // Cantidad
                table.addCell(String.valueOf(detalleVenta.getPrecioUnitario())); // Precio                
                
            }
            document.add(table);

            // Total
            document.add(new Paragraph("Total: $" + cabeceraVenta.getValorPagar())
                    .setBold());

            // Cerrar el documento
            document.close();

            System.out.println("Boleta generada correctamente.");
            mostrarPDFEnDialog(fileName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarPDFEnDialog(String fileName) {
        try {
            // Cargar el archivo PDF
            File pdfFile = new File(fileName);
            PDDocument pdfDocument = Loader.loadPDF(pdfFile);
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);

            // Renderizar la primera página del PDF como imagen
            BufferedImage image = pdfRenderer.renderImage(0);

            // Mostrar el PDF en un JDialog
            ImageIcon icon = new ImageIcon(image);
            JLabel label = new JLabel(icon);

            JDialog dialog = new JDialog((JFrame) null,"Vista previa de Boleta de Venta", true);
            //dialog.setTitle("Vista previa de Boleta de Venta");
            dialog.getContentPane().add(new JScrollPane(label));
            dialog.setSize(600, 400);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            
                        // Crear el contenedor para la imagen del PDF y los botones
            JPanel panel = new JPanel(new BorderLayout());           
            
            panel.add(new JScrollPane(label), BorderLayout.CENTER);

            // Crear panel de botones para Imprimir y Guardar
            JPanel buttonPanel = new JPanel();
            JButton printButton = new JButton("Imprimir");
            JButton saveButton = new JButton("Guardar");

            // Acción del botón "Imprimir"
            printButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    imprimirBoleta(pdfFile);
                }
            });

            // Acción del botón "Guardar"
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    guardarBoleta(pdfFile);
                }
            });

            buttonPanel.add(printButton);
            buttonPanel.add(saveButton);

            // Añadir los botones y la imagen al JDialog
            panel.add(buttonPanel, BorderLayout.SOUTH);
            dialog.getContentPane().add(panel);

            dialog.setVisible(true);

            // Cerrar el documento PDF después de la visualización
            pdfDocument.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
        // Método para imprimir el archivo PDF
    public static void imprimirBoleta(File pdfFile) {
        try {
            PDDocument document = Loader.loadPDF(pdfFile);
            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

            if (printService != null) {
                PrinterJob printerJob = PrinterJob.getPrinterJob();
                printerJob.setPrintService(printService);
                printerJob.setPageable(new PDFPageable(document));
                printerJob.print();
            } else {
                System.out.println("No se encontró ninguna impresora predeterminada.");
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para guardar el archivo PDF en otra ubicación
    public static void guardarBoleta(File pdfFile) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Boleta de Venta");

        // Establecer archivo por defecto
        fileChooser.setSelectedFile(new File(pdfFile.getName()+".pdf"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                Files.copy(pdfFile.toPath(), fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Archivo guardado en: " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}