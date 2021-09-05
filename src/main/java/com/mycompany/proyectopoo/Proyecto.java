package com.mycompany.proyectopoo;
import java.io.*;
import java.util.ArrayList;
public class Proyecto {
    
    public static void main(String [] arg) throws IOException 
    {
            //Se crea un arrayList para guardar los cursos que se leeran del csv en la funcion leerParametroCSV
        ArrayList<String> listaCursos;
        listaCursos = leerParametroCSV("Curso");
        Curso[] cursos = new Curso[listaCursos.size()];
        
            //se crean los cursos y se llenan los cursos
        crearCursos(cursos,listaCursos);    
        llenarCursos(cursos);
        
            //se muestran los cursos
        mostrarCursos(cursos);
        
            //se llenan las Unidades
        llenarPreguntasUnidades(cursos);
        llenarNotasUnidades(cursos);
        
            //se muestran los bancos de preguntas y las notas de las unidades
        mostrarPreguntasUnidades(cursos);
        mostrarNotasUnidades(cursos); 
        
        
        crearMapAlumnos(cursos);
        mostrarNotasAlumnos(cursos);
    }
    
        /*Funcion que lee desde el csv un parametro(Rut, Alumnos, Curso, cantAsignaturas) y retorna un arrayList 
          con los elementos del parametro sin repetirse
          Ej: leerParametroCSV("Curso",ruta) =  ArrayList<"Tercero","Primero","Segundo","Cuarto","Quinto">*/
    public static ArrayList<String>  leerParametroCSV(String parametroALeer)
    {
        ArrayList<String> listaParametros = new ArrayList<>();
        BufferedReader br = null;
        try{
                // se crea un BufferedReader(br) que leera el FileReader(fr) del archivo ubicado en la rutaArchivo
            br = new BufferedReader(new FileReader ("Alumnos.csv"));
            
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
    public static ArrayList<String> leerParametroCursoCSV(String nombreCurso, String parametro) {
        
        ArrayList<String> listaParametro = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try{
                // se crea un BufferedReader(br) que leera el FileReader(fr) del archivo ubicado en la rutaArchivo
            br = new BufferedReader(new FileReader ("Alumnos.csv"));
            
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
                if(partes[columnaCurso].equals(nombreCurso)) 
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
            if (partes[i].equals(columnaBuscada)){
                columna=i;
                break;
            }
        }
        return columna;
    }

    private static void crearCursos(Curso[] cursos, ArrayList<String> listaCursos) {
            //se crean los cursos y se ingresan al la lista de objetos de tipo Curso       
        for (int i = 0; i < cursos.length; i++ ){
            Curso curso = new Curso(listaCursos.get(i),leerParametroCursoCSV(listaCursos.get(i),"Rut"));
            cursos[i] = curso;
        }
    }
    
    private static void llenarCursos(Curso[] cursos)throws IOException {
            //se llenan los cursos con sus respectivas Asignaturas y Unidades de estas
        for (int i = 0; i < cursos.length; i++ )
            cursos[i].llenarCurso(leerParametroCursoCSV(cursos[i].getNombreCurso(), "Asignaturas"),leerParametroCursoCSV(cursos[i].getNombreCurso(),"Unidades"));
    }
    private static void mostrarCursos(Curso[] cursos) {
            //se muestra la informacion de los cursos
        for (int i = 0; i < cursos.length; i++ )
            cursos[i].mostrarCurso();
    }
    
    private static void llenarPreguntasUnidades(Curso[] cursos) throws IOException 
    {
            //llena las preguntas de las unidades de las Asignaturas(j)  de los cursos(i)
        for (int i = 0; i < cursos.length; i++ ){
            for(int j = 0; j < cursos[i].getListaAsignaturas().size(); j++ )
                cursos[i].getListaAsignaturas().get(j).llenarPreguntasUnidad(cursos[i].getNombreCurso());
        }
    }

    private static void llenarNotasUnidades(Curso[] cursos) throws IOException {
            //llena las notas de las unidades de las Asignaturas(j)  de los cursos(i)
        for (int i = 0; i < cursos.length; i++ ){
            for(int j = 0; j < cursos[i].getListaAsignaturas().size(); j++ )
                cursos[i].getListaAsignaturas().get(j).llenarNotasUnidad();
        }
    }

    private static void mostrarPreguntasUnidades(Curso[] cursos) {
            //muestra las preguntas de las unidades(k) de las Asignaturas(j)  de los cursos(i)
        for (int i = 0; i < cursos.length; i++ ){
            for(int j = 0; j < cursos[i].getListaAsignaturas().size(); j++ )
            {
                for(int k = 0; k < cursos[i].getListaAsignaturas().get(j).getListaUnidades().size(); k++ )
                    cursos[i].getListaAsignaturas().get(j).getListaUnidades().get(k).mostrarPreguntas(cursos[i].getNombreCurso());
            }
        }
    }

    private static void mostrarNotasUnidades(Curso[] cursos) {
            //muestra las notas de las unidades(k) de las Asignaturas(j)  de los cursos(i)
        for (int i = 0; i < cursos.length; i++ ){
            for(int j = 0; j < cursos[i].getListaAsignaturas().size(); j++ )
            {
                for(int k = 0; k < cursos[i].getListaAsignaturas().get(j).getListaUnidades().size(); k++ )
                    cursos[i].getListaAsignaturas().get(j).getListaUnidades().get(k).mostrarNotas(cursos[i].getNombreCurso());
            }
        }
    } 

    private static void crearMapAlumnos(Curso[] cursos) {
        for (int i = 0; i < cursos.length; i++ ){
            for (int z = 0 ; z < cursos[i].getListaRutAlumnos().size() ; z++)
            {
                ArrayList<String> notasAlumno= new ArrayList<>();
                for(int j = 0; j < cursos[i].getListaAsignaturas().size(); j++ )
                {
                    for(int k = 0; k < cursos[i].getListaAsignaturas().get(j).getListaUnidades().size(); k++ )
                    {
                        double[]listaNotas = cursos[i].getListaAsignaturas().get(j).getListaUnidades().get(k).getListaNotas();
                        notasAlumno.add(Double.toString(listaNotas[z]));
                    } 
                }
                cursos[i].guardarNotasCurso(cursos[i].getListaRutAlumnos().get(z), notasAlumno);
            }
        }
    }

    private static void mostrarNotasAlumnos(Curso[] cursos) {
        for (int i = 0; i < cursos.length; i++ )
        {
            System.out.println("Notas curso "+cursos[i].getNombreCurso()+": ");
            for (int j = 0 ; j < cursos[i].getListaRutAlumnos().size() ; j++)
                System.out.println("Notas " + cursos[i].getListaRutAlumnos().get(j) + ": " + cursos[i].getNotasAlumnos().get(cursos[i].getListaRutAlumnos().get(j)));
        }
    }
}
