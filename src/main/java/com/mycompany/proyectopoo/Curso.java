package com.mycompany.proyectopoo;
import java.util.ArrayList;
public class Curso {
    //Instancias de clase
    private String nombreCurso;
    private ArrayList<Asignatura> listaAsignaturas;
    private ArrayList<String> listaRutAlumnos;
    private ArrayList<AlumnoMatricula> listaEstadoAlumno;
    
    //Constructor
    public Curso(String nombreCurso) {
        setNombreCurso(nombreCurso);
        
        this.listaRutAlumnos = new ArrayList<>();
        
        this.listaAsignaturas = new ArrayList<>();
        
        this.listaEstadoAlumno = new ArrayList<>();
        
    } 
    
    public Curso(String nombreCurso,ArrayList<String>listaRutAlumnos) {
        setNombreCurso(nombreCurso);
        
        this.listaRutAlumnos = new ArrayList<>();
        
        this.listaRutAlumnos.addAll(listaRutAlumnos);
        
        this.listaAsignaturas = new ArrayList<>();
        
        this.listaEstadoAlumno = new ArrayList<>();
    } 
    
    public Curso(String nombreCurso, String [] asignaturasCurso , String [] unidadesAsigUnidos) {
        setNombreCurso(nombreCurso);
        
        this.listaRutAlumnos = new ArrayList<>();
        
        this.listaAsignaturas = new ArrayList<>();
        for (int i = 0 ; i < asignaturasCurso.length ; i++){
            String[] unidadesAsigSeparados = unidadesAsigUnidos[i].split(",");
            Asignatura asig = new Asignatura(asignaturasCurso[i],unidadesAsigSeparados,this.listaRutAlumnos);
            this.listaAsignaturas.add(asig);
        }
        this.listaEstadoAlumno = new ArrayList<>();
    } 
    
    public Curso(String nombreCurso, ArrayList<String>listaRutAlumnos, String [] asignaturasCurso , String [] unidadesAsigUnidos) {
        setNombreCurso(nombreCurso);
        
        this.listaRutAlumnos = new ArrayList<>();
        
        this.listaRutAlumnos.addAll(listaRutAlumnos);
        
        this.listaAsignaturas = new ArrayList<>();
        for (int i = 0 ; i < asignaturasCurso.length ; i++){
            String[] unidadesAsigSeparados = unidadesAsigUnidos[i].split(",");
            Asignatura asig = new Asignatura(asignaturasCurso[i],unidadesAsigSeparados,this.listaRutAlumnos);
            this.listaAsignaturas.add(asig);
        }
        this.listaEstadoAlumno = new ArrayList<>();
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
    public void addAlumno(String rutAlumno) throws InvalidNotaInitializationException
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
    public void addNotaAlumno(String nombreAsig, String nombreUnidad, String rutAlumno, double notaAGuardar, boolean inicializacion) throws InvalidNotaInitializationException
    {
        for(int i = 0; i < this.listaAsignaturas.size(); i++ )
        {
            if((this.listaAsignaturas.get(i).getNombreAsignatura()).toLowerCase().equals(nombreAsig.toLowerCase()))
                this.listaAsignaturas.get(i).addNotaAlumnoUnidad(nombreUnidad,notaAGuardar,rutAlumno,inicializacion);
        }
    }
    
    public void addEstadoAlumno(AlumnoMatricula estado)
    {
        this.listaEstadoAlumno.add(estado);
    }
    public ArrayList<AlumnoMatricula> getEstadoCurso()
    {
        ArrayList<AlumnoMatricula> estados = new ArrayList<>();
        for (int i = 0 ; i < this.listaEstadoAlumno.size() ; i++)
            estados.add(this.listaEstadoAlumno.get(i));
        return estados;
    }
    public ArrayList<String> getListaRuts ()
    {
        ArrayList <String> listaRuts = new ArrayList <>();
        for (int i = 0 ; i < this.listaRutAlumnos.size() ; i++)
            listaRuts.add(this.listaRutAlumnos.get(i));
        return listaRuts;
    }
    public ArrayList<String> getListaNombresAsig ()
    {
        ArrayList <String> listaNombresAsig = new ArrayList <>();
        for (int i = 0 ; i < this.listaAsignaturas.size() ; i++)
            listaNombresAsig.add(this.listaAsignaturas.get(i).getNombreAsignatura());
        return listaNombresAsig;
    }
    public ArrayList<String> getListaNombresUnidades(String nombreAsignatura)
    {
        ArrayList <String> listaNombresUnidades = new ArrayList <>();
        for (int i = 0 ; i < this.listaAsignaturas.size() ; i++){
            if (nombreAsignatura.equals(this.listaAsignaturas.get(i).getNombreAsignatura()))
                listaNombresUnidades = this.listaAsignaturas.get(i).getNombreUnidades();
        }
        return listaNombresUnidades;
    }
    
    public ArrayList<String> getListaPreguntasAsig(String nombreAsig, String nombreUnidad) {
        ArrayList <String> listaPreguntasUnidades;
        listaPreguntasUnidades = new ArrayList <>();
        for(int j = 0; j < this.listaAsignaturas.size(); j++ )
        {
            if((this.listaAsignaturas.get(j).getNombreAsignatura()).toLowerCase().equals(nombreAsig.toLowerCase()))
                listaPreguntasUnidades = this.listaAsignaturas.get(j).getListaPreguntasUnidad(nombreUnidad);
        }
        return listaPreguntasUnidades;
    }
    
    public  ArrayList<Double> getListaNotasAsig(String nombreAsig,String nombreUnidad)
    {
        ArrayList<Double> notas = new ArrayList<>();
        for(int j = 0; j < this.listaAsignaturas.size(); j++ )
        {
            if((this.listaAsignaturas.get(j).getNombreAsignatura()).toLowerCase().equals(nombreAsig.toLowerCase()))
                notas = this.listaAsignaturas.get(j).getListaNotasUnidad(nombreUnidad);
        }
        return notas;
    }
    
    public  ArrayList<Double> getNotas(String rutAlumno)
    {
        ArrayList<Double> notas = new  ArrayList<>(); 
        for(int j = 0; j < this.listaAsignaturas.size(); j++ )
        {
            ArrayList<String> unidades = this.listaAsignaturas.get(j).getNombreUnidades(); 
            for (int z = 0; z < unidades.size(); z++)
            {
                notas.add(this.listaAsignaturas.get(j).getNotaAlumno(unidades.get(z), rutAlumno));
            }
        }
        return notas;
    }
    public double getNotaAlumno (String nombreAsig, String nombreUnidad, String rutAlumno)
    {
        double nota = 0.0;
        for(int j = 0; j < this.listaAsignaturas.size(); j++ )
        {
            if((this.listaAsignaturas.get(j).getNombreAsignatura()).toLowerCase().equals(nombreAsig.toLowerCase()))
                nota = this.listaAsignaturas.get(j).getNotaAlumno(nombreUnidad,rutAlumno);
        }
        return nota;
    }
    public boolean deleteAlumno(String rutAlumno)
    {
        if(this.listaRutAlumnos.remove(rutAlumno))
        {
            //
            for(int i = 0 ; i < this.listaAsignaturas.size();i++)
            {
                this.listaAsignaturas.get(i).deleteAlumno(rutAlumno);
            }
            return true;
        }
        return false;
    }
    
    public boolean replaceAlumno(String rutOriginal, String rutNuevo)
    {
        
        if(this.listaRutAlumnos.contains(rutOriginal))
        {
            int  i = this.listaRutAlumnos.indexOf(rutOriginal);
            this.listaRutAlumnos.remove(rutOriginal);
            this.listaRutAlumnos.add(i,rutNuevo);
            for(i = 0 ; i < this.listaAsignaturas.size();i++)
            {
                this.listaAsignaturas.get(i).replaceAlumno(rutOriginal,rutNuevo);
            }
            return true;
        }
        return false;
    }
    public boolean replaceAlumnoMatricula(AlumnoMatricula original, AlumnoMatricula nuevo)
    {
        
        if(this.listaEstadoAlumno.contains(original))
        {
            int  i = this.listaEstadoAlumno.indexOf(original);
            this.listaEstadoAlumno.remove(original);
            this.listaEstadoAlumno.add(i,nuevo);
            return true;
        }
        return false;
    }
    
    
    //Getters y Setters
    public String getNombreCurso() {
        return nombreCurso;
    }

    private void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
}