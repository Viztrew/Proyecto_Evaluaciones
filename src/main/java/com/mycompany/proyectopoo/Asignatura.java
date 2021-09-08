
package com.mycompany.proyectopoo;
import java.io.*;
import java.util.ArrayList;

public class Asignatura {
    //Instancias de Clase
    private String nombreAsignatura;
    private ArrayList<Unidad> listaUnidades;
    private ArrayList<String> listaRutAlumnos;
    
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
    
    public void addPreguntaUnidad(String nombreUnidad, String pregunta)
    {
        for(int k = 0; k < this.listaUnidades.size(); k++ )
        {
            if((this.listaUnidades.get(k).getNombreUnidad()).toLowerCase().equals(nombreUnidad.toLowerCase()))
            this.listaUnidades.get(k).addPregunta(pregunta);
        }
    }
    
    
    public ArrayList <String> getNombreUnidades()
    {
        ArrayList <String> listaNombresUnidades= new ArrayList <String>();
        for (int i = 0 ; i < this.listaUnidades.size() ; i++)
            listaNombresUnidades.add(this.listaUnidades.get(i).getNombreUnidad());
        return listaNombresUnidades;
    }
    
    public ArrayList<String> getListaPreguntasUnidad(String nombreUnidad) 
    {
        ArrayList <String> listaPreguntas= new ArrayList <String>();
        for(int k = 0; k < this.listaUnidades.size(); k++ )
        {
            if((this.listaUnidades.get(k).getNombreUnidad()).toLowerCase().equals(nombreUnidad.toLowerCase()))
            listaPreguntas = this.listaUnidades.get(k).getPreguntas();
        }
        return listaPreguntas;
    }
    
    //Getters y Setters
    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    


}