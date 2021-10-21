package com.mycompany.proyectopoo;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Unidad {
    
    //Instancias de Clase
    private String nombreUnidad;
    private ArrayList<String> preguntasUnidad;
    private String fechaEvaluacion;
    private ArrayList<String> listaRutAlumnos;
    private HashMap<String,Double> mapaNotasUnidad;
    BufferedReader lector = new BufferedReader(new InputStreamReader (System.in));
   
    //Constructor
    public Unidad(String nombreUnidad,ArrayList<String> listaRutAlumnos) {
        this.nombreUnidad = nombreUnidad;
        this.preguntasUnidad = new ArrayList<>();
        this.listaRutAlumnos = new ArrayList<>();
        this.listaRutAlumnos.addAll(listaRutAlumnos);
        Calendar c = Calendar.getInstance();
        this.fechaEvaluacion = Integer.toString(c.get(Calendar.DATE))+"/"+Integer.toString(c.get(Calendar.MONTH))+"/"+ Integer.toString(c.get(Calendar.YEAR));
        
        
        this.mapaNotasUnidad = new HashMap<>();/*
        for(int i = 0 ; i < this.listaRutAlumnos.size() ; i++)
        {
            this.mapaNotasUnidad.put(this.listaRutAlumnos.get(i), 0.0);
        }*/
    }
    
    //Metodos
    //Metodos de añadir, eliminar reemplazar
    
    public void addPregunta(String pregunta)
    {
        this.preguntasUnidad.add(pregunta);
    }
    
    public void addAlumnoUnidad(String rutAlumno)
    {
        
        this.listaRutAlumnos.add(rutAlumno);
    }
    public boolean deleteAlumno(String rutAlumno)
    {
        if (this.listaRutAlumnos.remove(rutAlumno))
        {
            this.mapaNotasUnidad.remove(rutAlumno);
            return true;
        }
        return false;
    }
    public boolean replaceAlumno(String rutOriginal, String rutNuevo)
    {
        if (this.listaRutAlumnos.contains(rutOriginal))
        {
            int  i = this.listaRutAlumnos.indexOf(rutOriginal);
            this.listaRutAlumnos.remove(rutOriginal);
            this.listaRutAlumnos.add(i,rutNuevo);
            this.mapaNotasUnidad.put(rutNuevo,this.mapaNotasUnidad.remove(rutOriginal));
            return true;
        }
        return false;
    }
    
    public ArrayList<String> getPreguntas()
    {
        ArrayList<String> listaPreguntas = new  ArrayList<>();
        for(int i = 0 ; i< this.preguntasUnidad.size(); i++)
            listaPreguntas.add(this.preguntasUnidad.get(i));
        return listaPreguntas;
    }
    public ArrayList<Double> getNotas()
    {
        ArrayList<Double> notas = new ArrayList<>();
        for(int i = 0 ; i< this.listaRutAlumnos.size(); i++)
            notas.add(this.mapaNotasUnidad.get(this.listaRutAlumnos.get(i)));
        return notas;
    }
    
    public String getNombreUnidad() {
        return nombreUnidad;
    }
    public double getNotaAlumno(String rutAlumno)
    {
        
        if (this.mapaNotasUnidad.get(rutAlumno)==null)
        {
            return -1.0;
        }else
        {
            return this.mapaNotasUnidad.get(rutAlumno);
        }
        
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
    
    // si la nota a ingresar es para inicializar la unidad, se admite el valor 0.0 que es el valor de una nota sin valor
    public void setNota(double nota, String rutAlumno, boolean inicializacion)
    {
        if ((nota >= 1.0 && nota <= 7.0)||(inicializacion)){
            this.mapaNotasUnidad.put(rutAlumno, nota);
        }else
        {
            //error de parametro no válido
        }
    }
}