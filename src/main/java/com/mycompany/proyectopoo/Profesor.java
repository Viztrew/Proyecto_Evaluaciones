package com.mycompany.proyectopoo;
import java.io.*;
import java.util.ArrayList;

public class Profesor extends Persona{
  
  //Instancia de Clase
  private ArrayList<Double> listaNotasProfe;
  
 //Constructor
  public Alumno(String rutPersona, ArrayList<Double> listaNotasProfe){
    super(rutPersona);
    this.listaNotasProfe = new ArrayList<>();
    this.listaNotasProfe.addAll(listaNotasProfe);
  }
  
  //metodos (hacer getters y setter en el netbeans)
      public ArrayList<Double> getListaNotasProfe(){
        return listaNotasProfe;
    }

    public void setListaNotasAlumno(ArrayList<Double> listaNotasProfe) {
        this.listaNotasProfe = listaNotasProfe;
    }

    

    @Override //debiese estar aca en vez de en alumno?
    public void aprobadosCurso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override //debiese estar aca en vez de alumno?
    public void reprobadosCurso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
  
}
