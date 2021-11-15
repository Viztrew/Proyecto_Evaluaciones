package com.mycompany.proyectopoo;

import static com.mycompany.proyectopoo.ManejoDeCSV.leerParametroCursoCSV;
import static com.mycompany.proyectopoo.ManejoDeCSV.leerAlumnosCursoCSV;
import static com.mycompany.proyectopoo.ManejoDeCSV.leerParametroCSV;
import java.util.ArrayList;

public class ControladorVentanaMostrar {
    
    private static String[] listaCursos;
    
    public ControladorVentanaMostrar()
    {
        ArrayList<String> lista = leerParametroCSV("Curso","Cursos.csv");
        this.listaCursos = new String[lista.size()];
        for (int i =0 ; i<lista.size(); i++)
        {
            listaCursos[i] = lista.get(i);
        }
    }
    
    // Metodod que se accionará cuando el botón de la ventana sea presionado.
    // recibe el nombre del curso a mostrar y el parametro (alumnos o asignaturas)
    // retorna una lista de strings con los parametros del curso correspondiente
    public static String[] botonPresionado (String nombreCurso,String parametro)
    {
        String[] listaParametro = null;
        ArrayList<String> arrayParametro;
        for (int i = 0 ; i < listaCursos.length ; i++)
        {
            if (nombreCurso.toLowerCase().equals(listaCursos[i].toLowerCase()))
            {
                // si el parametro son alumnos, se tratara de manera diferente a las asignaturas, ya que sus datos estan guardados en diferentes csv
                if (parametro.equals("Alumnos"))
                {
                    arrayParametro=leerAlumnosCursoCSV(nombreCurso,"Alumnos.csv");
                    
                    //si el curso no tiene alumnos la listaParametro tendrá solo un valor en la posición 0, indicando que no tiene alumnos
                    if(arrayParametro.isEmpty())
                    {
                        listaParametro = new String[1];
                        listaParametro[0] = "Curso sin Alumnos";
                    }else {
                        // si no, se traspasarán todos los valores del ArrayList a la lista[]
                        listaParametro = new String[arrayParametro.size()];
                        for (int j = 0 ; j < arrayParametro.size() ; j++)
                        {
                            listaParametro[j]=arrayParametro.get(j);
                        }
                    }
                }else
                {
                    // se separan y se guardan los nombres de las asignaturas directamente a la lista de strings
                    listaParametro = leerParametroCursoCSV(nombreCurso,"Asignaturas","Cursos.csv",false).get(0).split(",");
                    
                    // las Asignaturas tambien mostrarán sus unidades, asi que es necesario una lista auxiliar(aux) para guardarlas luego de separarlas (split).
                    String [] aux = leerParametroCursoCSV(nombreCurso,"Unidades","Cursos.csv",false).get(0).split(" ");
                    
                    // si el curso no tiene asignaturas guardadas (none, tendrá solo un valor en la posición 0, indicando que no tiene asignaturas
                    if (listaParametro[0].equals("none"))
                    {
                        listaParametro[0] = "Curso sin Asignaturas";
                    }else{
                        
                        // se añaden las unidades a las asignaturas, las cuales tienen el mismo índice
                        for (int k = 0 ; k < listaParametro.length ; k++)
                        {
                            listaParametro[k]+=": "+aux[k];
                        }
                    }
                }
            }
        }
        return listaParametro;
    }
    
    // método que valida si el curso ingresado es válido (está guardado)
    public static boolean validarCursoIngresado(String nombreCurso)
    {
        for (int i = 0 ; i < listaCursos.length ; i++)
        {
            if (nombreCurso.toLowerCase().equals(listaCursos[i].toLowerCase()))
            {
                return true;
            }
        }
        return false;
    }
            
    
    public String[] getLista()
    {
        String[] lista = new String[this.listaCursos.length];
        for (int i = 0 ; i < this.listaCursos.length ; i++)
        {
            lista[i]=this.listaCursos[i];
        }
        return lista;
    }
}
