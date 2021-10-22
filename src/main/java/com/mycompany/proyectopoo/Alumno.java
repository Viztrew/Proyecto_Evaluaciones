package com.mycompany.proyectopoo;
import java.io.*;
import java.util.ArrayList;

public class Alumno extends Persona{
  
  //Instancia de Clase
  private ArrayList<Double> listaNotasAlumno;
  
 //Constructor
  public Alumno(String rutPersona, ArrayList<Double> listaNotasAlumno){
    super(rutPersona);
    this.listaNotasAlumno = new ArrayList<>();
    this.listaNotasAlumno.addAll(listaNotasAlumno);
  }
  
  //metodos (hacer getters y setter en el netbeans)
      public ArrayList<Double> getListaNotasAlumno(){
        return listaNotasAlumno;
    }

    public void setListaNotasAlumno(ArrayList<Double> listaNotasAlumno) {
        this.listaNotasAlumno = listaNotasAlumno;
    }

    @Override
    public void promedioAlumno() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override //mover esto a clase profesor?
    public void aprobadosCurso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override //mover esto a clase profesor
    public void reprobadosCurso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
  
}
