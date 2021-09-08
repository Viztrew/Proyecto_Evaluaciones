
package com.mycompany.proyectopoo;

import java.io.*;
import java.util.ArrayList;


public class ManejoDeCSV {
    /*Funcion que lee desde el csv un parametro(Rut, Alumnos, Curso, cantAsignaturas) y retorna un arrayList 
      con los elementos del parametro sin repetirse
      Ej: leerParametroCSV("Curso",ruta) =  ArrayList<"Tercero","Primero","Segundo","Cuarto","Quinto">*/
    public static ArrayList<String>  leerParametroCSV(String parametroALeer,String nombreArchivo)
    {
        ArrayList<String> listaParametros = new ArrayList<>();
        BufferedReader br = null;
        try{
                // se crea un BufferedReader(br) que leera el FileReader(fr) del archivo ubicado en la rutaArchivo
            br = new BufferedReader(new FileReader (nombreArchivo));
            
            String linea = null;
            
                //se busca la columna que contiene los parametros a guardar en el ArrayList
            int columna = buscarColumnaCSV(br.readLine(),parametroALeer); 
            
                //se leerá hasta la ultima linea del archivo
            while((linea = br.readLine()) != null)
            {
                String[] partes = linea.split(";");
                
                    /*si la lista de parametros está vacía, se le añade al ArrayList el primer parametro ubicado en 
                      la columna correspondiete*/
                if (listaParametros.isEmpty())
                    listaParametros.add(partes[columna]);
                else{
                    /*si la lista de parametros no está vacía, se pregunta si el parametro ya se encuentra en el
                      ArrayList, si no está, se añade al ArrayList el parametro ubicado en la columna correspondiete*/
                    if (listaParametros.contains(partes[columna])!=true){
                        listaParametros.add(partes[columna]);
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
                //se cierra el archivo
            try{                    
                if( null != br )
                br.close();
            }catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
        return listaParametros;
    }
    
        /*Funcion que lee desde el archivo csv un parametro(Ej: Rut, Alumnos, cantAsignaturas) del curso especificado y 
          lo retorna como lista con los elementos del parametro sin repetirse
            Ej: leerParametroCursoCSV("Quinto", "Rut",ruta) retorna ArrayList<"10987546-2","20324521-k">
        */
    public static ArrayList<String> leerParametroCursoCSV(String nombreCurso, String parametro, String nombreArchivo) {
        
        ArrayList<String> listaParametro = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try{
                // se crea un BufferedReader(br) que leera el FileReader(fr) del archivo ubicado en la rutaArchivo
            br = new BufferedReader(new FileReader (nombreArchivo));
            
                // se lee la primera linea del archivo, esta contiene el parametro que identifica a los valores la columna
            String linea = br.readLine();
            
                // se buscan las dos columnas para recorrer y buscar los datos
            int columnaParametro= buscarColumnaCSV(linea,parametro); 
            int columnaCurso = buscarColumnaCSV(linea,"Curso"); 
            
            while((linea = br.readLine()) != null)
            {  
                String[] partes = linea.split(";");
                
                    /*se llenará el ArrayList listaParametro con un parametro ssi la linea leída posee el nombre del curso buscado 
                      en la parte correspondiente (partes[columnaCurso]) y el ArrayList no posea un parametro con el mismo valor
                    */
                if(partes[columnaCurso].toLowerCase().equals(nombreCurso.toLowerCase())) 
                    if (listaParametro.isEmpty())
                        listaParametro.add(partes[columnaParametro]);
                    else{
                        if (listaParametro.contains(partes[columnaParametro])!=true){
                            listaParametro.add(partes[columnaParametro]);
                        }
                    }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
                //se cierra el archivo
            try{                    
                if( null != br )
                    br.close();
            }catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
        return listaParametro;
    }
    
        // funcion que recibe como parametro la primera linea del csv y la columna que se busca en el archivo, retorna el numero de la columna
        // Ej: buscarColumnaCSV(primeraLinea,"Curso") retorna 2
    public static int buscarColumnaCSV(String linea,String columnaBuscada) {
        int columna = 0;
        
            /*la linea se le aplica el metodo split(";") ya que es un archivo separado por comas (CSV)
              separandola en partes, cada parte es un parametro.
            Ej: linea = Rut;Curso;Asignatura | partes[0]=Rut , partes[1]=Curso ,partes[2]=Asignatura
            */
        String[] partes = linea.split(";");
        for (int i = 0 ; i < partes.length ; i++){
            if (partes[i].toLowerCase().equals(columnaBuscada.toLowerCase())){
                columna=i;
                break;
            }
        }
        return columna;
    }
    public static void guardarPreguntaEnCSV(String nombreCurso, String nombreAsig, String nombreUnidad, String pregunta)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("BancoPreguntas.csv",true);
            pw = new PrintWriter(fichero);
            pw.println(nombreCurso+";"+nombreAsig +","+nombreUnidad + ","+pregunta);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
}
