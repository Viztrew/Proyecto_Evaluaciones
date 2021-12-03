package com.mycompany.proyectopoo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

// Clase que forma parte de el patrón Strategy,  muestra por consola todos los alumnos, sus promedios 
// y si aprobaron (con o sin beca) o reprobaron (con o sin derecho a examen) por curso
public class FormatoFinal extends FormatoEstadoAlumnos {

    public void mostrarEstadoAlumnos(ArrayList<Curso> listaCursos){
        try
        {
            for (int i = 0 ; i < listaCursos.size() ; i++ )
            {
                ArrayList <AlumnoMatricula> estados =  listaCursos.get(i).getEstadoCurso();
                ArrayList <String> alumnos = listaCursos.get(i).getListaRuts();

                if (estados.isEmpty()&& !alumnos.isEmpty())
                {
                    throw new SchoolYearUnfinishedException();
                }else
                {
                    System.out.println("Alumnos de " +listaCursos.get(i).getNombreCurso()+": ");
                    if (alumnos.isEmpty()){
                    System.out.println("El curso " + listaCursos.get(i).getNombreCurso() + " no tiene alumnos registrados.");
                    }  

                    for (int j = 0 ; j < estados.size() ; j++)
                    {
                        if (estados.get(j) instanceof AlumnoAprobado)
                        {
                            AlumnoAprobado estadoAlumno = (AlumnoAprobado)estados.get(j);
                            if (estadoAlumno.getBeca())
                            {
                                System.out.println("Alumno: "+estadoAlumno.getRutPersona()+ " Promedio Final: " + estadoAlumno.getPromedioFinal()+" Estado: APROBADO CON BECA");
                            }else
                            {
                                System.out.println("Alumno: "+estadoAlumno.getRutPersona()+ " Promedio Final: " + estadoAlumno.getPromedioFinal()+" Estado: APROBADO SIN BECA");
                            }
                        }else if (estados.get(j) instanceof AlumnoReprobado)
                        {
                            AlumnoReprobado estadoAlumno = (AlumnoReprobado)estados.get(j);
                            if(estadoAlumno.getRendirEvaluacionFinal())
                            {
                                System.out.println("Alumno: "+estadoAlumno.getRutPersona()+ " Promedio Final: " + estadoAlumno.getPromedioFinal()+" Estado: REPROBADO CON DERECHO A EXAMEN");
                            }else
                            {
                                System.out.println("Alumno: "+estadoAlumno.getRutPersona()+ " Promedio Final: " + estadoAlumno.getPromedioFinal()+" Estado: REPROBADO SIN DERECHO A EXAMEN");
                            }
                        }
                    }
                }
            }
            System.out.print("\n");    
        }catch(SchoolYearUnfinishedException e)
        {
            e.printStackTrace();
        }
    }
}
