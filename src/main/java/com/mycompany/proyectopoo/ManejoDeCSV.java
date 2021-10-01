
package com.mycompany.proyectopoo;

import java.io.*;
import java.util.ArrayList;


public class ManejoDeCSV {
    /*Funcion que lee desde el csv un parametro(Rut, Alumnos, Curso, Asignaturas) y retorna un arrayList 
      con los elementos del parametro sin repetirse
      Ej: leerParametroCSV("Curso","Cursos.csv") =  ArrayList<"Tercero","Primero","Segundo","Cuarto","Quinto">*/
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
    /*Funcion que lee desde el archivo csv un parametro(Ej: Rut, Alumnos, Asignaturas) del curso especificado y 
          lo retorna como lista con los elementos del parametro sin repetirse
            Ej: leerParametroCursoCSV("Quinto", "Rut", "Alumnos.csv") retorna ArrayList<"10987546-2","20324521-k">
        */
    public static ArrayList<String> leerAlumnosCursoCSV(String nombreCurso, String nombreArchivo) {
        
        ArrayList<String> listaParametro = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try{
                // se crea un BufferedReader(br) que leera el FileReader(fr) del archivo ubicado en la rutaArchivo
            br = new BufferedReader(new FileReader (nombreArchivo));
            
                // se lee la primera linea del archivo, esta contiene el parametro que identifica a los valores la columna
            String linea = br.readLine();
            
                // se buscan las dos columnas para recorrer y buscar los datos
            int columnaRut= buscarColumnaCSV(linea,"Rut"); 
            int columnaCurso = buscarColumnaCSV(linea,"Curso"); 
            
            while((linea = br.readLine()) != null)
            {  
                String[] partes = linea.split(";");
                if (partes[1].isEmpty()!=true)
                {
                    /*se llenará el ArrayList listaParametro con un parametro ssi la linea leída posee el nombre del curso buscado 
                  en la parte correspondiente (partes[columnaCurso]) y el ArrayList no posea un parametro con el mismo valor
                    */
                    if(partes[columnaCurso].toLowerCase().equals(nombreCurso.toLowerCase())){ 
                        if (listaParametro.isEmpty())
                            listaParametro.add(partes[columnaRut]);
                        else{
                            if (listaParametro.contains(partes[columnaRut]) == false){
                                listaParametro.add(partes[columnaRut]);
                            }
                        }
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
                
                if(partes[columnaCurso].toLowerCase().equals(nombreCurso.toLowerCase())){ 
                    if (listaParametro.isEmpty())
                        listaParametro.add(partes[columnaParametro]);
                    else{
                        if (listaParametro.contains(partes[columnaParametro]) == false){
                            listaParametro.add(partes[columnaParametro]);
                        }
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
    
    public static void guardarAlumnoEnCSV(String nombreCurso, String rutAlumno)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("Alumnos.csv",true);
            pw = new PrintWriter(fichero);
            pw.println(rutAlumno+";"+nombreCurso);

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
    
    public static void guardarCursoEnCSV(String nombreCurso)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("Cursos.csv",true);
            pw = new PrintWriter(fichero);
            pw.println(nombreCurso+";none;none");

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
    public static void guardarAsignaturaEnCSV(String nombreCurso,String nombreAsig,String nombreUnidad) {
        try {
            BufferedReader archivo = new BufferedReader(new FileReader("Cursos.csv"));
            StringBuffer bufferLineas = new StringBuffer();
            String linea;

            while ((linea = archivo.readLine()) != null) {
                String [] partes = linea.split(";");
                if(partes[0].toLowerCase().equals(nombreCurso.toLowerCase()))
                {
                    if (partes[1].toLowerCase().equals("none"))
                    {
                        linea = partes[0]+";"+nombreAsig+";"+nombreUnidad;
                    }else{
                        linea = partes[0]+";"+partes[1]+","+nombreAsig+";"+partes[2]+" "+nombreUnidad;
                    }        
                }
                bufferLineas.append(linea);
                bufferLineas.append('\n');

            }
            archivo.close();

          
            FileOutputStream archivoOut = new FileOutputStream("Cursos.csv");
            archivoOut.write(bufferLineas.toString().getBytes());
            archivoOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public static void guardarUnidadEnCSV(String nombreCurso,String nombreAsig,String nombreUnidad) {
        try {
            BufferedReader archivo = new BufferedReader(new FileReader("Cursos.csv"));
            StringBuffer bufferLineas = new StringBuffer();
            String linea;

            while ((linea = archivo.readLine()) != null) {
                String [] partes = linea.split(";");
                if(partes[0].toLowerCase().equals(nombreCurso.toLowerCase()))
                {
                    String [] asignaturas = partes[1].split(",");
                    for(int i = 0 ; i < asignaturas.length ; i++)
                    {
                        if (asignaturas[i].toLowerCase().equals(nombreAsig))
                        {
                            linea = partes[0]+";"+partes[1]+";";
                            String [] unidades = partes[2].split(" ");
                            for(int j = 0 ; j < unidades.length ; j++)
                            {
                                if (j==i)
                                {
                                    linea += unidades[j]+","+nombreUnidad+" ";
                                }else
                                {
                                    linea +=unidades[j]+" ";
                                }
                            }
                            
                        }
                    }
                }
                bufferLineas.append(linea);
                bufferLineas.append('\n');

            }
            archivo.close();

            
            FileOutputStream archivoOut = new FileOutputStream("Cursos.csv");
            archivoOut.write(bufferLineas.toString().getBytes());
            archivoOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    public static void deleteAlumnoCSV(String rutAlumno)
    {
       try {
            BufferedReader archivo = new BufferedReader(new FileReader("Alumnos.csv"));
            StringBuffer bufferLineas = new StringBuffer();
            String linea;

            while ((linea = archivo.readLine()) != null) {
                String [] partes = linea.split(";");
                if(partes[0].toLowerCase().equals(rutAlumno.toLowerCase()))
                {
                    
                }else
                {
                    bufferLineas.append(linea);
                    bufferLineas.append('\n');
                }
                

            }
            archivo.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream archivoOut = new FileOutputStream("Alumnos.csv");
            archivoOut.write(bufferLineas.toString().getBytes());
            archivoOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    public static void replaceAlumnoCSV(String rutOriginal, String rutNuevo)
    {
       try {
            BufferedReader archivo = new BufferedReader(new FileReader("Alumnos.csv"));
            StringBuffer bufferLineas = new StringBuffer();
            String linea;

            while ((linea = archivo.readLine()) != null) {
                String [] partes = linea.split(";");
                if(partes[0].toLowerCase().equals(rutOriginal.toLowerCase()))
                {
                    linea = rutNuevo+";"+partes[1];
                }
                bufferLineas.append(linea);
                bufferLineas.append('\n');
            }
            archivo.close();
            FileOutputStream archivoOut = new FileOutputStream("Alumnos.csv");
            archivoOut.write(bufferLineas.toString().getBytes());
            archivoOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
