
package com.mycompany.proyectopoo;

import java.util.ArrayList;
import java.io.IOException;
import static com.mycompany.proyectopoo.ManejoDeCSV.*;
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
                    curso = new Curso(nombreCurso,leerParametroCursoCSV(nombreCurso,"Rut","Alumnos.csv"));
                    break;
                case 3: //Solamente el nombre del curso, al menos una asignatura y una unidad de dicha asignatura estan guardados en los csv
                    curso = new Curso(nombreCurso,leerParametroCursoCSV(nombreCurso, "Asignaturas","Cursos.csv").get(0).split(","),leerParametroCursoCSV(nombreCurso,"Unidades","Cursos.csv").get(0).split(" "));   
                    break;
                case 4: //El curso tiene guardado al menos un alumno, asignatura y una unidad de dicha asignatura
                    curso = new Curso(nombreCurso,leerAlumnosCursoCSV(nombreCurso,"Alumnos.csv"), leerParametroCursoCSV(nombreCurso, "Asignaturas","Cursos.csv").get(0).split(","), leerParametroCursoCSV(nombreCurso,"Unidades","Cursos.csv").get(0).split(" "));
                    break;
            }
            this.cursos.add(curso);
        }
        
        //se leen los bancos de preguntas (si tienen) de las unidades de cada asignatura de cada curso 
        for (int i = 0; i < this.cursos.size(); i++ )
            leerBancoDePreguntas(cursos.get(i));
        crearMapAlumnos(cursos);
    }
    
        //funcion que retorna un int que determina que constructor se utiliza para el objeto curso, según los datos presentes en los csv
    public int tipoLlenado(String nombreCurso)
    {
        int caso;
        if (leerAlumnosCursoCSV(nombreCurso,"Alumnos.csv").isEmpty()){
            if (leerParametroCursoCSV(nombreCurso, "Asignaturas","Cursos.csv").get(0).equals("none")){
                caso=1;
            }else{
                caso=3;
            }
        }else{
            if (leerParametroCursoCSV(nombreCurso, "Asignaturas","Cursos.csv").get(0).equals("none")){
                caso=2;
            }else{
                caso=4;
            }
        }
        return caso;
    }
    
    private void leerBancoDePreguntas(Curso curso) {
        ArrayList<String> listaPreguntas = leerParametroCursoCSV(curso.getNombreCurso(),"Asignatura,Unidad,Pregunta","BancoPreguntas.csv");
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
                        return;
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
        System.out.println("'"+nombreUnidad+"' no fue encontrado en la Asignatura " +nombreAsig+ ", intente nuevamente." );
        System.out.print("\n");
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
    public void addPreguntaUnidad(String nombreCurso, String nombreAsig, String nombreUnidad, String pregunta, boolean guardarEnCSV)
    {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
                cursos.get(i).addPreguntaAsig(nombreAsig, nombreUnidad, pregunta);
        }
        if (guardarEnCSV) 
            guardarPreguntaEnCSV(nombreCurso, nombreAsig, nombreUnidad, pregunta);
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
            
    public void mostrarBancoPreguntasUnidad(String nombreCurso, String nombreAsig, String nombreUnidad) {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                ArrayList <String> listaPreguntas = new ArrayList <String>();
                listaPreguntas.addAll(cursos.get(i).getListaPreguntasAsig(nombreAsig, nombreUnidad));
                for (int j = 0 ; j<listaPreguntas.size();j++)
                    System.out.println("Pregunta "+(j+1)+": "+listaPreguntas.get(j));
            }
        }
    }
    public void addCurso(String nombreCurso)
    {
        
        cursos.add(new Curso(nombreCurso));
        this.listaCursos.add(nombreCurso);
        guardarCursoEnCSV(nombreCurso);
    }
    public void addAsignaturaACurso(String nombreCurso, String nombreAsig , String nombreUnidad) {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                cursos.get(i).addAsignatura(nombreAsig,nombreUnidad);
            }
        }
        guardarAsignaturaEnCSV(nombreCurso,nombreAsig,nombreUnidad);
    }
    public void addUnidadAAsignatura(String nombreCurso, String nombreAsig , String nombreUnidad) {
        for (int i = 0; i < this.cursos.size() ; i++ ){
            if((cursos.get(i).getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                cursos.get(i).addUnidadAsignatura(nombreAsig,nombreUnidad);
            }
        }
        guardarUnidadEnCSV(nombreCurso,nombreAsig,nombreUnidad);
    }

    public void crearMapAlumnos(ArrayList<Curso> cursos) {
        for (int i = 0; i < cursos.size(); i++ ){
            for (int z = 0 ; z < cursos.get(i).getListaRuts().size() ; z++)
            {
                ArrayList<Double> notasAlumno= new ArrayList<>();
                for(int j = 0; j < cursos.get(i).getListaNombresAsig().size(); j++ )
                {
                    for(int k = 0; k < cursos.get(i).getListaNombresUnidades(cursos.get(i).getListaNombresAsig().get(j)).size(); k++ )
                    {
                        ArrayList<Double> listaNotas = cursos.get(i).getListaNotasAsig(cursos.get(i).getListaNombresAsig().get(j), cursos.get(i).getListaNombresUnidades(cursos.get(i).getListaNombresAsig().get(j)).get(k));
                        notasAlumno.add(listaNotas.get(z));
                    }
                }
                cursos.get(i).guardarNotasCurso(cursos.get(i).getListaRuts().get(z), notasAlumno);
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

    


    
}
