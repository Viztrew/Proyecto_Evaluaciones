package com.mycompany.proyectopoo;

import java.util.ArrayList;
import org.apache.commons.math3.util.Precision;

//Clase "Padre" de las que forman el patrón Strategy
public abstract class FormatoEstadoAlumnos {
    
    public abstract void mostrarEstadoAlumnos(ArrayList<Curso> listaCursos);
    
    protected double getPromedioAlumno(Curso curso, String rutAlumno)
    {
        double notasAlumno=0.0;
        int cantNotas=0;
        
        ArrayList <Double> listaNotas =  curso.getNotas(rutAlumno);
        for (int j = 0 ; j < listaNotas.size() ; j++)
        {
            if (listaNotas.get(j) != 0.0){
                notasAlumno += listaNotas.get(j);
                cantNotas++;
            }
        }
       
            // evitar división por 0
        if (cantNotas>0)
        {
            return Precision.round(notasAlumno/cantNotas,1);
        }
        
        return 0.0;
    }
}
 //388 462