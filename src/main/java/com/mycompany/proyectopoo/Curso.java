/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Vicente
 */
public class Curso {
    //Instancias de clase
    private String nombreCurso;
    private ArrayList<Asignatura> listaAsignaturas;
    private ArrayList<String> listaRutAlumnos;
    private HashMap<String,ArrayList<String>> mapaNotasAlumnos;
    
    //Constructor
    public Curso(String nombreCurso, ArrayList<String>listaRutAlumnos, String [] asignaturasCurso , String [] unidadesAsigUnidos) {
        this.nombreCurso = nombreCurso;
        this.mapaNotasAlumnos = new HashMap<>();
        
        this.listaRutAlumnos = new ArrayList<>();
        this.listaRutAlumnos.addAll(listaRutAlumnos);
        
        this.listaAsignaturas = new ArrayList<>();
        for (int i = 0 ; i < asignaturasCurso.length ; i++){
            String[] unidadesAsigSeparados = unidadesAsigUnidos[i].split(",");
            Asignatura asig = new Asignatura(asignaturasCurso[i],unidadesAsigSeparados,this.listaRutAlumnos);
            this.listaAsignaturas.add(asig);
        }
    } 
    
    //Metodos
    public void addPreguntaAsig(String nombreAsig, String nombreUnidad, String pregunta)
    {
        for(int j = 0; j < this.listaAsignaturas.size(); j++ )
        {
            if((this.listaAsignaturas.get(j).getNombreAsignatura()).toLowerCase().equals(nombreAsig.toLowerCase()))
                this.listaAsignaturas.get(j).addPreguntaUnidad(nombreUnidad,pregunta);
        }
    }
    
    public void guardarNotasCurso(String rut, ArrayList<String> notas)
    {
        this.mapaNotasAlumnos.put(rut, notas);
    }
    
     public void guardarNotasCurso(ArrayList<String> notas, String rut)
    {
        this.mapaNotasAlumnos.put(rut, notas);
    }
    
    public ArrayList<String> getListaRuts ()
    {
        ArrayList <String> listaRuts = new ArrayList <String>();
        for (int i = 0 ; i < this.listaRutAlumnos.size() ; i++)
            listaRuts.add(this.listaRutAlumnos.get(i));
        return listaRuts;
    }
    public ArrayList<String> getListaNombresAsig ()
    {
        ArrayList <String> listaNombresAsig = new ArrayList <String>();
        for (int i = 0 ; i < this.listaAsignaturas.size() ; i++)
            listaNombresAsig.add(this.listaAsignaturas.get(i).getNombreAsignatura());
        return listaNombresAsig;
    }
    public ArrayList<String> getListaNombresUnidades(String nombreAsignatura)
    {
        ArrayList <String> listaNombresUnidades = new ArrayList <String>();
        for (int i = 0 ; i < this.listaAsignaturas.size() ; i++){
            if (nombreAsignatura.equals(this.listaAsignaturas.get(i).getNombreAsignatura()))
                listaNombresUnidades = this.listaAsignaturas.get(i).getNombreUnidades();
        }
        return listaNombresUnidades;
    }
    
    public ArrayList<String> getListaPreguntasAsig(String nombreAsig, String nombreUnidad) {
        ArrayList <String> listaPreguntasUnidades = new ArrayList <String>();
        for(int j = 0; j < this.listaAsignaturas.size(); j++ )
        {
            if((this.listaAsignaturas.get(j).getNombreAsignatura()).toLowerCase().equals(nombreAsig.toLowerCase()))
            {
                listaPreguntasUnidades = this.listaAsignaturas.get(j).getListaPreguntasUnidad(nombreUnidad);
            }
        }
        return listaPreguntasUnidades;
    }
    //Getters y Setters
    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    
}
