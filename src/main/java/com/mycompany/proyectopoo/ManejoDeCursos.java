
package com.mycompany.proyectopoo;

import java.util.ArrayList;
import java.io.IOException;
import static com.mycompany.proyectopoo.ManejoDeCSV.*;
import org.apache.commons.math3.util.Precision;//se utiliza la dependencia org.apache.commons de matematicas para aproximar los numeros decimales con Precision.round
public class ManejoDeCursos {
         //Se crea un arrayList para guardar los cursos que se leeran del csv en la funcion leerParametroCSV
    private ArrayList<String> listaCursos;
    private ArrayList<Curso> cursos;

    ManejoDeCursos(){
        this.listaCursos = leerParametroCSV("Curso","Cursos.csv");
        this.cursos = new ArrayList<>();
    }
        
        //se crean los objetos cursos segun los datos guardados en el csv  
    public void crearCursos()throws IOException  {
        for (int i = 0; i < this.listaCursos.size(); i++ ){
            Curso curso = null;
            String nombreCurso = this.listaCursos.get(i);
            switch(tipoLlenado(nombreCurso)){
                
                case 1: //Solamente el nombre del curso esta guardado en el csv
                    curso = new Curso(nombreCurso);
                    break;
                case 2: //Solamente el nombre del curso y al menos un alumno estan guardados en los csv
                    curso = new Curso(nombreCurso,leerParametroCursoCSV(nombreCurso,"Rut","Alumnos.csv",false));
                    break;
                case 3: //Solamente el nombre del curso, al menos una asignatura y una unidad de dicha asignatura estan guardados en los csv
                    curso = new Curso(nombreCurso,leerParametroCursoCSV(nombreCurso, "Asignaturas","Cursos.csv",false).get(0).split(","),leerParametroCursoCSV(nombreCurso,"Unidades","Cursos.csv",false).get(0).split(" "));   
                    break;
                case 4: //El curso tiene guardado al menos un alumno, asignatura y una unidad de dicha asignatura
                    curso = new Curso(nombreCurso,leerAlumnosCursoCSV(nombreCurso,"Alumnos.csv"), leerParametroCursoCSV(nombreCurso, "Asignaturas","Cursos.csv",false).get(0).split(","), leerParametroCursoCSV(nombreCurso,"Unidades","Cursos.csv",false).get(0).split(" "));
                    break;
            }
            this.cursos.add(curso);
        }
        
        //se leen los bancos de preguntas (si tienen) de las unidades de cada asignatura de cada curso 
        for (int i = 0; i < this.cursos.size(); i++ )
            leerBancoDePreguntasCSV(cursos.get(i));
        
        //se leen y guardan las notas guardadas en el CSV
        for (int i = 0; i < this.cursos.size(); i++ )
            leerNotasCSV(cursos.get(i));
        
    }
    
        //funcion que retorna un int que determina que constructor se utiliza para el objeto curso, según los datos presentes en los csv
    public int tipoLlenado(String nombreCurso)
    {
        int caso;
        if (leerAlumnosCursoCSV(nombreCurso,"Alumnos.csv").isEmpty()){
            if (leerParametroCursoCSV(nombreCurso, "Asignaturas","Cursos.csv",false).get(0).equals("none")){
                caso=1;
            }else{
                caso=3;
            }
        }else{
            if (leerParametroCursoCSV(nombreCurso, "Asignaturas","Cursos.csv",false).get(0).equals("none")){
                caso=2;
            }else{
                caso=4;
            }
        }
        return caso;
    }
    
    private void leerBancoDePreguntasCSV(Curso curso) {
        ArrayList<String> listaPreguntas = leerParametroCursoCSV(curso.getNombreCurso(),"Asignatura,Unidad,Pregunta","BancoPreguntas.csv",false);
        ArrayList<String> listaAsig = curso.getListaNombresAsig();
        
        for(int i = 0 ; i<listaPreguntas.size(); i++)
        {
            String[] partes = listaPreguntas.get(i).split(",");
            for (int j = 0; j< listaAsig.size() ; j++)
            {
                if (partes[0].toLowerCase().equals(listaAsig.get(j).toLowerCase()))
                {
                    ArrayList <String> listaUnidades = curso.getListaNombresUnidades(listaAsig.get(j));
                    for (int k = 0 ; k < listaUnidades.size() ; k++)
                    {
                        if (partes[1].toLowerCase().equals(listaUnidades.get(k).toLowerCase()))
                            addPreguntaUnidad(curso.getNombreCurso(), partes[0], partes[1], partes[2], false);
                    }
                }
            }
        }
    }
    
    private void leerNotasCSV (Curso curso)
    {
        ArrayList<String> listaRuts = leerParametroCursoCSV(curso.getNombreCurso(),"Rut","Notas.csv",false);
        ArrayList<String> listaNotas = leerParametroCursoCSV(curso.getNombreCurso(),"Nota","Notas.csv",true);
     
        ArrayList<String> listaAsig = curso.getListaNombresAsig();
        
        for(int i = 0 ; i<listaRuts.size(); i++)
        {
            
            String[] notas = listaNotas.get(i).split(",");
            int aux = 0;
            for (int j = 0; j< listaAsig.size() ; j++)
            {
                
                ArrayList <String> listaUnidades = curso.getListaNombresUnidades(listaAsig.get(j));
                for (int k = 0 ; k < listaUnidades.size() ; k++)
                {
                    addNotaAlumno(curso.getNombreCurso(),listaAsig.get(j),listaUnidades.get(k),listaRuts.get(i),Double.parseDouble(notas[aux]),true);
                    aux++;
                }
            }
        }
    }
        //se muestra la informacion de los cursos
    public void mostrarNombreCursos()
    {
        for (int i = 0; i < this.cursos.size(); i++ ){
            System.out.println(this.cursos.get(i).getNombreCurso());
        }
    }
    
    public void mostrarNombresAsig(String nombreCurso)
    {
        for (int i = 0; i < this.cursos.size(); i++ ){
            if ((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                ArrayList <String> listaNombresAsig = cursos.get(i).getListaNombresAsig();
                if (listaNombresAsig.isEmpty()){
                    System.out.println("El Curso "+ this.cursos.get(i).getNombreCurso() + " no tiene Asignaturas registradas");
                }else
                {
                    for(int j = 0; j < listaNombresAsig.size(); j++ )
                    System.out.println("Asignatura "+(j+1)+":"+listaNombresAsig.get(j));
                return;
                }
            }
        }
    }
    
    public void mostrarNombresUnidades(String nombreCurso,String nombreAsig)
    {
        
        for (int i = 0; i < this.cursos.size(); i++ )
        {
            if ((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                if (validarAsignatura(nombreCurso,nombreAsig))
                {
                    ArrayList <String> listaNombresAsig = cursos.get(i).getListaNombresAsig();
                    for(int j = 0; j < listaNombresAsig.size(); j++ )
                    {
                        if((listaNombresAsig.get(j)).toLowerCase().equals(nombreAsig.toLowerCase()))
                        {
                            ArrayList <String> listaNombresUnidades = this.cursos.get(i).getListaNombresUnidades(listaNombresAsig.get(j));
                            for(int k = 0; k < listaNombresUnidades.size(); k++ )
                            System.out.println("Unidad "+(k+1)+": " +listaNombresUnidades.get(k) );
                        }
                    }
                    return;            
                }
            }
        }
    }
    
    public void mostrarTodosAlumnos() {
        for (int i = 0; i < this.cursos.size(); i++ )
        {
            System.out.println("Alumnos de " + cursos.get(i).getNombreCurso()+ ": ");
            ArrayList<String> rutsAlumnos = cursos.get(i).getListaRuts();
            if (rutsAlumnos.isEmpty())
            {
                System.out.println("El curso " + this.cursos.get(i).getNombreCurso() + " no tiene alumnos registrados.");
            }else
            {
                for(int j=0 ; j<rutsAlumnos.size(); j++)
                    System.out.println("Alumno " + (j+1) + ": " + rutsAlumnos.get(j));
            }
            System.out.print("\n");
        }
    }
    public void matriculaFinal() {
        for (int i = 0; i < this.cursos.size(); i++ )
        {
            ArrayList<String> nombresCursos = getNombreCursos();
            ArrayList<String> rutsAlumnos = cursos.get(i).getListaRuts();
            if (rutsAlumnos.isEmpty()!=true)
            {
                // Se calcula el promedio final del alumno y se llama al constructor correspondiente dado los siguientes parametros y se guardan en una arraylist de Curso
                // Aprueba con beca: promedio >= 6.5
                // Aprueba sin beca: promedio >= 4
                // Reprueban (sin derecho a examen): promedio < 3
                // Reprueban (con derecho a examen): 4 > promedio >= 3
                for(int j=0 ; j<rutsAlumnos.size(); j++){  
                    
                    // los aprobados que tengan promedio >=6.5 ya tienen su beca asegurada y no tienen derecho a rendir la ultima prueba (true(beca),false(prueba))
                    if((getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j))) >= 4){
                        if((getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j))) >= 6.5){
                            Aprobado a = new Aprobado(rutsAlumnos.get(j),getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j)),true,false);
                            cursos.get(i).addEstadoAlumno(a);
                        }
                        else{
                            // los aprobados que no tengan su beca, podrán rendir la prueba, si su nota es mayora a 6.5 obtienen beca  (false(beca),true(prueba))
                            Aprobado a = new Aprobado(rutsAlumnos.get(j),getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j)),false,true);
                            cursos.get(i).addEstadoAlumno(a);
                        }
                    }
                    // los repitentes que tengan un promedio >= 3 tienen tienen una posibildiad de dar un examen, si su nota cumple con los parametros, su situacionAcademica cambia a true(pasa de curso)
                    // (false(repiten), true(examen)
                    if((getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j))) < 4){
                        if((getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j))) >= 3){
                            Repitente a = new Repitente(rutsAlumnos.get(j),getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j)), false,true);
                            cursos.get(i).addEstadoAlumno(a);
                        }
                        else{
                            // si el alumno no tiene notas (no se puede sacar su promedio), se le asigna promedio 1.0 y no tiene derecho a examen por tanto repite curso (false,false)
                            if (getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j))==0.0)
                            {
                                Repitente a = new Repitente(rutsAlumnos.get(j),1.0,false,false);
                                cursos.get(i).addEstadoAlumno(a);
                            }else
                            {
                                Repitente a = new Repitente(rutsAlumnos.get(j),getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j)),false,false);
                                cursos.get(i).addEstadoAlumno(a);
                            }
                        }
                    }
                }
            }
        }
    }    
    
    // Metodo que muestra en pantalla los estado de los alumnos ya sean aprobado o reprobados
    // si reprobados es true, mostrara reprobados, si no, los omite.
    // si aprobados es true, mostrará aprobados, si no, los omite
    public void mostrarEstadoAlumnos (boolean reprobados, boolean aprobados)
    {
        for (int i = 0; i < this.cursos.size(); i++ )
        {
            ArrayList <AlumnoMatricula> estados =  this.cursos.get(i).getEstadoCurso();
            if (aprobados && reprobados)
            {
                System.out.println("Alumnos de " +this.cursos.get(i).getNombreCurso()+": ");
                if (estados.isEmpty()){
                    System.out.println("El curso " + this.cursos.get(i).getNombreCurso() + " no tiene alumnos registrados.");
                }  
            }
            for (int j =0 ; j < estados.size() ; j++)
            {
                if (estados.get(j) instanceof Aprobado && aprobados)
                {
                    Aprobado estadoAlumno = (Aprobado)estados.get(j);
                    if (estadoAlumno.getBeca())
                    {
                        System.out.println("Alumno: "+estadoAlumno.getRutPersona()+ " Promedio Final: " + estadoAlumno.getPromedioFinal()+" Estado: APROBADO CON BECA");
                    }else
                    {
                        System.out.println("Alumno: "+estadoAlumno.getRutPersona()+ " Promedio Final: " + estadoAlumno.getPromedioFinal()+" Estado: APROBADO SIN BECA");
                    }
                }else if (estados.get(j) instanceof Repitente && reprobados)
                {
                    Repitente estadoAlumno = (Repitente)estados.get(j);
                    if(estadoAlumno.getRendirEvaluacionFinal())
                    {
                        System.out.println("Alumno: "+estadoAlumno.getRutPersona()+ " Promedio Final: " + estadoAlumno.getPromedioFinal()+" Estado: REPROBADO CON DERECHO A EXAMEN");
                    }else
                    {
                        System.out.println("Alumno: "+estadoAlumno.getRutPersona()+ " Promedio Final: " + estadoAlumno.getPromedioFinal()+" Estado: REPROBADO SIN DERECHO A EXAMEN");
                    }
                }
            }
            if (aprobados && reprobados)
            {
                
                System.out.print("\n");
            }
        }
        System.out.print("\n");
    }
    
    // fncion que muestra los alumnos que pueden rendir la evaluacion final (RendirEvaluacionFinal == true)
    public void mostrarAlumnosEvaluacionFinal()
    {
        for (int i = 0; i < this.cursos.size(); i++ )
        {
            ArrayList <AlumnoMatricula> estados =  this.cursos.get(i).getEstadoCurso();
            for (int j =0 ; j < estados.size() ; j++)
            {
                if (estados.get(j) instanceof Aprobado)
                {
                    Aprobado estadoAlumno = (Aprobado)estados.get(j);
                    if(estadoAlumno.getRendirEvaluacionFinal()){
                        System.out.println("Alumno: "+estadoAlumno.getRutPersona()+ " Promedio Final: " + estadoAlumno.getPromedioFinal()+" Estado: APROBADO SIN BECA");
                        System.out.print("\n");
                    }
                }else if (estados.get(j) instanceof Repitente)
                {
                    Repitente estadoAlumno = (Repitente)estados.get(j);
                    if(estadoAlumno.getRendirEvaluacionFinal())
                    {
                        System.out.println("Alumno: "+estadoAlumno.getRutPersona()+ " Promedio Final: " + estadoAlumno.getPromedioFinal()+" Estado: REPROBADO CON DERECHO A EXAMEN");
                        System.out.print("\n");
                    }
                }
            }
            
        }
    }
    /*
    public int  AprobadoReprobado(String rutAlumno){

             
        for(int i = 0; i < listaAprobados.size(); i++){
            if ((listaAprobados.get(i).getRutPersona()).toLowerCase().equals(rutAlumno.toLowerCase()))
            {
                if(listaAprobados.get(i).getBeca()==true){
                    return 1;
                }
            }
        }
        return 0;
    }*/

    public void mostrarAlumnosCurso(String nombreCurso)
    {
        for (int i = 0; i < this.cursos.size() ; i++ )
        {
            if ((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                System.out.println("Alumnos de " + cursos.get(i).getNombreCurso()+ ": ");
                ArrayList<String> rutsAlumnos = cursos.get(i).getListaRuts();
                if (rutsAlumnos.isEmpty())
                {
                    System.out.println("El curso " + this.cursos.get(i).getNombreCurso() + " no tiene Alumnos registrados.");
                }else
                {
                    for(int j=0 ; j<rutsAlumnos.size(); j++)
                        System.out.println("Alumno " + (j+1) + ": " + rutsAlumnos.get(j));
                    return;
                }
            }
        }
    }
            
    public void mostrarTodosAsigYUnidades()
    {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            System.out.println("Asignaturas y Unidades Curso "+ cursos.get(i).getNombreCurso()+":");
            ArrayList <String> listaNombresAsig = cursos.get(i).getListaNombresAsig();
            if (listaNombresAsig.isEmpty())
            {
                System.out.println("El Curso "+ this.cursos.get(i).getNombreCurso() + " no tiene Asignaturas registradas");
            }else
            {
                for(int j = 0; j < listaNombresAsig.size(); j++ )
                {
                    System.out.println("Asignatura "+(j+1)+": "+listaNombresAsig.get(j));
                    ArrayList <String> listaNombresUnidades = this.cursos.get(i).getListaNombresUnidades(listaNombresAsig.get(j));
                    if (listaNombresUnidades.isEmpty())
                    {
                        System.out.println("La Asignatura "+ listaNombresAsig.get(j) + " no tiene Unidades registradas");
                    }else
                    {
                        for(int k = 0; k < listaNombresUnidades.size(); k++ )
                            System.out.println("Unidad "+(k+1)+": " +listaNombresUnidades.get(k) );
                    }
                }
            }
            System.out.print("\n");
        }
    }
    
    public void mostrarAsigYUnidadesCurso(String nombreCurso)
    {
        
        for (int i = 0; i < this.cursos.size() ; i++ )
        {
            if ((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                System.out.println("Asignaturas y Unidades Curso "+ cursos.get(i).getNombreCurso()+":");
                ArrayList <String> listaNombresAsig = cursos.get(i).getListaNombresAsig();
               if (listaNombresAsig.isEmpty())
                {
                    System.out.println("El Curso "+ this.cursos.get(i).getNombreCurso() + " no tiene Asignaturas registradas");
                }else
                {
                    for(int j = 0; j < listaNombresAsig.size(); j++ )
                    {
                        System.out.println("Asignatura "+(j+1)+":"+listaNombresAsig.get(j));
                        ArrayList <String> listaNombresUnidades = this.cursos.get(i).getListaNombresUnidades(listaNombresAsig.get(j));
                        if (listaNombresUnidades.isEmpty())
                        {
                            System.out.println("La Asignatura "+ listaNombresAsig.get(j) + " no tiene Unidades registradas");
                        }else
                        {
                            for(int k = 0; k < listaNombresUnidades.size(); k++ )
                                System.out.println("Unidad "+(k+1)+": " +listaNombresUnidades.get(k) );
                        }
                    }
                }
            }
        }
    }
     
    public void mostrarBancoPreguntasUnidad(String nombreCurso, String nombreAsig, String nombreUnidad) {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                ArrayList <String> listaPreguntas = new ArrayList <String>();
                listaPreguntas.addAll(cursos.get(i).getListaPreguntasAsig(nombreAsig, nombreUnidad));
                if (listaPreguntas.isEmpty())
                {
                    System.out.println("No se encontraron preguntas");
                    return;
                }else{
                    for (int j = 0 ; j<listaPreguntas.size();j++)
                    System.out.println("Pregunta "+(j+1)+": "+listaPreguntas.get(j));
                }
                
            }
        }
    }
    
    public void mostrarNotasAlumnos(String nombreCurso) {
       for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                ArrayList<String> alumnos = this.cursos.get(i).getListaRuts();
                for(int j = 0 ; j < alumnos.size(); j++)
                {
                    System.out.println("Notas alumno "+alumnos.get(j)+": ");
                    System.out.println(cursos.get(i).getNotas(alumnos.get(j)));
                }
            }
        }
    }
    public void mostrarAlumnosYNotasUnidad(String nombreCurso, String nombreAsig, String nombreUnidad)
    {
        for (int i = 0 ; i< this.cursos.size() ; i++)
        {
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                ArrayList<String> listaAlumnos = this.cursos.get(i).getListaRuts();
                for (int j = 0 ; j < listaAlumnos.size(); j++)
                {
                    System.out.print("Alumno "+j+": "+ listaAlumnos.get(j)+" Nota de Unidad "+ nombreUnidad+": ");
                    if (getNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, listaAlumnos.get(j))==0.0)
                    {
                        System.out.println("s/n");
                    }
                    else
                    {
                        System.out.println(getNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, listaAlumnos.get(j)));
                    }
                    
                }
                
            }
        }
    }

    
    public boolean validarCurso(String nombreCurso)
    {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
                return true;
        }
        System.out.println("'"+nombreCurso+"' no fue encontrado en el sistema." );
        System.out.print("\n");
        return false;
    }
    
    public boolean validarAsignatura(String nombreCurso,String nombreAsig)
    {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                ArrayList<String> nombresAsignaturas = cursos.get(i).getListaNombresAsig();
                for (int j = 0; j < nombresAsignaturas.size(); j++ ){
                    if((nombresAsignaturas.get(j)).toLowerCase().equals(nombreAsig.toLowerCase()))
                        return true;
                }
            }
        }
        System.out.println("'"+nombreAsig+"' no fue encontrado en el curso " +nombreCurso+ "." );
        System.out.print("\n");
        return false;
    }
    
    public boolean validarUnidad(String nombreCurso,String nombreAsig,String nombreUnidad)
    {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                ArrayList <String> listaNombresAsig = cursos.get(i).getListaNombresAsig();
                for(int j = 0; j < listaNombresAsig.size(); j++ )
                {
                    if((listaNombresAsig.get(j)).toLowerCase().equals(nombreAsig.toLowerCase()))
                    {
                        ArrayList <String> listaNombresUnidades = this.cursos.get(i).getListaNombresUnidades(listaNombresAsig.get(j));
                        for(int k = 0; k < listaNombresUnidades.size(); k++ )
                        {
                            if((listaNombresUnidades.get(k)).toLowerCase().equals(nombreUnidad.toLowerCase()))
                                return true;
                        }
                    }
                }
            }
        }
        System.out.println("'"+nombreUnidad+"' no fue encontrado en la Asignatura " +nombreAsig );
        System.out.print("\n");
        return false;
    }
    public boolean validarAlumno(String rutAlumno)
    {
        for (int i = 0; i < this.cursos.size() ; i++ )
        {
            ArrayList<String> rutsAlumnos = cursos.get(i).getListaRuts();
            for(int j=0 ; j<rutsAlumnos.size(); j++)
            {
                if(rutsAlumnos.get(j).toLowerCase().equals(rutAlumno.toLowerCase()))
                    return true;
            }
        }
        
        return false;
    }
    public boolean validarAlumnoEnCurso(String nombreCurso, String rutAlumno)
    {
        for (int i = 0; i < this.cursos.size() ; i++ )
        {
            if ((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                ArrayList<String> rutsAlumnos = cursos.get(i).getListaRuts();
                for(int j=0 ; j<rutsAlumnos.size(); j++)
                {
                    if(rutsAlumnos.get(j).toLowerCase().equals(rutAlumno.toLowerCase()))
                        return true;
                }
            }
        }
        return false;
    }
    public boolean validarAlumnoEvaluacion(String rutAlumno)
    {
        String nombreCurso = buscarAlumno(rutAlumno);
        for (int i = 0; i < this.cursos.size() ; i++ )
        {
            if ((cursos.get(i).getNombreCurso()).equals(nombreCurso))
            {
                ArrayList<AlumnoMatricula> estados = this.cursos.get(i).getEstadoCurso();
                for (int j = 0 ; j < estados.size() ; j++)
                {
                    if (estados.get(j).getRutPersona().toLowerCase().equals(rutAlumno.toLowerCase()))
                    {
                        if (estados.get(j) instanceof Aprobado)
                        {
                            Aprobado alumno = (Aprobado)estados.get(j);
                            if (alumno.getBeca()!=true)
                            {
                                return true;
                            }else
                            {
                                return false;
                            }
                        }else if (estados.get(j) instanceof Repitente)
                        {
                            Repitente alumno = (Repitente)estados.get(j);
                            if (alumno.getRendirEvaluacionFinal())
                            {
                                return true;
                            }else
                            {
                                return false;
                            }
                        }    
                    }
                }
            }
        }
        return false;
    }
    public boolean verificarAsigDeCurso(String nombreCurso)
    {
        for (int i = 0; i < this.cursos.size(); i++ ){
            if ((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                ArrayList <String> listaNombresAsig = cursos.get(i).getListaNombresAsig();
                if (listaNombresAsig.isEmpty()){
                    return false;
                }else
                {
                    return true;
                }
            }
        }
        return false;
    }
     public boolean verificarAlumnosDeCurso(String nombreCurso)
    {
        for (int i = 0; i < this.cursos.size(); i++ ){
            if ((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                ArrayList <String> listaAlumnos = cursos.get(i).getListaRuts();
                if (listaAlumnos.isEmpty()){
                    return false;
                }else
                {
                    return true;
                }
            }
        }
        return false;
    }
    public String buscarAlumno(String rutAlumno)
    {
        String curso = null;
        for (int i = 0; i < this.cursos.size() ; i++ )
        {
            ArrayList<String> rutsAlumnos = this.cursos.get(i).getListaRuts();
            for(int j=0 ; j<rutsAlumnos.size(); j++)
            {
                if(rutsAlumnos.get(j).toLowerCase().equals(rutAlumno.toLowerCase()))
                    curso = this.cursos.get(i).getNombreCurso();
            }
        }
        return curso;
    }
    
    
    public void addCurso(String nombreCurso)
    {
        
        cursos.add(new Curso(nombreCurso));
        this.listaCursos.add(nombreCurso);
        guardarCursoEnCSV(nombreCurso);
    }
    public void addAlumnoACurso(String nombreCurso, String rutAlumno)
    {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                cursos.get(i).addAlumno(rutAlumno);
                guardarAlumnoEnCSV(cursos.get(i).getNombreCurso(),rutAlumno);
            }
        }
    }
    public void addAsignaturaACurso(String nombreCurso, String nombreAsig , String nombreUnidad) {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                cursos.get(i).addAsignatura(nombreAsig,nombreUnidad);
                
                ArrayList<String> alumnos = this.cursos.get(i).getListaRuts();
                for (int j = 0 ; j < alumnos.size(); j++)
                {
                    addNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, alumnos.get(j), 0.0, true);
                }
            }
        }
        guardarAsignaturaEnCSV(nombreCurso,nombreAsig,nombreUnidad);
    }
    public void addUnidadAAsignatura(String nombreCurso, String nombreAsig , String nombreUnidad) {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                cursos.get(i).addUnidadAsignatura(nombreAsig,nombreUnidad);
                ArrayList<String> alumnos = this.cursos.get(i).getListaRuts();
                for (int j = 0 ; j < alumnos.size(); j++)
                {
                    addNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, alumnos.get(j), 0.0, true);
                }
            }
        }
        guardarUnidadEnCSV(nombreCurso,nombreAsig,nombreUnidad);
    }
    public void addPreguntaUnidad(String nombreCurso, String nombreAsig, String nombreUnidad, String pregunta, boolean guardarEnCSV)
    {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
                cursos.get(i).addPreguntaAsig(nombreAsig, nombreUnidad, pregunta);
        }
        if (guardarEnCSV) 
            guardarPreguntaEnCSV(nombreCurso, nombreAsig, nombreUnidad, pregunta);
    }
    public void addNotaAlumno(String nombreCurso,String nombreAsig, String nombreUnidad,String rutAlumno,double notaAGuardar,boolean inicializacion)
    {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase())){
                cursos.get(i).addNotaAlumno(nombreAsig, nombreUnidad, rutAlumno,notaAGuardar,inicializacion);
            }
        }
    }
    
    //funcion que añade la nota final a la clase correspondiente (aprobado/repitente)
    
    public void addNotaEvaluacionFinal(String rutAlumno,double notaAlumno)
    {
        String nombreCurso = buscarAlumno(rutAlumno);
        for (int i = 0; i < this.cursos.size() ; i++ )
        {
            if ((cursos.get(i).getNombreCurso()).equals(nombreCurso))
            {
                ArrayList<AlumnoMatricula> estados = this.cursos.get(i).getEstadoCurso();
                for (int j = 0 ; j < estados.size() ; j++)
                {
                    if (estados.get(j).getRutPersona().toLowerCase().equals(rutAlumno.toLowerCase()))
                    {
                        // si la nota pertenece a un aprobado, y la nota es  6.5, solamente se reemplaza la variable beca, por true
                        if (estados.get(j) instanceof Aprobado)
                        {
                            Aprobado alumno = (Aprobado)estados.get(j);
                            alumno.evaluacionFinal(notaAlumno);
                            
                        }// En cambio si es un repitente, y la suma del 40% de su nota final mas el 60% de su promedio final es mayor a 4.5, pertenecerá a la clase Aprobado ("situacionAcademica" = true).
                        // todos sus datos se guardaran en alumnoAprobado y guardada en la listaEstadoAlumno de Curso
                        else if (estados.get(j) instanceof Repitente)
                        {
                            Repitente alumno = (Repitente)estados.get(j);
                            alumno.evaluacionFinal(notaAlumno);
                            if (alumno.getSituacionAcademica())
                            {
                                Aprobado alumnoAprobado = new Aprobado(alumno.getRutPersona(),alumno.getPromedioFinal(),false,false);
                                this.cursos.get(i).replaceAlumnoMatricula(alumno,alumnoAprobado);
                            }
                        }    
                    }
                }
            }
        }
    }
    public ArrayList<String> getNombreCursos()
    {
        ArrayList<String> nombreCursos = new ArrayList<>();
        for (int i = 0 ; i < this.listaCursos.size(); i++)
            nombreCursos.add(this.listaCursos.get(i));
        return nombreCursos;
    }
    public ArrayList<String> getRutAlumnos(String curso)
    {
        ArrayList<String> rutAlumnos = new ArrayList<>();
        for(int i = 0 ; i < this.cursos.size(); i++)
        {
            if (curso.equals(this.cursos.get(i).getNombreCurso()))
            {
                rutAlumnos=this.cursos.get(i).getListaRuts();
                break;
            }
        }
        return rutAlumnos;
    }
    public ArrayList<String> getNombresAsignaturas(String curso)
    {
        ArrayList<String> listaAsignaturas = new ArrayList<>();
        for(int i = 0 ; i < this.cursos.size(); i++)
        {
            if (curso.equals(this.cursos.get(i).getNombreCurso()))
            {
                listaAsignaturas = this.cursos.get(i).getListaNombresAsig();
                break;
            }
        }
        return listaAsignaturas;
    }
    public ArrayList<String> getUnidadesAsignatura(String curso,String asig)
    {
        ArrayList<String> listaUnidades = new ArrayList<>();
        for(int i = 0 ; i < this.cursos.size(); i++)
        {
            if (curso.equals(this.cursos.get(i).getNombreCurso()))
            {
                listaUnidades = this.cursos.get(i).getListaNombresUnidades(asig);
                break;
            }
        }
        return listaUnidades;
    }
    public ArrayList<Double> getNotasAlumno (String nombreCurso, String rutAlumno)
    {
        ArrayList<Double> notasAlumno = new ArrayList<>();
        for(int i = 0 ; i < this.cursos.size(); i++)
        {
            if (nombreCurso.equals(this.cursos.get(i).getNombreCurso()))
            {
                notasAlumno = this.cursos.get(i).getNotas(rutAlumno);
                break;
            }
        }
        return notasAlumno;
    }
    public double getNotaAlumno (String nombreCurso, String nombreAsig, String nombreUnidad, String rutAlumno)
    {
        double nota=0.0;
        for(int i = 0 ; i < this.cursos.size(); i++)
        {
            if (nombreCurso.toLowerCase().equals(this.cursos.get(i).getNombreCurso().toLowerCase()))
            {
                nota = this.cursos.get(i).getNotaAlumno(nombreAsig,nombreUnidad,rutAlumno);
                break;
            }
        }
        return nota;
    }
    public ArrayList<String> getBancoPreguntas (String curso, String Asig, String Unidad)
    {
        ArrayList<String> preguntas = new ArrayList<>();
        for(int i = 0 ; i < this.cursos.size(); i++)
        {
            if (curso.equals(this.cursos.get(i).getNombreCurso()))
            {
                preguntas = this.cursos.get(i).getListaPreguntasAsig(Asig, Unidad);
            }
        }
        return preguntas;
    }
    // para los promedios se utiliza la dependencia org.apache.commons de matematicas para aproximar los numeros decimales
    // metodo que promedia notas de una unidad, las notas 0.0 (sin nota) no son consideradas
    public double getPromedioNotasUnidad(String nombreCurso, String nombreAsig, String nombreUnidad)
    {
        double totalNotas = 0.0 , nota, promedioNotas=0.0;
        int alumnosConNota=0;
        for(int i = 0 ; i < this.cursos.size(); i++)
        {
            if (nombreCurso.toLowerCase().equals(this.cursos.get(i).getNombreCurso().toLowerCase()))
            {
                ArrayList<String> alumnos = this.cursos.get(i).getListaRuts();
                for (int j =0 ; j < alumnos.size() ; j++)
                {
                    nota = getNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, alumnos.get(j));
                    
                    //si la nota es 0.0 (sin nota) no se considera en el promedio, es por eso que es necesaria 
                    //la variable alumnosConNota, para saber con que número obtener el promedio
                    if (getNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, alumnos.get(j))!= 0.0)
                    {
                        totalNotas += nota;
                        alumnosConNota++;
                    }
                }
                // si la unidad no tiene notas en ella, el promedio tambien es 0.0
                if (totalNotas==0.0)
                {
                    promedioNotas = 0.0;
                }else{
                    promedioNotas = totalNotas/alumnosConNota;
                }
            }
        }
        return Precision.round(promedioNotas,1);
    }
    public double getPromedioAlumno(String nombreCurso, String rutAlumno)
    {
        double notasAlumno=0.0;
        int cantNotas=0;
        for(int i = 0 ; i < this.cursos.size(); i++)
        {
            if (nombreCurso.toLowerCase().equals(this.cursos.get(i).getNombreCurso().toLowerCase())){
                ArrayList <Double> listaNotas =  this.cursos.get(i).getNotas(rutAlumno);
                for (int j = 0 ; j < listaNotas.size() ; j++)
                {
                    if (listaNotas.get(j) != 0.0){
                        notasAlumno += listaNotas.get(j);
                        cantNotas++;
                    }
                }
            }
            if (cantNotas>0)
            {
                return Precision.round(notasAlumno/cantNotas,1);
            }
        }
        return 0.0;
    }
    public boolean deleteAlumno (String rutAlumno)
    {
        String cursoAlumno = buscarAlumno(rutAlumno);
        for (int i = 0 ; i < this.cursos.size(); i++)
        {
            if (this.cursos.get(i).getNombreCurso().equals(cursoAlumno))
            {
                if(this.cursos.get(i).deleteAlumno(rutAlumno)){
                    deleteAlumnoCSV(rutAlumno);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean replaceAlumno(String rutOriginal, String rutNuevo)
    {
        String cursoAlumno = buscarAlumno(rutOriginal);
        for (int i = 0 ; i < this.cursos.size(); i++)
        {
            if (this.cursos.get(i).getNombreCurso().equals(cursoAlumno))
            {
                if(this.cursos.get(i).replaceAlumno(rutOriginal,rutNuevo)){
                    replaceAlumnoCSV(rutOriginal,rutNuevo);
                    return true;
                }
            }
        }
        return false;
    }
    
    
    
}
