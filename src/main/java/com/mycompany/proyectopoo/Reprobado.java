package com.mycompany.proyectopoo;
import java.io.*;
import java.util.Scanner;
import org.apache.commons.math3.util.Precision;

public class  Reprobado extends AlumnoMatricula {  
  
  //Instancia de Clase
    private boolean situacionAcademica;
    private boolean rendirEvaluacionFinal;
  //Constructor
    
    public Reprobado(String rutPersona,double promedioFinal ,boolean situacionAcademica, boolean rendirEvaluacionFinal){
        super (rutPersona,promedioFinal);
        this.situacionAcademica = situacionAcademica;
        this.rendirEvaluacionFinal = rendirEvaluacionFinal;
    }
 
    public void evaluacionFinal(double notaEvaluacion){
        double notaFinal = notaEvaluacion*0.4 + this.getPromedioFinal()*0.6;
        notaFinal = Precision.round(notaFinal,1);
        setPromedioFinal(notaFinal);
        if(notaFinal >= 4.5){
            setSituacionAcademica(true);
            setRendirEvaluacionFinal(false);
        }else
        {
            setSituacionAcademica(false);
            setRendirEvaluacionFinal(false);
        }
    }
    
    
  //metodos (hacer getters y setter en el netbeans)
    public boolean getSituacionAcademica() {
        return situacionAcademica;
    }

    public boolean getRendirEvaluacionFinal() {
        return rendirEvaluacionFinal;
    }
    public void setSituacionAcademica(boolean situacionAcademica) {
        this.situacionAcademica = situacionAcademica;
    }
    
    public void setRendirEvaluacionFinal(boolean rendirEvaluacionFinal) {
        this.rendirEvaluacionFinal = rendirEvaluacionFinal;
    }
}