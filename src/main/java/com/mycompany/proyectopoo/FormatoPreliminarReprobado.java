
package com.mycompany.proyectopoo;

import java.util.ArrayList;

// Clase que forma parte de el patrón Strategy,  muestra por consola todos los alumnos con sus promedios que preliminarmente reprueban
public class FormatoPreliminarReprobado extends FormatoEstadoAlumnos {
    
    public void mostrarEstadoAlumnos(ArrayList<Curso> listaCursos) {
        double promedio;
        for (int i = 0 ; i < listaCursos.size() ; i++ )
        {
            ArrayList<String> alumnos = listaCursos.get(i).getListaRuts();
            for (int j = 0 ; j < alumnos.size() ; j++ )
            {
                promedio = getPromedioAlumno(listaCursos.get(i), alumnos.get(j));
                if (promedio<4.0)
                {
                    System.out.println("Alumno: " + alumnos.get(j) + " Promedio : " + promedio + " Reprobando" );
                }
            }
        }
    }
    
}
