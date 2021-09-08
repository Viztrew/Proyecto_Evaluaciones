
package com.mycompany.proyectopoo;

import java.util.ArrayList;
import java.io.IOException;
import static com.mycompany.proyectopoo.ManejoDeCSV.*;
public class ManejoDeCursos {
         //Se crea un arrayList para guardar los cursos que se leeran del csv en la funcion leerParametroCSV
    private ArrayList<String> listaCursos;
    private Curso[] cursos;
        
    ManejoDeCursos(){
        this.listaCursos = leerParametroCSV("Curso","Alumnos.csv");
        this.cursos = new Curso[listaCursos.size()];
    }
        
        //se crean los cursos y se ingresan al la lista de objetos de tipo Curso       
    public void crearCursos()throws IOException  {
            
        for (int i = 0; i < this.cursos.length; i++ ){
            /*como las asignaturas y las unidades se guardan de manera diferente en el csv se deben separan en partes
              las asignaturas se separan una vez por comas y las unidades se separan dos veces, la primera por espacios
              la segunda por comas*/
            ArrayList<String> listaAsig = leerParametroCursoCSV(this.listaCursos.get(i), "Asignaturas","Alumnos.csv");
            ArrayList<String> listaUnid = leerParametroCursoCSV(this.listaCursos.get(i),"Unidades","Alumnos.csv");
            Curso curso = new Curso(this.listaCursos.get(i),leerParametroCursoCSV(this.listaCursos.get(i),"Rut","Alumnos.csv"), listaAsig.get(0).split(","), listaUnid.get(0).split(" "));
            
            this.cursos[i] = curso;
        }
        for (int i = 0; i < this.cursos.length; i++ )
            leerBancoDePreguntas(cursos[i]);
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
        for (int i = 0; i < this.cursos.length; i++ ){
            System.out.println(this.cursos[i].getNombreCurso());
        }
    }
    
    public void mostrarNombresAsig(String nombreCurso)
    {
        for (int i = 0; i < this.cursos.length; i++ ){
            if ((cursos[i].getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                ArrayList <String> listaNombresAsig = cursos[i].getListaNombresAsig();
                for(int j = 0; j < listaNombresAsig.size(); j++ )
                    System.out.println("Asignatura "+(j+1)+":"+listaNombresAsig.get(j));
                return;
            }
        }
    }
    
    public void mostrarNombresUnidades(String nombreCurso,String nombreAsig)
    {
        
        for (int i = 0; i < this.cursos.length; i++ )
        {
            if ((cursos[i].getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                if (validarAsignatura(nombreCurso,nombreAsig))
                {
                    ArrayList <String> listaNombresAsig = cursos[i].getListaNombresAsig();
                    for(int j = 0; j < listaNombresAsig.size(); j++ )
                    {
                        if((listaNombresAsig.get(j)).toLowerCase().equals(nombreAsig.toLowerCase()))
                        {
                            ArrayList <String> listaNombresUnidades = this.cursos[i].getListaNombresUnidades(listaNombresAsig.get(j));
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
        for (int i = 0; i < this.cursos.length; i++ )
        {
            System.out.println("Alumnos de " + cursos[i].getNombreCurso()+ ": ");
            ArrayList<String> rutsAlumnos = cursos[i].getListaRuts();
            for(int j=0 ; j<rutsAlumnos.size(); j++)
                System.out.println("Alumno " + (j+1) + ": " + rutsAlumnos.get(j));
            System.out.print("\n");
        }
    }
    
    public void mostrarAlumnosCurso(String nombreCurso)
    {
        if(validarCurso(nombreCurso))
        {
            for (int i = 0; i < this.cursos.length; i++ )
            {
                if ((cursos[i].getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
                {
                    System.out.println("Alumnos de " + cursos[i].getNombreCurso()+ ": ");
                    ArrayList<String> rutsAlumnos = cursos[i].getListaRuts();
                    for(int j=0 ; j<rutsAlumnos.size(); j++)
                        System.out.println("Alumno " + (j+1) + ": " + rutsAlumnos.get(j));
                    return;
                }
            }
        }
        
        System.out.println("'"+nombreCurso+"' no fue encontrado, intente nuevamente." );
    }
            
    public void mostrarTodosAsigYUnidades()
    {
        for (int i = 0; i < this.cursos.length; i++ ){
            System.out.println("Asignaturas y Unidades Curso "+ cursos[i].getNombreCurso()+":");
            ArrayList <String> listaNombresAsig = cursos[i].getListaNombresAsig();
            for(int j = 0; j < listaNombresAsig.size(); j++ )
            {
                ArrayList <String> listaNombresUnidades = this.cursos[i].getListaNombresUnidades(listaNombresAsig.get(j));
                System.out.println("Asignatura "+(j+1)+":"+listaNombresAsig.get(j));
                for(int k = 0; k < listaNombresUnidades.size(); k++ )
                    System.out.println("Unidad "+(k+1)+": " +listaNombresUnidades.get(k) );
            }
            System.out.print("\n");
        }
    }
    
    public void mostrarAsigYUnidadesCurso(String nombreCurso)
    {
        
        for (int i = 0; i < this.cursos.length; i++ )
        {
            if ((cursos[i].getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                System.out.println("Asignaturas y Unidades Curso "+ cursos[i].getNombreCurso()+":");
                ArrayList <String> listaNombresAsig = cursos[i].getListaNombresAsig();
                for(int j = 0; j < listaNombresAsig.size(); j++ )
                {
                    ArrayList <String> listaNombresUnidades = this.cursos[i].getListaNombresUnidades(listaNombresAsig.get(j));
                    System.out.println("Asignatura "+(j+1)+":"+listaNombresAsig.get(j));
                    for(int k = 0; k < listaNombresUnidades.size(); k++ )
                        System.out.println("Unidad "+(k+1)+": " +listaNombresUnidades.get(k) );
                }
                return;
            }
        }
    }
    
    public boolean validarCurso(String nombreCurso)
    {
        for (int i = 0; i < this.cursos.length; i++ ){
            if((cursos[i].getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
                return true;
        }
        System.out.println("'"+nombreCurso+"' no fue encontrado, intente nuevamente." );
        System.out.print("\n");
        return false;
    }
    
    public boolean validarAsignatura(String nombreCurso,String nombreAsig)
    {
        for (int i = 0; i < this.cursos.length; i++ ){
            if((cursos[i].getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                ArrayList<String> nombresAsignaturas = cursos[i].getListaNombresAsig();
                for (int j = 0; j < nombresAsignaturas.size(); j++ ){
                    if((nombresAsignaturas.get(j)).toLowerCase().equals(nombreAsig.toLowerCase()))
                        return true;
                }
            }
        }
        System.out.println("'"+nombreAsig+"' no fue encontrado en el curso " +nombreCurso+ ", intente nuevamente." );
        System.out.print("\n");
        return false;
    }
    
    public boolean validarUnidad(String nombreCurso,String nombreAsig,String nombreUnidad)
    {
        for (int i = 0; i < this.cursos.length; i++ ){
            if((cursos[i].getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                ArrayList <String> listaNombresAsig = cursos[i].getListaNombresAsig();
                for(int j = 0; j < listaNombresAsig.size(); j++ )
                {
                    if((listaNombresAsig.get(j)).toLowerCase().equals(nombreAsig.toLowerCase()))
                    {
                        ArrayList <String> listaNombresUnidades = this.cursos[i].getListaNombresUnidades(listaNombresAsig.get(j));
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
    
    public void addPreguntaUnidad(String nombreCurso, String nombreAsig, String nombreUnidad, String pregunta, boolean guardarEnCSV)
    {
        for (int i = 0; i < this.cursos.length; i++ ){
            if((cursos[i].getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
                cursos[i].addPreguntaAsig(nombreAsig, nombreUnidad, pregunta);
        }
        if (guardarEnCSV) 
                guardarPreguntaEnCSV(nombreCurso, nombreAsig, nombreUnidad, pregunta);
    }
    
    void mostrarBancoPreguntasUnidad(String nombreCurso, String nombreAsig, String nombreUnidad) {
        for (int i = 0; i < this.cursos.length; i++ ){
            if((cursos[i].getNombreCurso()).toLowerCase().equals(nombreCurso.toLowerCase()))
            {
                ArrayList <String> listaPreguntas = new ArrayList <String>();
                listaPreguntas.addAll(cursos[i].getListaPreguntasAsig(nombreAsig, nombreUnidad));
                for (int j = 0 ; j<listaPreguntas.size();j++)
                    System.out.println("Pregunta "+(j+1)+": "+listaPreguntas.get(j));
            }
        }
    }
    /*
    public void llenarPreguntasUnidades(Curso[] cursos) throws IOException 
    {
            //llena las preguntas de las unidades de las Asignaturas(j)  de los cursos(i)
        for (int i = 0; i < cursos.length; i++ ){
            for(int j = 0; j < cursos[i].getListaNombresAsig().size(); j++ )
                cursos[i].getListaNombresAsig().get(j).llenarPreguntasUnidad(cursos[i].getNombreCurso());
        }
    }

    public void llenarNotasUnidades(Curso[] cursos) throws IOException {
            //llena las notas de las unidades de las Asignaturas(j)  de los cursos(i)
        for (int i = 0; i < cursos.length; i++ ){
            for(int j = 0; j < cursos[i].getListaAsignaturas().size(); j++ )
                cursos[i].getListaAsignaturas().get(j).llenarNotasUnidad();
        }
    }

    public void mostrarPreguntasUnidades(Curso[] cursos) {
            //muestra las preguntas de las unidades(k) de las Asignaturas(j)  de los cursos(i)
        for (int i = 0; i < cursos.length; i++ ){
            for(int j = 0; j < cursos[i].getListaAsignaturas().size(); j++ )
            {
                for(int k = 0; k < cursos[i].getListaAsignaturas().get(j).getListaUnidades().size(); k++ )
                    cursos[i].getListaAsignaturas().get(j).getListaUnidades().get(k).mostrarPreguntas(cursos[i].getNombreCurso());
            }
        }
    }

    public void mostrarNotasUnidades(Curso[] cursos) {
            //muestra las notas de las unidades(k) de las Asignaturas(j)  de los cursos(i)
        for (int i = 0; i < cursos.length; i++ ){
            for(int j = 0; j < cursos[i].getListaAsignaturas().size(); j++ )
            {
                for(int k = 0; k < cursos[i].getListaAsignaturas().get(j).getListaUnidades().size(); k++ )
                    cursos[i].getListaAsignaturas().get(j).getListaUnidades().get(k).mostrarNotas(cursos[i].getNombreCurso());
            }
        }
    } 

    public void crearMapAlumnos(Curso[] cursos) {
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

    public void mostrarNotasAlumnos(Curso[] cursos) {
        for (int i = 0; i < cursos.length; i++ )
        {
            System.out.println("Notas curso "+cursos[i].getNombreCurso()+": ");
            for (int j = 0 ; j < cursos[i].getListaRutAlumnos().size() ; j++)
                System.out.println("Notas " + cursos[i].getListaRutAlumnos().get(j) + ": " + cursos[i].getNotasAlumnos().get(cursos[i].getListaRutAlumnos().get(j)));
        }
    }*/


    
}
