
package com.mycompany.proyectopoo;

import java.util.ArrayList;
import java.io.IOException;
import static com.mycompany.proyectopoo.ManejoDeCSV.*;
import org.apache.commons.math3.util.Precision;//se utiliza la dependencia org.apache.commons de matematicas para aproximar los numeros decimales con Precision.round

public class ManejoDeCursos implements EstadoMatricula{
         //Se crea un arrayList para guardar los cursos que se leeran del csv en la funcion leerParametroCSV
    private ArrayList<String> listaCursos;
    private ArrayList<Curso> cursos;

    // constructor, lee los nombres de los cursos mediante el csv "Cursos.csv"
    ManejoDeCursos(){
        this.listaCursos = leerParametroCSV("Curso","Cursos.csv");
        this.cursos = new ArrayList<>();
    }
        
        //se crean los objetos cursos segun los datos guardados en el csv  
    public void crearCursos()throws IOException, InvalidNotaInitializationException  {
        
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
        
        //se leen y guardan las notas almacenadas en el CSV
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
    
    // método que lee un banco de preguntas desde "BancoPreguntas.csv" de un curso específico y lo guarda en ram mediante el método addPreguntaUnidad
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
    
    // método que lee desde el CSV "Notas.csv", las notas respectivas de cada alumno y las guarda en ram mediante el método addNotaAlumno
    private void leerNotasCSV (Curso curso) throws InvalidNotaInitializationException
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
    // Método que muestra por consola los nombres de todos los cursos guardados en el sistema
    public void mostrarNombreCursos()
    {
        for (int i = 0; i < this.cursos.size(); i++ ){
            System.out.println(this.cursos.get(i).getNombreCurso());
        }
    }
    
    // método que muestra por consola todos los ruts de los alumnos guardados en el sistema agrupados por sus cursos respectivos
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
    
    //método que muestra por consola los ruts de alumnos de un curso guardado en el sistema
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
    
    //método que muestra por consola los nombres de las asignaturas de un curso específico
    public void mostrarAsignaturas(String nombreCurso)
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
    
    // método que muestra por consola las Unidades de una asignatura y curso específico
    public void mostrarUnidades(String nombreCurso,String nombreAsig)
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
    //método que muestra por consola las unidades y asignaturas de todos los cursos en el sistema
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
    
    
    /*------Método sin utilizar, fue reemplazado por una ventana----------
    método que muestra las asignaturas y unidades de un curso específico*/
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
    
    // método que muesta por consola un banco de preguntas de una unidad de una asignatura de un curso en el sistema
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
    
    // método que muestra por consola las notas de todos los alumnos de un curso en el sistema
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
                break;
            }
        }
    }
    
    // método que muestra por consola las notas de todos los alumnos de una unidad de una asignatura de un curso en el sistema
    public void mostrarAlumnosYNotasUnidad(String nombreCurso, String nombreAsig, String nombreUnidad)
    {
        for (int i = 0 ; i < this.cursos.size() ; i++)
        {
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                ArrayList<String> listaAlumnos = this.cursos.get(i).getListaRuts();
                for (int j = 0 ; j < listaAlumnos.size() ; j++)
                {
                    System.out.print("Alumno " + j + ": " + listaAlumnos.get(j) + " Nota de Unidad " + nombreUnidad + ": ");
                    if (getNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, listaAlumnos.get(j)) == 0.0)
                    {
                        System.out.println("s/n");
                    }
                    else
                    {
                        System.out.println(getNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, listaAlumnos.get(j)));
                    }
                }
                break;
            }
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
                            AlumnoAprobado a = new AlumnoAprobado(rutsAlumnos.get(j),getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j)),true,false);
                            cursos.get(i).addEstadoAlumno(a);
                        }
                        else{
                            // los aprobados que no tengan su beca, podrán rendir la prueba, si su nota es mayora a 6.5 obtienen beca  (false(beca),true(prueba))
                            AlumnoAprobado a = new AlumnoAprobado(rutsAlumnos.get(j),getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j)),false,true);
                            cursos.get(i).addEstadoAlumno(a);
                        }
                    }
                    // los repitentes que tengan un promedio >= 3 tienen tienen una posibildiad de dar un examen, si su nota cumple con los parametros, su situacionAcademica cambia a true(pasa de curso)
                    // (false(repiten), true(examen)
                    if((getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j))) < 4){
                        if((getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j))) >= 3){
                            AlumnoReprobado a = new AlumnoReprobado(rutsAlumnos.get(j),getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j)), false,true);
                            cursos.get(i).addEstadoAlumno(a);
                        }
                        else{
                            // si el alumno no tiene notas (no se puede sacar su promedio), se le asigna promedio 1.0 y no tiene derecho a examen por tanto repite curso (false,false)
                            if (getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j))==0.0)
                            {
                                AlumnoReprobado a = new AlumnoReprobado(rutsAlumnos.get(j),1.0,false,false);
                                cursos.get(i).addEstadoAlumno(a);
                            }else
                            {
                                AlumnoReprobado a = new AlumnoReprobado(rutsAlumnos.get(j),getPromedioAlumno(nombresCursos.get(i), rutsAlumnos.get(j)),false,false);
                                cursos.get(i).addEstadoAlumno(a);
                            }
                        }
                    }
                }
            }
        }
    }    
    
    // método que muestra los alumnos que pueden rendir la evaluacion final (RendirEvaluacionFinal == true)
    public void mostrarAlumnosEvaluacionFinal()
    {
        for (int i = 0; i < this.cursos.size(); i++ )
        {
            ArrayList <AlumnoMatricula> estados =  this.cursos.get(i).getEstadoCurso();
            for (int j =0 ; j < estados.size() ; j++)
            {
                if (estados.get(j) instanceof AlumnoAprobado)
                {
                    AlumnoAprobado estadoAlumno = (AlumnoAprobado)estados.get(j);
                    if(estadoAlumno.getRendirEvaluacionFinal()){
                        System.out.println("Alumno: "+estadoAlumno.getRutPersona()+ " Promedio Final: " + estadoAlumno.getPromedioFinal()+" Estado: APROBADO SIN BECA");
                        System.out.print("\n");
                    }
                }else if (estados.get(j) instanceof AlumnoReprobado)
                {
                    AlumnoReprobado estadoAlumno = (AlumnoReprobado)estados.get(j);
                    if(estadoAlumno.getRendirEvaluacionFinal())
                    {
                        System.out.println("Alumno: "+estadoAlumno.getRutPersona()+ " Promedio Final: " + estadoAlumno.getPromedioFinal()+" Estado: REPROBADO CON DERECHO A EXAMEN");
                        System.out.print("\n");
                    }
                }
            }
        }
    }
    
    
    

    //método que valida si un curso está guardado en el sistema, retorna true en caso positivo y false en caso negativo 
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
    
    // método que valida si una asignatura está guardada en un curso del sistema, retorna true en caso positivo y false en caso negativo 
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
    
    // método que valida si una unidad está guardada en una asignatura de un curso del sistema, retorna true en caso positivo y false en caso negativo 
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
    
    //método que valida si un alumno está guardado en algún curso del sistema, retorna true en caso positivo y false en caso negativo 
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
    
    // método que valida si una alumno pertenece a un curso, retorna true en caso positivo y false en caso negativo
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
    
    // metodo que válida si un alumno es apto para rendir la evaluacion final, retornando true en caso que pueda rendirla
    // o false en caso contrario
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
                        if (estados.get(j) instanceof AlumnoAprobado)
                        {
                            AlumnoAprobado alumno = (AlumnoAprobado)estados.get(j);
                            if (alumno.getBeca()!=true)
                            {
                                return true;
                            }else
                            {
                                return false;
                            }
                        }else if (estados.get(j) instanceof AlumnoReprobado)
                        {
                            AlumnoReprobado alumno = (AlumnoReprobado)estados.get(j);
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
    
    // método que verifica si un curso posee asignaturas guardadas, retornando false en caso de no tener asignaturas, 
    // o true en caso de tenerlas
    public boolean verificarAsigDeCurso(String nombreCurso)
    {
        for (int i = 0 ; i < this.cursos.size() ; i++ ){
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
    
    // método que verifica si un curso posee alumnos guardados, retornando false en caso de no tener alumnos, 
    // o true en caso de tenerlos
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
     
    // método que busca en todos los cursos un rut de un alumno, retorna el nombre del curso correspondiente en caso de encontrarlo,
    // o de lo contrario, retorna null 
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
    
    // método que agrega un curso al sistema, tanto al csv con el método guardarCursoEnCSV, como en ram
    public void addCurso(String nombreCurso)
    {
        cursos.add(new Curso(nombreCurso));
        this.listaCursos.add(nombreCurso);
        guardarCursoEnCSV(nombreCurso);
    }
    
    // método que agrega un alumno a un curso, tanto al csv con el método guardarAlumnoEnCSV, como en ram
    public void addAlumnoACurso(String nombreCurso, String rutAlumno) throws InvalidNotaInitializationException
    {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                cursos.get(i).addAlumno(rutAlumno);
                guardarAlumnoEnCSV(cursos.get(i).getNombreCurso(),rutAlumno);
            }
        }
    }
    
    // método que agrega una asignatura y una unidad a un curso, tanto al csv con el método guardarAsignaturaEnCSV, como en ram
    public void addAsignaturaACurso(String nombreCurso, String nombreAsig , String nombreUnidad) throws InvalidNotaInitializationException {
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
    
    // método que agraga una unidad a una asignatura de un curso, tanto al csv con el método guardarUnidadEnCSV, como en ram
    public void addUnidadAAsignatura(String nombreCurso, String nombreAsig , String nombreUnidad) throws InvalidNotaInitializationException {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                cursos.get(i).addUnidadAsignatura(nombreAsig,nombreUnidad);
                ArrayList<String> alumnos = cursos.get(i).getListaRuts();
                for (int j = 0 ; j < alumnos.size(); j++)
                {
                    addNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, alumnos.get(j), 0.0, true);
                }
            }
        }
        guardarUnidadEnCSV(nombreCurso,nombreAsig,nombreUnidad);
    }
    
    // método que agrega una pregunta al banco de preguntas de una unidad guardandola siempre en ram, pero dependiendo del valor de guardarEnCSV,
    // se guardará o no en el csv. (Ya que este método se utiliza para inicializar el banco de preguntas al iniciar el programa y para guardar preguntas en este)
    public void addPreguntaUnidad(String nombreCurso, String nombreAsig, String nombreUnidad, String pregunta, boolean guardarEnCSV)
    {
        for (int i = 0 ; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
                cursos.get(i).addPreguntaAsig(nombreAsig, nombreUnidad, pregunta);
        }
        if (guardarEnCSV) 
            guardarPreguntaEnCSV(nombreCurso, nombreAsig, nombreUnidad, pregunta);
    }
    
    // método que agrega una nota de una unidad a un alumno específico, el boolean es utilizado para que al iniciar el programa,
    // la nota pueda tomar un valor 0.0 (sin nota)
    public void addNotaAlumno(String nombreCurso,String nombreAsig, String nombreUnidad,String rutAlumno,double notaAGuardar,boolean inicializacion) throws InvalidNotaInitializationException
    {
        for (int i = 0 ; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase())){
                cursos.get(i).addNotaAlumno(nombreAsig, nombreUnidad, rutAlumno,notaAGuardar,inicializacion);
            }
        }
    }
    
    //método que añade la nota final a la clase correspondiente (aprobado/repitente)
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
                        if (estados.get(j) instanceof AlumnoAprobado)
                        {
                            AlumnoAprobado alumno = (AlumnoAprobado)estados.get(j);
                            alumno.evaluacionFinal(notaAlumno);
                            
                        }// En cambio si es un repitente, y la suma del 40% de su nota final mas el 60% de su promedio final es mayor a 4.5, pertenecerá a la clase Aprobado ("situacionAcademica" = true).
                        // todos sus datos se guardaran en alumnoAprobado y guardada en la listaEstadoAlumno de Curso
                        else if (estados.get(j) instanceof AlumnoReprobado)
                        {
                            AlumnoReprobado alumno = (AlumnoReprobado)estados.get(j);
                            alumno.evaluacionFinal(notaAlumno);
                            if (alumno.getSituacionAcademica())
                            {
                                AlumnoAprobado alumnoAprobado = new AlumnoAprobado(alumno.getRutPersona(),alumno.getPromedioFinal(),false,false);
                                this.cursos.get(i).replaceAlumnoMatricula(alumno,alumnoAprobado);
                            }
                        }    
                    }
                }
            }
        }
    }
    
    //método para obtener una lista con los nombres de los cursos
    public ArrayList<String> getNombreCursos()
    {
        ArrayList<String> nombreCursos = new ArrayList<>();
        for (int i = 0 ; i < this.listaCursos.size(); i++)
            nombreCursos.add(this.listaCursos.get(i));
        return nombreCursos;
    }
    
    //método para obtener una lista con los ruts de los alumnos de un curso
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
    
    //método para obtener una lista con los nombres de las Asignaturas de un curso
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
    
    //método para obtener una lista con los nombres de las Unidades de una asignatura de un curso
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
    
    // método para obtener una lista de double con las notas de un alumno de un curso
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
    
    // método para obtener una nota de una unidad de un alumno
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
    
    //método para obtener una lista con las preguntas de una Unidad 
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
    
    // método para obtener el promedio de un alumno de un curso
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
            // evitar división por 0
            if (cantNotas>0)
            {
                return Precision.round(notasAlumno/cantNotas,1);
            }
        }
        return 0.0;
    }
    
    //metodo que retorna un int segun el estado actual del alumno
    //1: alumno aprobado y con beca
    //2: alumno aprobado y sin beca
    //3: alumno reprobado y con examen
    //4: alumno reprobado y sin examen
    //0: desconocido
    public int getEstadoAlumno(String rutAlumno)
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
                        
                        if (estados.get(j) instanceof AlumnoAprobado)
                        {
                            AlumnoAprobado alumno = (AlumnoAprobado)estados.get(j);
                            if (alumno.getBeca())
                            {
                                return 1;
                            }else
                            {
                                return 2;
                            }
                        }
                        else if (estados.get(j) instanceof AlumnoReprobado)
                        {
                            AlumnoReprobado alumno = (AlumnoReprobado)estados.get(j);
                            if (alumno.getRendirEvaluacionFinal()){
                                return 3;
                            }else{
                                return 4;
                            }
                                
                        }    
                    }
                }
            }
        }
        return 0;
    }
    
    // método para eliminar un alumno del csv mediante el método deleteAlumnoCSV y de ram, retorna true en caso de éxito, 
    // o en caso contrario, false
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
    
    // método para reemplazar un rut de un alumno del csv mediante el método deleteAlumnoCSV y de ram, retorna true en caso de éxito, 
    // o en caso contrario, false
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
    // implementacion de método contratados por la interfaz EstadoMatricula
    public void reportarEstado(double promedio){
        String estado;
        if(promedio >= 4.0){
            estado = "Aprobado";
        }
        else{
            if(promedio == 0.0){
            estado = "Desconocido";
            }
            else{
                estado = "Reprobado";
            }
        }
        System.out.println(estado);
    }
    // implementacion de método contratados por la interfaz EstadoMatricula
    public void actualizarEstado(int estado)
    {
        switch(estado)
        {
            case 0:
                System.out.println("Desconocido");
                break;
            case 1:
                System.out.println("Aprobado"+" CON BECA");
                break;
            case 2:
                System.out.println("Aprobado"+" SIN BECA");
                break;
            case 3:
                System.out.println("Reprobado");
                break;
            case 4:
                System.out.println("Reprobado");
                break;
        }
        System.out.println("\n");
    }
    
    // implementación de el patron Strategy
    public void setFormatoEstado(FormatoEstadoAlumnos formato)
    {
        ArrayList<Curso> auxCursos = new ArrayList<>();
        auxCursos.addAll(this.cursos);
        formato.mostrarEstadoAlumnos(auxCursos);
    }
    
}
