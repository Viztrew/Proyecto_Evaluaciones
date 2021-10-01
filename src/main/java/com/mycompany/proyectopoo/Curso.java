package com.mycompany.proyectopoo;
import java.util.ArrayList;
import java.util.HashMap;
public class Curso {
    //Instancias de clase
    private String nombreCurso;
    private ArrayList<Asignatura> listaAsignaturas;
    private ArrayList<String> listaRutAlumnos;
    private HashMap<String,ArrayList<Double>> mapaNotasAlumnos;
    
    //Constructor
    public Curso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
        this.mapaNotasAlumnos = new HashMap<>();
        
        this.listaRutAlumnos = new ArrayList<>();
        
        this.listaAsignaturas = new ArrayList<>();
    } 
    
    public Curso(String nombreCurso,ArrayList<String>listaRutAlumnos) {
        this.nombreCurso = nombreCurso;
        this.mapaNotasAlumnos = new HashMap<>();
        
        this.listaRutAlumnos = new ArrayList<>();
        this.listaRutAlumnos.addAll(listaRutAlumnos);
        
        this.listaAsignaturas = new ArrayList<>();
    } 
    
    public Curso(String nombreCurso, String [] asignaturasCurso , String [] unidadesAsigUnidos) {
        this.nombreCurso = nombreCurso;
        this.mapaNotasAlumnos = new HashMap<>();
        
        this.listaRutAlumnos = new ArrayList<>();
        
        this.listaAsignaturas = new ArrayList<>();
        for (int i = 0 ; i < asignaturasCurso.length ; i++){
            String[] unidadesAsigSeparados = unidadesAsigUnidos[i].split(",");
            Asignatura asig = new Asignatura(asignaturasCurso[i],unidadesAsigSeparados,this.listaRutAlumnos);
            this.listaAsignaturas.add(asig);
        }
    } 
    
    public Curso(String nombreCurso, ArrayList<String>listaRutAlumnos, String [] asignaturasCurso , String [] unidadesAsigUnidos) {
        this.nombreCurso = nombreCurso;
        this.mapaNotasAlumnos = new HashMap<>();
        
        this.listaRutAlumnos = new ArrayList<>();
        this.listaRutAlumnos.addAll(listaRutAlumnos);
        
        this.listaAsignaturas = new ArrayList<>();
        for (int i = 0 ; i < asignaturasCurso.length ; i++){
            String[] unidadesAsigSeparados = unidadesAsigUnidos[i].split(",");
            Asignatura asig = new Asignatura(asignaturasCurso[i],unidadesAsigSeparados,this.listaRutAlumnos);
            this.listaAsignaturas.add(asig);
        }
    } 
   
    //Metodos
    public void addAsignatura(String nombreAsig, String nombreUnidad)
    {
        Asignatura asig = new Asignatura(nombreAsig,nombreUnidad,this.listaRutAlumnos);
        this.listaAsignaturas.add(asig);
    }
    public void addUnidadAsignatura(String nombreAsig, String nombreUnidad)
    {
        for (int i = 0 ; i<this.listaAsignaturas.size() ; i++)
        {
            if((this.listaAsignaturas.get(i).getNombreAsignatura()).toLowerCase().equals(nombreAsig.toLowerCase()))
            {
                this.listaAsignaturas.get(i).addUnidad(nombreUnidad);
            }
        }
    }
    public void addAlumno(String rutAlumno)
    {
        this.listaRutAlumnos.add(rutAlumno);
        for(int i = 0; i < this.listaAsignaturas.size(); i++ )
        {
            this.listaAsignaturas.get(i).addAlumnoAsig(rutAlumno);
        }
    }
    
    public void addPreguntaAsig(String nombreAsig, String nombreUnidad, String pregunta)
    {
        for(int i = 0; i < this.listaAsignaturas.size(); i++ )
        {
            if((this.listaAsignaturas.get(i).getNombreAsignatura()).toLowerCase().equals(nombreAsig.toLowerCase()))
                this.listaAsignaturas.get(i).addPreguntaUnidad(nombreUnidad,pregunta);
        }
    }
    public void guardarNotasCurso(String rut, ArrayList<Double> notas)
    {
        this.mapaNotasAlumnos.put(rut, notas);
    }
    
    public ArrayList<String> getListaRuts ()
    {
        ArrayList <String> listaRuts = new ArrayList <String>();
        for (int i = 0 ; i < this.listaRutAlumnos.size() ; i++)
            listaRuts.add(this.listaRutAlumnos.get(i));
        return listaRuts;
    }
    public ArrayList<String> getListaNombresAsig ()
    {
        ArrayList <String> listaNombresAsig = new ArrayList <String>();
        for (int i = 0 ; i < this.listaAsignaturas.size() ; i++)
            listaNombresAsig.add(this.listaAsignaturas.get(i).getNombreAsignatura());
        return listaNombresAsig;
    }
    public ArrayList<String> getListaNombresUnidades(String nombreAsignatura)
    {
        ArrayList <String> listaNombresUnidades = new ArrayList <String>();
        for (int i = 0 ; i < this.listaAsignaturas.size() ; i++){
            if (nombreAsignatura.equals(this.listaAsignaturas.get(i).getNombreAsignatura()))
                listaNombresUnidades = this.listaAsignaturas.get(i).getNombreUnidades();
        }
        return listaNombresUnidades;
    }
    
    public ArrayList<String> getListaPreguntasAsig(String nombreAsig, String nombreUnidad) {
        ArrayList <String> listaPreguntasUnidades = new ArrayList <String>();
        for(int j = 0; j < this.listaAsignaturas.size(); j++ )
        {
            if((this.listaAsignaturas.get(j).getNombreAsignatura()).toLowerCase().equals(nombreAsig.toLowerCase()))
            {
                listaPreguntasUnidades = this.listaAsignaturas.get(j).getListaPreguntasUnidad(nombreUnidad);
            }
        }
        return listaPreguntasUnidades;
    }
    
    public  ArrayList<Double> getListaNotasAsig(String nombreAsig,String nombreUnidad)
    {
        ArrayList<Double> notas = new ArrayList<>();
        for(int j = 0; j < this.listaAsignaturas.size(); j++ )
        {
            if((this.listaAsignaturas.get(j).getNombreAsignatura()).toLowerCase().equals(nombreAsig.toLowerCase()))
            {
                notas = this.listaAsignaturas.get(j).getListaNotasUnidad(nombreUnidad);
            }
        }
        return notas;
    }
    
    public  ArrayList<Double> getNotas(String rutAlumno)
    {
        ArrayList<Double> notas = new  ArrayList<>(); 
        for(int j = 0; j < this.listaRutAlumnos.size(); j++ )
        {
            if (this.listaRutAlumnos.get(j).toLowerCase().equals(rutAlumno.toLowerCase()))
            {
                notas=(this.mapaNotasAlumnos.get(this.listaRutAlumnos.get(j)));
            }
            
        }
        return notas;
        
    }
    //Getters y Setters
    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    
}