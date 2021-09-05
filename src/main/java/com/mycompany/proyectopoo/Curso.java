/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectopoo;
import java.io.*;
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
    BufferedReader lectorTeclado = new BufferedReader(new InputStreamReader (System.in));
    
    //Constructor
    public Curso(String nombreCurso,ArrayList<String>listaRutAlumnos) {
        this.nombreCurso = nombreCurso;
        this.listaAsignaturas = new ArrayList<>();
        this.listaRutAlumnos = listaRutAlumnos;
        this.mapaNotasAlumnos = new HashMap<>();
    }
    
    //Metodos
    //Metodos de llenado
    public void llenarCurso(ArrayList<String> listaAsig,ArrayList<String> listaUnid)  throws IOException
    {
            /*como las asignaturas y las unidades se guardan de manera diferente en el csv se deben separan en partes
            //las asignaturas se separan una vez por comas y las unidades se separan dos veces, la primera por espacios
              la segunda por comas*/
        String asignaturasJuntas = listaAsig.get(0);  
        String[] asignaturasSeparadas = asignaturasJuntas.split(",");
        String temasDeUnidadJuntos = listaUnid.get(0);
        String[] temasUnidadesJuntosPorComas = temasDeUnidadJuntos.split(" ");
        
        for (int i = 0; i < asignaturasSeparadas.length ; i++)
        {
            String[] temasUnidadesSeparados = temasUnidadesJuntosPorComas[i].split(",");
            
            //Se crea el objeto asig y se guarda en la listaAsignaturas
            Asignatura asig = new Asignatura(asignaturasSeparadas[i],temasUnidadesSeparados,this.listaRutAlumnos);
            this.listaAsignaturas.add(asig);
        }
    }
    public void guardarNotasCurso(String rut, ArrayList<String> notas)
    {
        this.mapaNotasAlumnos.put(rut, notas);
    }
    
    //Metodos de impresion
    public void mostrarCurso()
    {
            //se muestra toda la información del objeto curso
        System.out.println(" ");
        System.out.println("Nombre del curso: " + this.nombreCurso);
        System.out.println("Alumnos de " + this.nombreCurso + ": ");
        mostrarArrayListAlumnos();
        
        for (int i = 0 ; i < this.listaAsignaturas.size() ; i++)
        {
            System.out.println("Asignatura numero " + (i+1) + ": " + this.listaAsignaturas.get(i).getNombreAsignatura());
            this.listaAsignaturas.get(i).mostrarListaUnidades();
        }
        System.out.println(" ");
    }
    
    public void mostrarArrayListAlumnos()
    {
            //se muestra el ArrayList de los rut de los alumnos
        for (int i = 0 ; i < this.listaRutAlumnos.size(); i++)
            System.out.println("Alumno numero " + (i+1) + ": " + this.listaRutAlumnos.get(i));
    }
    
    
    
    //Getters y Setters
    public ArrayList<String> getListaRutAlumnos() {
        return listaRutAlumnos;
    }

    public void setListaRutAlumnos(ArrayList<String> listaRutAlumnos) {
        this.listaRutAlumnos = listaRutAlumnos;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public ArrayList<Asignatura> getListaAsignaturas() {
        return listaAsignaturas;
    }

    public void setListaAsignaturas(ArrayList<Asignatura> listaAsignaturas) {
        this.listaAsignaturas = listaAsignaturas;
    }

    public HashMap<String, ArrayList<String>> getNotasAlumnos() {
        return mapaNotasAlumnos;
    }

    public void setNotasAlumnos(HashMap<String, ArrayList<String>> notasAlumnos) {
        this.mapaNotasAlumnos = notasAlumnos;
    }
    
}