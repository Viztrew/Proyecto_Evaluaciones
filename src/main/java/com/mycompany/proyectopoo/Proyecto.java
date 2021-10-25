package com.mycompany.proyectopoo;


import static com.mycompany.proyectopoo.ManejoDeCSV.generarNotasCSV;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import org.apache.commons.math3.util.Precision;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class Proyecto implements EstadoMatricula{
    
    public static void main(String [] arg) throws IOException 
    {
        Scanner lector = new Scanner(System.in);
        int opcion;
        int opcion2;
        String nombreCurso = null;
        String nombreAsig = null;
        String nombreUnidad = null;
        String rutAlumno = null;
        String datoAGuardar = null;
        String respuesta = null;
        double notaAlumno;
        boolean atras;
        boolean salir = true;
        double promedio;
        Aprobado alum1 = null;
        Repitente alum2 = null;
        int estadoAlumno;
        
        ManejoDeCursos c = new ManejoDeCursos();
        c.crearCursos();
        do{
            
            // Menu
            System.out.println("---MENÚ PRINCIPAL---");
            System.out.println("[1] Impresión de datos");
            System.out.println("[2] Agregar/Llenar datos");
            System.out.println("[3] Eliminar datos");
            System.out.println("[4] Modificar datos");
            System.out.println("[5] Finalizar año Escolar");
            System.out.println("[0] Salir");
            System.out.println("Ingrese opción: ");
            opcion = lector.nextInt(); // Ingresan opcion            
            lector.nextLine(); // Limpiar '\n'
            switch (opcion){
                case 0: 
                    salir = false;
                    
        
                    generarReporte(c);
                    break;
                case 1: // Impresion de datos
                    atras = true;
                    do{
                        // Menu
                        System.out.print("\n");
                        System.out.println("Opción: IMPRESIÓN DE DATOS");
                        System.out.println("[1] Mostrar cursos");
                        System.out.println("[2] Mostrar Alumnos de TODOS los cursos");
                        System.out.println("[3] Mostrar Alumnos de curso específico");
                        System.out.println("[4] Mostrar Asignaturas y Unidades de TODOS los cursos");
                        System.out.println("[5] Mostrar Asignaturas y Unidades de curso específico");
                        System.out.println("[6] Mostrar Notas de alumnos de un curso");
                        System.out.println("[7] Mostrar Promedio de Notas de una Unidad");
                        System.out.println("[8] Mostrar Promedio de Notas de un Alumno");
                        System.out.println("[0] Atrás"); 
                        System.out.println("Ingrese opción: ");
                        opcion2 = lector.nextInt();         
                        lector.nextLine();
                        System.out.print("\n");
                        switch (opcion2){
                            case 0: 
                                atras = false;
                                break;
                            case 1:
                                System.out.println("Opción: IMPRESION DE CURSOS");
                                c.mostrarNombreCursos();
                                break;
                            case 2: 
                                System.out.println("Opción: IMPRESION DE TODOS LOS ALUMNOS");
                                c.mostrarTodosAlumnos();
                                break;
                            case 3:
                                System.out.println("Opción: IMPRESION DE LOS ALUMNOS DE UN CURSO ESPECIFICO");
                                do
                                {
                                    System.out.println("Cursos en el sistema:");
                                    c.mostrarNombreCursos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese nombre del curso:");
                                    nombreCurso = lector.nextLine();
                                    System.out.print("\n");
                                }while(c.validarCurso(nombreCurso)== false);
                                c.mostrarAlumnosCurso(nombreCurso);
                                break;
                            case 4: 
                                System.out.println("Opción: IMPRESION DE ASIGNATURAS Y UNIDADES DE TODOS LOS CURSOS");
                                c.mostrarTodosAsigYUnidades();
                                break;
                            case 5: 
                                System.out.println("Opción: IMPRESION DE ASIGNATURAS Y UNIDADES DE UN CURSO ESPECÍFICO");
                                do
                                {
                                    System.out.println("Cursos en el sistema:");
                                    c.mostrarNombreCursos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese nombre del curso:");
                                    nombreCurso=lector.nextLine();
                                    System.out.print("\n");
                                }while(c.validarCurso(nombreCurso) == false);
                                c.mostrarAsigYUnidadesCurso(nombreCurso);
                                break;
                            case 6:
                                System.out.println("Opción: IMPRESION DE NOTAS DE UN CURSO ESPECÍFICO");
                                do
                                {
                                    System.out.println("Cursos en el sistema:");
                                    c.mostrarNombreCursos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese nombre del curso:");
                                    nombreCurso = lector.nextLine();
                                    System.out.print("\n");
                                }while(c.validarCurso(nombreCurso)== false);
                                c.mostrarNotasAlumnos(nombreCurso);
                                break;
                            case 7:
                                System.out.println("Opción: IMPRESION DE PROMEDIO DE NOTAS DE UNA UNIDAD");
                                System.out.println("Para mostrar el promedio de notas de una Unidad, debe especificar Curso, Asigantura y Unidad");
                                do
                                {
                                    System.out.println("Cursos en el sistema:");
                                    c.mostrarNombreCursos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese Curso de la Asignatura:");
                                    nombreCurso = lector.nextLine();  
                                    System.out.print("\n");
                                }while(c.validarCurso(nombreCurso) == false);
                                if(c.verificarAsigDeCurso(nombreCurso))
                                {
                                    do
                                    {
                                        System.out.println("Asginaturas del Curso "+ nombreCurso+":");
                                        c.mostrarNombresAsig(nombreCurso);
                                        System.out.print("\n");
                                        System.out.println("Ingrese Asginatura de la Unidad:");
                                        nombreAsig = lector.nextLine();
                                        System.out.print("\n");
                                    }while(c.validarAsignatura(nombreCurso,nombreAsig) == false);
                                    do
                                    {
                                        System.out.println("Unidades en la Asignatura "+ nombreAsig +":");
                                        c.mostrarNombresUnidades(nombreCurso,nombreAsig);
                                        System.out.print("\n");
                                        System.out.println("Ingrese Unidad " + nombreAsig + " (SIN ESPACIOS, ej: RevolucionIndustrial) :");
                                        nombreUnidad = lector.nextLine();
                                        System.out.print("\n");
                                        if(c.validarUnidad(nombreCurso,nombreAsig, nombreUnidad) == true)
                                        {
                                            if (c.getPromedioNotasUnidad(nombreCurso, nombreAsig, nombreUnidad)!=0.0){
                                                System.out.println("El promedio de notas de la Unidad "+nombreUnidad+" es: "+c.getPromedioNotasUnidad(nombreCurso, nombreAsig, nombreUnidad));
                                            }
                                            else{
                                                System.out.println("La Unidad"+nombreUnidad+" no tiene notas ingresadas.");
                                            }
                                            respuesta = "si";
                                        }else{
                                            respuesta = "no";
                                        }
                                    }while (respuesta.equals("no"));
                                }
                                break;
                            case 8:
                                do
                                {
                                    System.out.println("Opcion: IMPRESION DE PROMEDIO DE NOTAS DE UN ALUMNO");
                                    System.out.println("Alumnos en el sistema: ");
                                    c.mostrarTodosAlumnos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese RUT del alumno sin puntos y con guión (Ej:20132111-k):");
                                    rutAlumno = lector.nextLine();  
                                    System.out.print("\n");
                                    if (c.validarAlumno(rutAlumno)!=true) 
                                    {
                                        System.out.println("'"+rutAlumno+"' no fue encontrado en el sistema ");
                                        System.out.print("\n");
                                    }
                                }while( c.validarAlumno(rutAlumno)!=true);
                                promedio = c.getPromedioAlumno(c.buscarAlumno(rutAlumno), rutAlumno);
                                if (promedio==0.0)
                                {
                                    System.out.println("El Alumno '"+rutAlumno+"' no tiene notas ingresadas, no es posible obtener su promedio.");
                                }else{
                                    System.out.println("El promedio de notas del Alumno '"+rutAlumno+"'"+" es "+promedio);
                                }
                                reportarEstado(promedio);
                                break;
                                
                            
                                
                            default:
                                System.out.println("Ingrese opción válida:");
                                opcion2 = lector.nextInt();
                        }
                    }while(atras);
                    break;
                case 2: // Llenado
                    atras = true;
                    do{
                        // Menu
                        System.out.print("\n");
                        System.out.println("Opcion: AGREGAR/LLENAR DATOS");
                        System.out.println("[1] Agregar Curso al sistema");
                        System.out.println("[2] Agregar Asignatura a Curso");
                        System.out.println("[3] Agregar Unidad a Asigantura");
                        System.out.println("[4] Agregar Alumno a Curso");
                        System.out.println("[5] Agregar Nota a Alumno");
                        System.out.println("[6] Ingresar banco de preguntas");
                        System.out.println("[0] Atras"); 
                        System.out.println("Ingrese opción: ");
                        opcion2 = lector.nextInt();         
                        lector.nextLine();
                        System.out.print("\n");
                        switch (opcion2){
                            case 0: 
                                atras = false;
                                break;
                                
                            case 1:
                                System.out.println("Opción: AGREGAR CURSO AL SISTEMA");
                                do
                                {
                                    System.out.println("Cursos en el sistema:");
                                    c.mostrarNombreCursos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese nombre del Curso:");
                                    nombreCurso = lector.nextLine();  
                                    System.out.print("\n");
                                    if(c.validarCurso(nombreCurso) == false)
                                    {
                                        do
                                        {
                                            System.out.println("¿Desea añadir el Curso " + "'"+nombreCurso+"'?");
                                            System.out.println("Ingrese opción: (Si/No)");
                                            respuesta = lector.nextLine().toLowerCase();
                                        }while((respuesta.equals("no") != true) && (respuesta.equals("si") != true));
                                    }else{
                                        System.out.println("El Curso '"+ nombreCurso +"' ya se encuentra ingresado en el sistema, intente nuevamente.");
                                        respuesta = "no";
                                    }
                                }while (respuesta.equals("no"));
                                c.addCurso(nombreCurso);
                                break;
                            case 2: 
                                System.out.println("Opción: AGREGAR ASIGNATURA AL CURSO");
                                System.out.println("Para agregar una Asignatura, debe especificar su Curso.");
                                do
                                {
                                    System.out.println("Cursos en el sistema:");
                                    c.mostrarNombreCursos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese Curso:");
                                    nombreCurso = lector.nextLine();  
                                    System.out.print("\n");
                                }while(c.validarCurso(nombreCurso) == false);
                                
                                do
                                {
                                    System.out.println("Asignaturas en el Curso "+ nombreCurso +":");
                                    c.mostrarNombresAsig(nombreCurso);
                                    System.out.print("\n");
                                    System.out.println("Ingrese nombre de la Asignatura a añadir al curso " + nombreCurso + ":");
                                    datoAGuardar = lector.nextLine();
                                    System.out.print("\n");
                                    if(c.validarAsignatura(nombreCurso, datoAGuardar) == false)
                                    {
                                        do
                                        {
                                            System.out.println("¿Desea añadir la Asignatura " + "'"+datoAGuardar+"'?");
                                            System.out.println("Ingrese opción: (Si/No)");
                                            respuesta = lector.nextLine().toLowerCase();
                                        }while((respuesta.equals("no") != true) && (respuesta.equals("si") != true));
                                    }else{
                                        System.out.println("La Asignatura '"+ datoAGuardar +"' ya se encuentra ingresada en "+ nombreCurso +", intente nuevamente.");
                                        respuesta = "no";
                                    }
                                }while (respuesta.equals("no"));
                                nombreAsig = datoAGuardar;
                                do
                                {
                                    System.out.print("\n");
                                    System.out.println("Para crear la Asignatura "+ nombreAsig +" debe ingresar una Unidad:");
                                    System.out.println("Ingrese nombre de la Unidad a añadir a la Asignatura " + nombreAsig + " (SIN ESPACIOS, ej: RevolucionIndustrial) :");
                                    datoAGuardar = lector.nextLine();
                                    System.out.print("\n");
                                    do
                                    {
                                        System.out.println("¿Desea añadir la Unidad " + "'"+datoAGuardar+"'?");
                                        System.out.println("Ingrese opción: (Si/No)");
                                        respuesta = lector.nextLine().toLowerCase();
                                    }while((respuesta.equals("no") != true) && (respuesta.equals("si") != true));
                                }while (respuesta.equals("no"));
                                
                                System.out.println("Asignatura ingresada.");
                                c.addAsignaturaACurso(nombreCurso,nombreAsig,datoAGuardar);
                                break;
                                
                            case 3: 
                                System.out.println("Opción: AGREGAR UNIDAD A ASIGNATURA");
                                System.out.println("Para agregar una Unidad, debe especificar Curso y Asigantura");
                                do
                                {
                                    System.out.println("Cursos en el sistema:");
                                    c.mostrarNombreCursos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese Curso de la Asignatura:");
                                    nombreCurso = lector.nextLine();  
                                    System.out.print("\n");
                                }while(c.validarCurso(nombreCurso) == false);
                                if(c.verificarAsigDeCurso(nombreCurso))
                                {
                                    do
                                    {
                                        System.out.println("Asginaturas del Curso "+ nombreCurso+":");
                                        c.mostrarNombresAsig(nombreCurso);
                                        System.out.print("\n");
                                        System.out.println("Ingrese Asginatura de la Unidad:");
                                        nombreAsig = lector.nextLine();
                                        System.out.print("\n");
                                    }while(c.validarAsignatura(nombreCurso,nombreAsig) == false);
                                    do
                                    {
                                        System.out.println("Unidades en la Asignatura "+ nombreAsig +":");
                                        c.mostrarNombresUnidades(nombreCurso,nombreAsig);
                                        System.out.print("\n");
                                        System.out.println("Ingrese Unidad a añadir a la Asignatura " + nombreAsig + " (SIN ESPACIOS, ej: RevolucionIndustrial) :");
                                        datoAGuardar = lector.nextLine();
                                        System.out.print("\n");
                                        if(c.validarUnidad(nombreCurso,nombreAsig, datoAGuardar) == false)
                                        {
                                            do
                                            {
                                                System.out.println("¿Desea añadir la Unidad " + "'"+datoAGuardar+"'?");
                                                System.out.println("Ingrese opción: (Si/No)");
                                                respuesta = lector.nextLine().toLowerCase();
                                            }while((respuesta.equals("no") != true) && (respuesta.equals("si") != true));
                                        }else{
                                            System.out.println("La Unidad '"+ datoAGuardar +"' ya se encuentra ingresado en "+ nombreAsig +", intente nuevamente.");
                                            respuesta = "no";
                                        }
                                    }while (respuesta.equals("no"));
                                    c.addUnidadAAsignatura(nombreCurso,nombreAsig,datoAGuardar);
                                    System.out.println("Unidad ingresada.");
                                }else
                                {
                                    System.out.println("El curso ingresado no tiene Asignaturas, por favor ingrese al menos una.");
                                }
                                
                                
                                break;
                                
                            case 4:
                                System.out.println("Opción: AGREGAR ALUMNO AL CURSO");
                                System.out.println("Para agregar un alumno, debe especificar su Curso de destino.");
                                do
                                {
                                    System.out.println("Cursos en el sistema:");
                                    c.mostrarNombreCursos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese Curso:");
                                    nombreCurso = lector.nextLine();  
                                    System.out.print("\n");
                                }while(c.validarCurso(nombreCurso) == false);
                                
                                do
                                {
                                    System.out.println("Alumnos en el Curso "+ nombreCurso +":");
                                    c.mostrarAlumnosCurso(nombreCurso);
                                    System.out.print("\n");
                                    System.out.println("Ingrese rut de alumno a añadir al curso " + nombreCurso + " (sin puntos y con guión):");
                                    datoAGuardar = lector.nextLine();
                                    System.out.print("\n");
                                    if(c.validarAlumnoEnCurso(nombreCurso, datoAGuardar) == false)
                                    {
                                        do
                                        {
                                            System.out.println("¿Desea añadir al alumno " + "'"+datoAGuardar+"'?");
                                            System.out.println("Ingrese opción: (Si/No)");
                                            respuesta = lector.nextLine().toLowerCase();
                                        }while((respuesta.equals("no") != true) && (respuesta.equals("si") != true));
                                    }else{
                                        System.out.println("El rut '"+ datoAGuardar +"' ya se encuentra ingresado en "+ nombreCurso +", intente nuevamente.");
                                        respuesta = "no";
                                    }
                                }while (respuesta.equals("no"));
                                System.out.println("Alumno ingresado.");
                                c.addAlumnoACurso(nombreCurso,datoAGuardar);
                                break;
                            case 5:
                                System.out.println("Opción: AGREGAR NOTA A ALUMNO");
                                System.out.println("Para agregar una Nota a un Alumno, debe especificar su Curso, Asigantura y Unidad");
                                do
                                {
                                    System.out.println("Cursos en el sistema:");
                                    c.mostrarNombreCursos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese Curso de la Asignatura:");
                                    nombreCurso = lector.nextLine();  
                                    System.out.print("\n");
                                }while(c.validarCurso(nombreCurso) == false);
                                if(c.verificarAsigDeCurso(nombreCurso)&&c.verificarAlumnosDeCurso(nombreCurso))
                                {
                                    do
                                    {
                                        System.out.println("Asginaturas del Curso "+ nombreCurso+":");
                                        c.mostrarNombresAsig(nombreCurso);
                                        System.out.print("\n");
                                        System.out.println("Ingrese Asginatura de la Unidad:");
                                        nombreAsig = lector.nextLine();
                                        System.out.print("\n");
                                    }while(c.validarAsignatura(nombreCurso,nombreAsig) == false);
                                    do
                                    {
                                        System.out.println("Unidades en la Asignatura "+ nombreAsig +":");
                                        c.mostrarNombresUnidades(nombreCurso,nombreAsig);
                                        System.out.print("\n");
                                        System.out.println("Ingrese Unidad para añadir nota: ");
                                        nombreUnidad = lector.nextLine();
                                        System.out.print("\n");
                                    }while(c.validarUnidad(nombreCurso,nombreAsig, nombreUnidad) == false);
                                    do
                                    {
                                        do
                                        {
                                            System.out.println("Alumnos en el Curso "+ nombreCurso +": ('s/n' sin nota)");
                                            c.mostrarAlumnosYNotasUnidad(nombreCurso, nombreAsig, nombreUnidad);
                                            System.out.print("\n");
                                            System.out.println("Ingrese rut de alumno a añadir Nota (sin puntos y con guión):");
                                            rutAlumno = lector.nextLine();
                                            System.out.print("\n");
                                            if(c.validarAlumnoEnCurso(nombreCurso, rutAlumno)==false)
                                            {
                                                System.out.println("El Rut '"+rutAlumno+"' no fue encontrado, intente nuevamente.");
                                                System.out.print("\n");
                                            }
                                        }while(c.validarAlumnoEnCurso(nombreCurso, rutAlumno)==false);

                                        if(c.getNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, rutAlumno)==0.0)
                                        {
                                            do
                                            {
                                                System.out.println("Ingrese Nota para el Alumno "+ rutAlumno +" en la evaluacion de "+nombreUnidad+" (1.0-7.0):");
                                                notaAlumno=Double.parseDouble(lector.nextLine());
                                                System.out.print("\n");
                                                if((notaAlumno >= 1.0 && notaAlumno <= 7.0))
                                                {
                                                    c.addNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, rutAlumno,notaAlumno,false);
                                                    System.out.println("Nota Ingresada. \n");
                                                }else
                                                {
                                                    System.out.println("Formato inválido, intente nuevamente ingresando un número entre 1.0 y 7.0.");
                                                    System.out.print("\n");
                                                }
                                            }while((notaAlumno >= 1.0 && notaAlumno <= 7.0)==false);
                                        }else
                                        {
                                            System.out.println("El Alumno '"+rutAlumno+"' tiene la nota: "+c.getNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, rutAlumno)+" ingresada, si desea reemplazarla, por favor ingresela en la sección de MODIFICAR DATOS.");
                                        }
                                        do
                                        {
                                            System.out.println("¿Desea ingresar otra nota en la Unidad "+nombreUnidad+" a otro alumno?");
                                             System.out.println("Ingrese opción: (Si/No)");
                                            respuesta = lector.nextLine().toLowerCase();
                                        }while((respuesta.equals("no") != true) && (respuesta.equals("si") != true));
                                    }while (respuesta.equals("si"));
                                    
                                    
                                }else
                                {
                                    if (c.verificarAsigDeCurso(nombreCurso)==false)
                                    {
                                        System.out.println("El curso ingresado no tiene Asignaturas, por favor ingrese al menos una.");
                                    }else{
                                        System.out.println("El curso ingresado no tiene Alumnos, por favor ingrese al menos uno.");
                                    }
                                }
                                break;
                                
                            case 6:
                                System.out.println("Opción: AGREGAR BANCO DE PREGUNTAS A UNIDAD");
                                System.out.println("Para ingresar un banco de preguntas, debe especificar Curso, Asigantura y Unidad de las preguntas");
                                do
                                {
                                    System.out.println("Cursos en el sistema:");
                                    c.mostrarNombreCursos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese Curso de la Asignatura:");
                                    nombreCurso = lector.nextLine();  
                                    System.out.print("\n");
                                }while(c.validarCurso(nombreCurso) == false);
                                if(c.verificarAsigDeCurso(nombreCurso))
                                {
                                    do
                                    {
                                        System.out.println("Asginaturas del Curso "+ nombreCurso+":");
                                        c.mostrarNombresAsig(nombreCurso);
                                        System.out.print("\n");
                                        System.out.println("Ingrese Asginatura de la Unidad:");
                                        nombreAsig = lector.nextLine();
                                        System.out.print("\n");
                                    }while(c.validarAsignatura(nombreCurso,nombreAsig) == false);

                                    do
                                    {
                                        System.out.println("Unidades de la Asignatura "+ nombreAsig +":");
                                        c.mostrarNombresUnidades(nombreCurso,nombreAsig); 
                                        System.out.print("\n");
                                        System.out.println("Ingrese Unidad de la Asignatura:");
                                        nombreUnidad = lector.nextLine();
                                    }while(c.validarUnidad(nombreCurso,nombreAsig,nombreUnidad) == false);
                                    atras = true;
                                    do{
                                        // subMenu
                                        System.out.print("\n");
                                        System.out.println("Opción: Llenado banco de preguntas de " +nombreCurso+"-"+ nombreAsig+"-"+nombreUnidad);
                                        System.out.println("[1] Ingresar pregunta");
                                        System.out.println("[2] Mostrar banco de preguntas");
                                        System.out.println("[0] Atras"); 
                                        System.out.println("Ingrese opción: ");
                                        opcion2 = lector.nextInt();         
                                        lector.nextLine();
                                        System.out.print("\n");
                                        switch (opcion2){
                                            case 0: 
                                                atras = false;
                                                break;
                                            case 1:
                                                do
                                                {
                                                    System.out.println("Ingrese pregunta a añadir:");
                                                    datoAGuardar = lector.nextLine();
                                                    System.out.print("\n");
                                                    do
                                                    {
                                                        System.out.println("¿Desea añadir " + "'"+datoAGuardar+"'?");
                                                        System.out.println("Ingrese opción: (Si/No)");
                                                        respuesta=lector.nextLine().toLowerCase();
                                                    }while((respuesta.equals("no") != true) && (respuesta.equals("si") != true));
                                                }while (respuesta.equals("no"));

                                                c.addPreguntaUnidad(nombreCurso,nombreAsig,nombreUnidad,datoAGuardar,true);
                                                System.out.println("Pregunta guardada.");
                                                break;
                                            case 2:
                                                c.mostrarBancoPreguntasUnidad(nombreCurso,nombreAsig,nombreUnidad);
                                                break;
                                            default:
                                                System.out.println("Ingrese opcion valida:");
                                                opcion2 = lector.nextInt();
                                        }
                                    }while(atras);
                                }else
                                {
                                    System.out.println("El curso ingresado no tiene Asignaturas, por favor ingrese al menos una.");
                                }
                                
                                break;
                            default:
                                System.out.println("Ingrese opción valida");
                                opcion2 = lector.nextInt();
                        }
                    }while(atras);
                    break;
                case 3: // opcion eliminar
                    atras=true;
                    do
                    {
                        System.out.print("\n");
                        System.out.println("Opción: ELIMINAR DATOS");
                        System.out.println("[1] Eliminar Alumno de Curso");
                        System.out.println("[2] Eliminar Nota Alumno");
                        System.out.println("[0] Atrás"); 
                        System.out.println("Ingrese opción: ");
                        opcion2 = lector.nextInt();         
                        lector.nextLine();
                        System.out.print("\n");
                        switch (opcion2){
                            case 0:
                                atras=false;
                                break;
                            case 1:
                                do
                                {
                                    System.out.println("Opción: ELIMINAR ALUMNO DE CURSO");
                                    System.out.println("Alumnos en el sistema: ");
                                    c.mostrarTodosAlumnos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese RUT del alumno sin puntos y con guión (Ej:20132111-k)():");
                                    rutAlumno = lector.nextLine();  
                                    System.out.print("\n");
                                    if (c.validarAlumno(rutAlumno)!=true) 
                                    {
                                        System.out.println("'"+rutAlumno+"' no fue encontrado en el sistema ");
                                        System.out.print("\n");
                                    }
                                }while( c.validarAlumno(rutAlumno)!=true);
                                System.out.println("Alumno "+ rutAlumno+ " fue encontrado en el Curso " + c.buscarAlumno(rutAlumno));
                                do
                                {
                                    System.out.println("¿Desea ELIMINAR el rut " + "'"+rutAlumno+"'?" +"(esta acción será permanente)");
                                    System.out.println("Ingrese opción: (Si/No)");
                                    respuesta=lector.nextLine().toLowerCase();
                                }while((respuesta.equals("no") != true) && (respuesta.equals("si") != true)); 
                                if (respuesta.equals("si")){
                                    if(c.deleteAlumno(rutAlumno))
                                    {
                                        System.out.println("Alumno " + rutAlumno + " ha sido eliminado.");
                                    }else{
                                        System.out.println("Alumno " + rutAlumno + " no se ha podido eliminar.");
                                    }
                                }
                                break;
                            case 2:
                                System.out.println("Opción: ELIMINAR NOTA A ALUMNO");
                                System.out.println("Para eliminar una Nota a un Alumno, debe especificar su Curso, Asigantura y Unidad. Ádemas debe tener la Nota ingresada.");
                                do
                                {
                                    System.out.println("Cursos en el sistema:");
                                    c.mostrarNombreCursos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese Curso de la Asignatura:");
                                    nombreCurso = lector.nextLine();  
                                    System.out.print("\n");
                                }while(c.validarCurso(nombreCurso) == false);
                                if(c.verificarAsigDeCurso(nombreCurso)&&c.verificarAlumnosDeCurso(nombreCurso))
                                {
                                    do
                                    {
                                        System.out.println("Asginaturas del Curso "+ nombreCurso+":");
                                        c.mostrarNombresAsig(nombreCurso);
                                        System.out.print("\n");
                                        System.out.println("Ingrese Asginatura de la Unidad:");
                                        nombreAsig = lector.nextLine();
                                        System.out.print("\n");
                                    }while(c.validarAsignatura(nombreCurso,nombreAsig) == false);
                                    do
                                    {
                                        System.out.println("Unidades en la Asignatura "+ nombreAsig +":");
                                        c.mostrarNombresUnidades(nombreCurso,nombreAsig);
                                        System.out.print("\n");
                                        System.out.println("Ingrese Unidad para añadir nota: ");
                                        nombreUnidad = lector.nextLine();
                                        System.out.print("\n");
                                    }while(c.validarUnidad(nombreCurso,nombreAsig, nombreUnidad) == false);
                                    do
                                    {
                                        do
                                        {
                                            System.out.println("Alumnos en el Curso "+ nombreCurso +": ('s/n' sin nota)");
                                            c.mostrarAlumnosYNotasUnidad(nombreCurso, nombreAsig, nombreUnidad);
                                            System.out.print("\n");
                                            System.out.println("Ingrese rut de alumno a eliminar Nota (sin puntos y con guión):");
                                            rutAlumno = lector.nextLine();
                                            System.out.print("\n");
                                            if(c.validarAlumnoEnCurso(nombreCurso, rutAlumno)==false)
                                            {
                                                System.out.println("El Rut '"+rutAlumno+"' no fue encontrado, intente nuevamente.");
                                                System.out.print("\n");
                                            }
                                        }while(c.validarAlumnoEnCurso(nombreCurso, rutAlumno)==false);

                                        if(c.getNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, rutAlumno)!=0.0)
                                        {
                                            do
                                            {
                                                System.out.println("¿Desea ELIMINAR Nota del Alumno '"+rutAlumno+"' Nota: " +c.getNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, rutAlumno)+"?" );
                                                System.out.println("Ingrese opción: (Si/No) (esta acción será permanente).");
                                                respuesta = lector.nextLine().toLowerCase();
                                                System.out.print("\n");
                                            }while((respuesta.equals("no") != true) && (respuesta.equals("si") != true));
                                            if (respuesta.equals("si"))
                                            {
                                                c.addNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, rutAlumno,0.0,true);
                                                System.out.println("Nota Eliminada.\n");
                                            }
                                        }else
                                        {
                                            System.out.println("El Alumno '"+rutAlumno+"' no tiene una Nota ingresada (s/n) por lo tanto no es posible eliminarla.\n");
                                        }
                                        do
                                        {
                                            System.out.println("¿Desea eliminar otra nota en la Unidad "+nombreUnidad+" a otro alumno?");
                                            System.out.println("Ingrese opción: (Si/No)");
                                            respuesta = lector.nextLine().toLowerCase();
                                            System.out.print("\n");
                                        }while((respuesta.equals("no") != true) && (respuesta.equals("si") != true));
                                    }while (respuesta.equals("si"));
                                }
                                break;
                        }
                                
                    }while(atras);
                    break;
                    
                case 4: //modificar
                    atras=true;
                    do
                    {
                        System.out.print("\n");
                        System.out.println("Opción: MODIFICAR DATOS");
                        System.out.println("[1] Modificar RUT Alumno de Curso");
                        System.out.println("[2] Modificar Nota a Alumno");
                        System.out.println("[0] Atrás"); 
                        System.out.println("Ingrese opción: ");
                        opcion2 = lector.nextInt();         
                        lector.nextLine();
                        System.out.print("\n");
                        switch (opcion2){
                            case 0:
                                atras=false;
                                break;
                            case 1:
                                do
                                {
                                    System.out.println("Opción: MODIFICAR RUT DE ALUMNO DE CURSO");
                                    System.out.println("Alumnos en el sistema: ");
                                    c.mostrarTodosAlumnos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese RUT del alumno sin puntos y con guión (Ej:20132111-k)():");
                                    rutAlumno = lector.nextLine();  
                                    System.out.print("\n");
                                }while( c.validarAlumno(rutAlumno)!=true);
                                System.out.println("Alumno "+ rutAlumno+ " fue encontrado en el Curso " + c.buscarAlumno(rutAlumno));
                                do
                                {
                                    do{
                                        System.out.println("Ingrese nuevo rut para modificar el anterior ("+ rutAlumno+"):");
                                        datoAGuardar=lector.nextLine();
                                        if (c.validarAlumno(datoAGuardar)) System.out.println("El rut '"+ datoAGuardar + "' ya existe en el sistema.");
                                        System.out.print("\n");
                                    }while( c.validarAlumno(datoAGuardar)!=false);
                                    System.out.println("'"+datoAGuardar+"' no fue encontrado en el sistema ");
                                    System.out.print("\n");
                                    do
                                    {
                                        System.out.println("¿Desea modificar el rut " + "'"+rutAlumno+"' por el rut '"+datoAGuardar+"'?" +"(esta acción será permanente)");
                                        System.out.println("Ingrese opción: (Si/No)");
                                        respuesta=lector.nextLine().toLowerCase();
                                    }while((respuesta.equals("no") != true) && (respuesta.equals("si") != true)); 
                                }while (respuesta.equals("no"));
                                
                                if (respuesta.equals("si")){
                                    if(c.replaceAlumno(rutAlumno,datoAGuardar))
                                    {
                                        System.out.println("El rut '" + rutAlumno + "' ha sido modificado por '" + datoAGuardar+"'.");
                                    }else{
                                        System.out.println("Alumno '" + rutAlumno + "' no se ha podido modificar.");
                                    }
                                }
                                break;
                            case 2:
                                System.out.println("Opción: MODIFICAR NOTA A ALUMNO");
                                System.out.println("Para modificar una Nota a un Alumno, debe especificar su Curso, Asigantura y Unidad. Ádemas debe tener la Nota ingresada.");
                                do
                                {
                                    System.out.println("Cursos en el sistema:");
                                    c.mostrarNombreCursos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese Curso de la Asignatura:");
                                    nombreCurso = lector.nextLine();  
                                    System.out.print("\n");
                                }while(c.validarCurso(nombreCurso) == false);
                                if(c.verificarAsigDeCurso(nombreCurso)&&c.verificarAlumnosDeCurso(nombreCurso))
                                {
                                    do
                                    {
                                        System.out.println("Asginaturas del Curso "+ nombreCurso+":");
                                        c.mostrarNombresAsig(nombreCurso);
                                        System.out.print("\n");
                                        System.out.println("Ingrese Asginatura de la Unidad:");
                                        nombreAsig = lector.nextLine();
                                        System.out.print("\n");
                                    }while(c.validarAsignatura(nombreCurso,nombreAsig) == false);
                                    do
                                    {
                                        System.out.println("Unidades en la Asignatura "+ nombreAsig +":");
                                        c.mostrarNombresUnidades(nombreCurso,nombreAsig);
                                        System.out.print("\n");
                                        System.out.println("Ingrese Unidad para añadir nota: ");
                                        nombreUnidad = lector.nextLine();
                                        System.out.print("\n");
                                    }while(c.validarUnidad(nombreCurso,nombreAsig, nombreUnidad) == false);
                                    do
                                    {
                                        do
                                        {
                                            System.out.println("Alumnos en el Curso "+ nombreCurso +": ('s/n' sin nota)");
                                            c.mostrarAlumnosYNotasUnidad(nombreCurso, nombreAsig, nombreUnidad);
                                            System.out.print("\n");
                                            System.out.println("Ingrese rut de alumno a modificar Nota (sin puntos y con guión):");
                                            rutAlumno = lector.nextLine();
                                            System.out.print("\n");
                                            if(c.validarAlumnoEnCurso(nombreCurso, rutAlumno)==false)
                                            {
                                                System.out.println("El Rut '"+rutAlumno+"' no fue encontrado, intente nuevamente.");
                                                System.out.print("\n");
                                            }
                                        }while(c.validarAlumnoEnCurso(nombreCurso, rutAlumno)==false);

                                        if(c.getNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, rutAlumno)!=0.0)
                                        {
                                            do
                                            {
                                                System.out.println("Alumno seleccionado: "+ rutAlumno);
                                                System.out.println("Ingrese Nota para reemplazar la Nota anterior ("+ c.getNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, rutAlumno) +") en la evaluacion de "+nombreUnidad+" (1.0-7.0):");
                                                notaAlumno=Double.parseDouble(lector.nextLine());
                                                if((notaAlumno >= 1.0 && notaAlumno <= 7.0))
                                                {
                                                    c.addNotaAlumno(nombreCurso, nombreAsig, nombreUnidad, rutAlumno,notaAlumno,false);
                                                    System.out.println("Nota Modificada. \n");
                                                }else
                                                {
                                                    System.out.println("Formato inválido, intente nuevamente ingresando un número entre 1.0 y 7.0.");
                                                    System.out.print("\n");
                                                }
                                            }while((notaAlumno >= 1.0 && notaAlumno <= 7.0)==false);
                                        }else
                                        {
                                            System.out.println("El Alumno '"+rutAlumno+"' no tiene una Nota ingresada (s/n) por favor, ingresela en la sección de AGREGAR/LLENAR DATOS.");
                                        }
                                        do
                                        {
                                            System.out.println("¿Desea modificar otra nota en la Unidad "+nombreUnidad+" a otro alumno?");
                                             System.out.println("Ingrese opción: (Si/No)");
                                            respuesta = lector.nextLine().toLowerCase();
                                        }while((respuesta.equals("no") != true) && (respuesta.equals("si") != true));
                                    }while (respuesta.equals("si"));
                                }
                                break;
                        }
                                
                    }while(atras);
                    break;
                case 5: 
                    atras=true;
                    do{
                        System.out.print("\n");
                        System.out.println("Opción: FINALIZAR AÑO ESCOLAR");
                        System.out.println("UNA VEZ FINALIZADO EL AÑO NO PODRÁS AÑADIR/MODIFICAR/ELIMINAR MAS DATOS");
                        System.out.println("[1] Finalizar año");
                        System.out.println("[0] Atrás"); 
                        System.out.println("Ingrese opción: ");
                        opcion2 = lector.nextInt();         
                        lector.nextLine();
                        System.out.print("\n");
                        switch(opcion2)
                        {
                            case 0:
                                atras=false;
                                break;
                            case 1:
                                c.matriculaFinal();
                                System.out.println("Notas cerradas.");
                                System.out.println("Estado de Alumnos creados.");
                                do
                                {
                                    System.out.println("Opción: FINALIZAR AÑO");
                                    System.out.println("[1] Mostrar estados de Alumnos(Aprobado/Reprobado) de TODOS los Cursos");
                                    System.out.println("[2] Mostrar SOLO Alumnos Aprobados");
                                    System.out.println("[3] Mostrar SOLO Alumnos Reprobados");
                                    System.out.println("[4] Ingresar Nota de Evaluación Final (Exámen)");
                                    System.out.println("[0] Salir"); 
                                    System.out.println("Ingrese opción: ");
                                    opcion2 = lector.nextInt();         
                                    lector.nextLine();
                                    System.out.print("\n");
                                    switch(opcion2)
                                    {
                                        case 0:
                                            salir=false;
                                            atras=false;
                                            break;
                                        case 1:
                                            System.out.println("Opción: MOSTRAR ESTADO DE ALUMNOS (APROBADO/REPROBADO) DE TODOS LOS CURSOS");
                                            c.mostrarEstadoAlumnos(true,true);
                                            break;
                                        case 2:
                                            System.out.println("Opción: MOSTRAR SOLO ALUMNOS APROBADOS");
                                            c.mostrarEstadoAlumnos(false,true);
                                            break;
                                        case 3:
                                            System.out.println("Opción: MOSTRAR SOLO ALUMNOS REPROBADOS");
                                            c.mostrarEstadoAlumnos(true,false);
                                            break;
                                        case 4:    
                                            do
                                            {
                                                System.out.println("Opción: INGRESAR NOTA EVALUACIÓN FINAL");
                                                System.out.println("Alumnos que pueden rendir evaluación: ");
                                                c.mostrarAlumnosEvaluacionFinal();
                                                System.out.print("\n");
                                                System.out.println("Ingrese RUT del alumno sin puntos y con guión (Ej:20132111-k):");
                                                rutAlumno = lector.nextLine();  
                                                System.out.print("\n");
                                                if (c.validarAlumnoEvaluacion(rutAlumno) != true) 
                                                {
                                                    System.out.println("El Alumno '"+rutAlumno+"' no esta admitido a rendir Evaluación final, intente nuevamente");
                                                    System.out.print("\n");
                                                }
                                            }while( c.validarAlumnoEvaluacion(rutAlumno) != true);
                                            do
                                            {
                                                System.out.println("Ingrese Nota para el Alumno "+ rutAlumno +" en la Evaluación Final (1.0-7.0):");
                                                notaAlumno=Double.parseDouble(lector.nextLine());
                                                System.out.print("\n");
                                                if((notaAlumno >= 1.0 && notaAlumno <= 7.0))
                                                {
                                                    c.addNotaEvaluacionFinal(rutAlumno,notaAlumno);
                                                    actualizarEstado(c.getEstadoAlumno(rutAlumno));
                                                }else
                                                {
                                                    System.out.println("Formato inválido, intente nuevamente ingresando un número entre 1.0 y 7.0.");
                                                    System.out.print("\n");
                                                }
                                            }while((notaAlumno >= 1.0 && notaAlumno <= 7.0)==false);
                                            break;
                                    }
                                }while(salir);
                                break;
                        }
                               
                    }while(atras);
                    break;
                default:
                    System.out.println("Ingrese opción valida: ");
                    opcion = lector.nextInt();
            }
            
        }while(salir);
    }
    
    // Metodos
    public static void reportarEstado(double promedio){
        String estado;
        if(promedio >= 4.0){
            estado = aprobado;
        }
        else{
            if(promedio == 0.0){
            estado = desconocido;
            }
            else{
                estado = reprobado;
            }
        }
        System.out.println(estado);
    }
    public static void actualizarEstado(int estado)
    {
        switch(estado)
        {
            case 0:
                System.out.println(desconocido);
                break;
            case 1:
                System.out.println(aprobado+" CON BECA");
                break;
            case 2:
                System.out.println(aprobado+" SIN BECA");
                break;
            case 3:
                System.out.println(reprobado);
                break;
            case 4:
                System.out.println(reprobado);
                break;
        }
        System.out.println("\n");
    }
    
    // se genera un reporte de los datos guardados, generando un reporte de cada curso con sus asignaturas, unidades, alumnos y sus notas
    public static void generarReporte(ManejoDeCursos c) throws IOException
    {
        // Ademas de generar un reporte, también se generará el CSV de notas, para la proxima inicialización del programa
        generarNotasCSV(c);
        System.out.println("Notas Actualizadas.");
        // El reporte tendrá de nombre la fecha de creacion de este 
        Calendar fecha = Calendar.getInstance();
        String nombreArchivo = "Reporte "+Integer.toString(fecha.get(Calendar.DATE))+"-"+Integer.toString(fecha.get(Calendar.MONTH))+"-"+ Integer.toString(fecha.get(Calendar.YEAR));
        File archivo = new File(nombreArchivo+".xlsx");
        
        // se obtiene una lista con los cursos
        ArrayList<String> listaNombreCursos = c.getNombreCursos();
        
        // Creamos el libro de trabajo de Excel formato OOXML
        Workbook libro = new XSSFWorkbook();
        
        // se recorrerá la lista de los cursos para crear una pagina exclusiva para cada uno
        for(int i = 0 ; i< listaNombreCursos.size() ; i++)
        {
            // se guarda una lista de los ruts y asignaturas del curso
            ArrayList<String> listaRutAlumnos = c.getRutAlumnos(listaNombreCursos.get(i));
            if (listaRutAlumnos.isEmpty()) listaRutAlumnos.add("none");
            ArrayList<String> listaAsignaturas = c.getNombresAsignaturas(listaNombreCursos.get(i));
            
            
            // Se crea la hoja del reporte del curso i
            Sheet pagina = libro.createSheet("Reporte de Curso "+listaNombreCursos.get(i));

            // Se crean estilos para las celdas de las asignaturas para diferenciarlas (amarilla=celdas pares , verdes=celdas impares)
            CellStyle style = libro.createCellStyle();
            style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            CellStyle style2 = libro.createCellStyle();
            style2.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            // se crean las filas y celdas 
            Row fila = pagina.createRow(0);
            Cell celda = fila.createCell(0);
            Row fila2 = pagina.createRow(1);
            Cell celda2 = fila.createCell(0);
            
            
            // la variable "ultimaFila" se usará para guardar en el excel los bancos de preguntas antes que los alumnos
            int ultimaFila = listaRutAlumnos.size()+4;
            Row fila3 = pagina.createRow(ultimaFila);
            Cell celda3 = fila3.createCell(0);
            celda3.setCellValue("Banco de Preguntas");
            
            // la variable "filaPromedios" se usará para saber en que fila se imprimirán los promedios de las unidades
            // y la variable "cantUnidades" se usará para saber en que columna se imprimirá los promedios de las unidades
            int filaPromedios = listaRutAlumnos.size()+2,cantUnidades=0;
            Row fila4 = pagina.createRow(filaPromedios);
            Cell celda4 = fila4.createCell(0);
            celda4.setCellValue("Promedio");
            
            
            int celdasASaltar=0;
            for (int j = 0; j < listaAsignaturas.size(); j++) {
                ArrayList<String> listaUnidades = c.getUnidadesAsignatura(listaNombreCursos.get(i),listaAsignaturas.get(j));
                if(j==0){
                    celda = fila.createCell(1);
                    celdasASaltar=listaUnidades.size();
                }else{
                    celda = fila.createCell(fila.getLastCellNum()+celdasASaltar-1);
                    celdasASaltar=listaUnidades.size();
                }
                if((j+1)%2==0){
                    celda.setCellStyle(style);
                } else{
                    celda.setCellStyle(style2);
                }
                celda.setCellValue(listaAsignaturas.get(j));
                
                for (int k = 0 ; k < listaUnidades.size() ; k++ )
                {
                    cantUnidades++;
                    celda4 = fila4.createCell(cantUnidades);
                    celda4.setCellValue(Precision.round(c.getPromedioNotasUnidad(listaNombreCursos.get(i), listaAsignaturas.get(j), listaUnidades.get(k)),1));
                    if(j==0&&k==0){
                        celda2 = fila2.createCell(1);
                    }else{
                        celda2 = fila2.createCell(fila2.getLastCellNum());
                    }
                    celda2.setCellStyle(celda.getCellStyle());
                    celda2.setCellValue(listaUnidades.get(k));
                    
                    //Se guardan los bancos de preguntas bajo las notas de los alumnos
                    ArrayList <String> preguntas = c.getBancoPreguntas(listaNombreCursos.get(i),listaAsignaturas.get(j),listaUnidades.get(k));
                    if (preguntas.isEmpty()) preguntas.add("none");
                    fila3 = pagina.createRow(ultimaFila+1);
                    celda3 = fila3.createCell(0);
                    celda3.setCellValue(listaUnidades.get(k));
                    celda3.setCellStyle(celda.getCellStyle());
                    for(int y = 0; y < preguntas.size() ; y++)
                    {
                        celda3 = fila3.createCell(y+1);
                        celda3.setCellValue(preguntas.get(y));
                    }
                    ultimaFila++;
                }
            }
           
            // se guardan los rut de los alumnos y sus notas respectivas
            for (int k = 0, j=2; k < listaRutAlumnos.size(); k++, j++) {
                ArrayList<Double> notasAlumno = c.getNotasAlumno(listaNombreCursos.get(i),listaRutAlumnos.get(k));
                if(notasAlumno.contains(-1.0)!=true)
                {
                    fila= pagina.createRow(j);
                    celda = fila.createCell(0);
                    celda.setCellValue(listaRutAlumnos.get(k));
                    for (int z = 0 ; z < notasAlumno.size() ; z++)
                    {
                        celda = fila.createCell(z+1);
                        celda.setCellValue(notasAlumno.get(z));
                    }
                }
                
            }
        }
        
        // se guarda y se cierra el archivo
        try {
            FileOutputStream salida = new FileOutputStream(archivo);
            libro.write(salida);
            libro.close();

           
            System.out.println("Reporte Generado.");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
/*
modelo de submenus
atras=true;
do{
    // subMenu
    System.out.print("\n");
    System.out.println("Opcion:");
    System.out.println("1. Ingresar banco de preguntas");
    System.out.println("0. Atras"); 
    opcion2 = lector.nextInt();         
    lector.nextLine();
    System.out.print("\n");
    switch (opcion2){
        case 0: 
            atras=false;
            break;
        case 1:
            break;
        default:
            System.out.println("Ingrese opcion valida:");
            opcion2 = lector.nextInt();
    }
}while(atras);
break;


*/