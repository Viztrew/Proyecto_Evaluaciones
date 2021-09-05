
package com.mycompany.proyectopoo;
import java.io.*;
import java.util.ArrayList;

public class Asignatura {
    //Instancias de Clase
    private String nombreAsignatura;
    private ArrayList<Unidad> listaUnidades;
    private ArrayList<String> listaRutAlumnos;
    BufferedReader lectorTeclado = new BufferedReader(new InputStreamReader (System.in));
    
    //Constructor
    public Asignatura(String nombreAsignatura,String[] temasUnidades,ArrayList<String> listaRutAlumnos) {
        this.nombreAsignatura = nombreAsignatura;
        this.listaUnidades = new ArrayList<>();
        for (int i = 0 ; i < temasUnidades.length ; i++)
        {
            Unidad uni = new Unidad(temasUnidades[i],listaRutAlumnos);
            this.listaUnidades.add(uni);
        }
    }
    
    //Metodos
    //Metodos de llenado
    
    public void llenarNotasUnidad() throws IOException
    {
        for (int i = 0 ; i < this.listaUnidades.size() ; i++)
            this.listaUnidades.get(i).llenarNotas();
    }
    
    public void llenarPreguntasUnidad(String nombreCurso) throws IOException
    {
        for (int i = 0 ; i < this.listaUnidades.size() ; i++)
            this.listaUnidades.get(i).llenarPreguntas(nombreCurso);
    }
    
    //Metodos de impresion
    public void mostrarListaUnidades()
    {
        for (int i = 0 ; i < this.listaUnidades.size() ; i++)
            System.out.println("Unidad "+ (i+1) + ": "+this.listaUnidades.get(i).getNombreUnidad());
    }
    public void mostrarListaUnidades(int cantAMostrar)
    {
        if (cantAMostrar <= this.listaUnidades.size() && cantAMostrar>0)
        {
            while(cantAMostrar-1>=0)
            {
                for (int i = 0 ; i < this.listaUnidades.size() ; i++)
                    System.out.println("Unidad "+ (i+1) + ": "+this.listaUnidades.get(i).getNombreUnidad());
            }
        }
    }
    
    
    //Getters y Setters
    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public ArrayList<Unidad> getListaUnidades() {
        return listaUnidades;
    }

    public void setListaUnidades(ArrayList<Unidad> listaUnidades) {
        this.listaUnidades = listaUnidades;
    }

    public ArrayList<String> getListaRutAlumnos() {
        return listaRutAlumnos;
    }

    public void setListaRutAlumnos(ArrayList<String> listaRutAlumnos) {
        this.listaRutAlumnos = listaRutAlumnos;
    }
    
}