/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
/**
 *
 * @author Vicente
 */
public class Unidad {
    
    //Instancias de Clase
    private String nombreUnidad;
    private ArrayList<String> preguntasUnidad;
    private String fechaEvaluacion;
    private double[] listaNotas;
    private ArrayList<String> listaRutAlumnos;
    BufferedReader lector = new BufferedReader(new InputStreamReader (System.in));
   
    //Constructor
    public Unidad(String nombreUnidad,ArrayList<String> listaRutAlumnos) {
        this.nombreUnidad = nombreUnidad;
        this.preguntasUnidad = new ArrayList<>();
        this.listaRutAlumnos = new ArrayList<>();
        this.listaRutAlumnos.addAll(listaRutAlumnos);
        Calendar c = Calendar.getInstance();
        this.fechaEvaluacion = Integer.toString(c.get(Calendar.DATE))+"/"+Integer.toString(c.get(Calendar.MONTH))+"/"+ Integer.toString(c.get(Calendar.YEAR));
    }
    
    //Metodos
    //Metodos de llenado
    
    public void addPregunta(String pregunta)
    {
        //se llenan las preguntas con ""pregunta "+(i+1)" solamente para mostrar que se guardan
        this.preguntasUnidad.add(pregunta);
    }
    
    public ArrayList<String> getPreguntas()
    {
        ArrayList<String> listaPreguntas = new  ArrayList<>();
        for(int i = 0 ; i< this.preguntasUnidad.size(); i++)
            listaPreguntas.add(this.preguntasUnidad.get(i));
        return listaPreguntas;
    }
    
    public void llenarNotas() throws IOException
    {
        
        int posibleNota;
        this.listaNotas = new double[this.listaRutAlumnos.size()];
        for(int i = 0 ; i < this.listaNotas.length ; i++)
        {
            //se llenan las notas del alumno (i) con una nota random y se guardan en la lista de notas en 
            //la misma posicion del alumno
            do
            {
                // notas de prueba, luego se llenaran correctamente
                posibleNota=(int) (Math.random() * 6 + 1);
            }while((posibleNota > 7) || (posibleNota <1) );
            this.listaNotas[i] = posibleNota;
        }
    }
    public void mostrarNotas(String nombreCurso)
    {
        System.out.println("Notas de Unidad " + this.nombreUnidad + " del curso "+ nombreCurso +" con fecha de " + this.fechaEvaluacion+":");
        for(int i = 0 ; i < this.listaNotas.length ; i++)
            System.out.println(this.listaRutAlumnos.get(i) +": "+ this.listaNotas[i] );
        
        System.out.println("\n");
    }
    
    public void mostrarArrayListAlumnos()
    {
        for (int i = 0 ; i < this.listaRutAlumnos.size(); i++)
            System.out.println("Alumno numero " + (i+1) + ": " + this.listaRutAlumnos.get(i));       
    }
    
    //Getters y Setters
    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public String getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(String fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }
    public ArrayList<String> getListaRutAlumnos() {
        return listaRutAlumnos;
    }


    public double[] getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(int[] listaNotas) {
        for (int i=0; i < listaNotas.length; i++){
            this.listaNotas[i] = (double)listaNotas[i];
        }
    }
}