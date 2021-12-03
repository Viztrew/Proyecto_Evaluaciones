package com.mycompany.proyectopoo;

import java.util.ArrayList;

// Clase que forma parte de el patrón Strategy,  muestra por consola los alumnos 
// Reprobados (con o sin derecho a examen) al finalizar el año, (no se puede utilizar sin antes finalizar el año)
public class FormatoFinalReprobado extends FormatoEstadoAlumnos{
    
    public void mostrarEstadoAlumnos(ArrayList<Curso> listaCursos) {
        try
        {
            int cant;
            for (int i = 0 ; i < listaCursos.size() ; i++ )
            {
                cant=0;
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
                        if (estados.get(j) instanceof AlumnoReprobado)
                        {
                            AlumnoReprobado estadoAlumno = (AlumnoReprobado)estados.get(j);
                            if(estadoAlumno.getRendirEvaluacionFinal())
                            {
                                System.out.println("Alumno: "+estadoAlumno.getRutPersona()+ " Promedio Final: " + estadoAlumno.getPromedioFinal()+" Estado: REPROBADO CON DERECHO A EXAMEN");
                            }else
                            {
                                System.out.println("Alumno: "+estadoAlumno.getRutPersona()+ " Promedio Final: " + estadoAlumno.getPromedioFinal()+" Estado: REPROBADO SIN DERECHO A EXAMEN");
                            }
                            cant++;
                        }
                    }
                    if(cant==0 && !alumnos.isEmpty())
                    {
                        System.out.println("Curso sin alumnos Reprobados");
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
