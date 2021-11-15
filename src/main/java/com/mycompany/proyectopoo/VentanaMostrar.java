/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo;

import java.awt.Color;
import java.util.ArrayList;
/**
 *
 * @author Vicente
 */
public class VentanaMostrar extends javax.swing.JFrame {

    private String[] aux = new String[10];
    private String parametro;
    private ControladorVentanaMostrar controlador;
    
    public VentanaMostrar(String parametroAMostrar) {
        initComponents();
        parametro = parametroAMostrar;
        jLabelTitulo.setText("Mostrar "+parametro+ " de un Curso específico");
        jLabelParametro.setText(parametro);
        jLabelError.setForeground(Color.red);
        
        controlador = new ControladorVentanaMostrar();
        jListaCursos.setListData(controlador.getLista());
        aux[1]="";
        jListaParametro.setListData(aux);
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // si la plicación se cierra con la X no dentendrá el programa
        this.setResizable(false); // no se podrá cambiar el tamaño de la ventana cuando se ejecute
        this.setLocationRelativeTo(null); // la ventana aparecerá centrada
        
    }

    private VentanaMostrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        jLabelCurso = new javax.swing.JLabel();
        jCampoTexto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListaCursos = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListaParametro = new javax.swing.JList<>();
        jBotonBuscar = new javax.swing.JButton();
        jLabelError = new javax.swing.JLabel();
        jLabelParametro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelTitulo.setText("Título");

        jLabelCurso.setText("Nombre Curso");

        jListaCursos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListaCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListaCursosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListaCursos);

        jListaParametro.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListaParametro);

        jBotonBuscar.setText("Mostrar");
        jBotonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonBuscarActionPerformed(evt);
            }
        });

        jLabelParametro.setText("Parametro");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelError, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCampoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBotonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(61, 61, 61)
                        .addComponent(jLabelParametro)
                        .addGap(0, 58, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCurso)
                    .addComponent(jCampoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBotonBuscar)
                    .addComponent(jLabelParametro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelError, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Botón mostrar presionado
    private void jBotonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonBuscarActionPerformed
        
        if (controlador.validarCursoIngresado(jCampoTexto.getText()))
        {
            jLabelError.setText("");
            jListaParametro.setListData(controlador.botonPresionado(jCampoTexto.getText(),parametro));
        }else
        {
            aux[1]="";
            jListaParametro.setListData(aux);
            jLabelError.setText("Curso no encontrado");
        }
        
    }//GEN-LAST:event_jBotonBuscarActionPerformed

    // Evento de seleccionar con el ratón (clickear) un curso de la jListaCurso, éste hará que en el campo de texto 
    // donde se escribe el nombre del curso, se coloque el que ha sido seleccionado con el ratón.
    private void jListaCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListaCursosMouseClicked
        jCampoTexto.setText(jListaCursos.getSelectedValue());
    }//GEN-LAST:event_jListaCursosMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaMostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaMostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaMostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaMostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaMostrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBotonBuscar;
    private javax.swing.JTextField jCampoTexto;
    private javax.swing.JLabel jLabelCurso;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JLabel jLabelParametro;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JList<String> jListaCursos;
    private javax.swing.JList<String> jListaParametro;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
