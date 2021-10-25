package com.mycompany.proyectopoo;
import java.io.*;
import java.util.Scanner;
public  class Aprobado extends AlumnoMatricula {
  
  //Instancia de Clase
    private boolean beca;
    private boolean rendirEvaluacionFinal;
  //Constructor
    public Aprobado(String rutPersona,double promedioFinal, boolean beca, boolean rendirEvaluacionFinal){
        super (rutPersona,promedioFinal);
        this.beca = beca;
        this.rendirEvaluacionFinal=rendirEvaluacionFinal;
    }

    public void evaluacionFinal(double notaEvaluacion){
        if(notaEvaluacion >= 6.5)
        {
            setBeca(true);
            setRendirEvaluacionFinal(false);
        }else
        {
            setBeca(false);
            setRendirEvaluacionFinal(false);
        }
    }
  
    public boolean getBeca() {
        return beca;
    }

    public void setBeca(boolean beca) {
      this.beca = beca;
    }
    public boolean getRendirEvaluacionFinal() {
        return rendirEvaluacionFinal;
    }
    
    public void setRendirEvaluacionFinal(boolean rendirEvaluacionFinal) {
        this.rendirEvaluacionFinal = rendirEvaluacionFinal;
    }
    
}
