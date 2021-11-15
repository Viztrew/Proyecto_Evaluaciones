/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo;

import static com.mycompany.proyectopoo.ManejoDeCSV.leerParametroCSV;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Vicente
 */
public class ControladorVentanaAgregar {
    private static String[] listaCursos;
    private static int res;
    public ControladorVentanaAgregar()
    {
        ArrayList<String> lista = leerParametroCSV("Curso","Cursos.csv");
        this.listaCursos = new String[lista.size()];
        for (int i =0 ; i<lista.size(); i++)
        {
            listaCursos[i] = lista.get(i);
        }
    }
    public boolean botonPresionado(String nombreCurso)
    {
        res = JOptionPane.showConfirmDialog(null, "¿Agregar Curso "+nombreCurso+"?","Confirmar", JOptionPane.YES_NO_OPTION );
        if ( res == JOptionPane.YES_OPTION )
        {
            return true;
        }else
        {
            if( res == JOptionPane.NO_OPTION ) return false;
        }
        return false;
    }
    
    public static boolean validarCursoIngresado(String nombreCurso)
    {
        for (int i = 0 ; i < listaCursos.length ; i++)
        {
            if (nombreCurso.toLowerCase().equals(listaCursos[i].toLowerCase()))
            {
                return false;
            }
        }
        return true;
    }
           
    public String[] getLista()
    {
        String[] lista = new String[this.listaCursos.length];
        for (int i = 0 ; i < this.listaCursos.length ; i++)
        {
            lista[i]=this.listaCursos[i];
        }
        return lista;
    }
}
