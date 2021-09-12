package com.mycompany.proyectopoo;
import java.io.*;
import java.util.Scanner;
public class Proyecto {
    
    public static void main(String [] arg) throws IOException 
    {
        Scanner lector = new Scanner(System.in);
        int opcion;
        int opcion2;
        String nombreCurso = null;
        String nombreAsig = null;
        String nombreUnidad = null;
        String datoAGuardar = null;
        String respuesta = null;
        boolean atras;
        boolean salir = true;
        
        ManejoDeCursos c = new ManejoDeCursos();
        c.crearCursos();
        
        
        do{
            
            // Menu
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. Impresión de datos");
            System.out.println("2. Agregar/Llenar datos");
            System.out.println("0. Salir");
            System.out.println("Ingrese opción: ");
            opcion = lector.nextInt(); // Ingresan opcion            
            lector.nextLine(); // Limpiar '\n'
            switch (opcion){
                case 0: 
                    salir = false;
                    break;
                case 1: // Impresion de datos
                    atras = true;
                    do{
                        // Menu
                        System.out.print("\n");
                        System.out.println("Opcion: IMPRESION DE DATOS");
                        System.out.println("1. Mostrar cursos");
                        System.out.println("2. Mostrar Alumnos de TODOS los cursos");
                        System.out.println("3. Mostrar Alumnos de curso especifico");
                        System.out.println("4. Mostrar Asignaturas y Unidades de TODOS los cursos");
                        System.out.println("5. Mostrar Asignaturas y Unidades de curso especifico");
                        System.out.println("6. Mostrar Notas de alumnos de un curso");
                        System.out.println("0. Atras"); 
                        System.out.println("Ingrese opción: ");
                        opcion2 = lector.nextInt();         
                        lector.nextLine();
                        System.out.print("\n");
                        switch (opcion2){
                            case 0: 
                                atras = false;
                                break;
                            case 1:
                                System.out.println("Opcion: IMPRESION DE CURSOS");
                                c.mostrarNombreCursos();
                                break;
                            case 2: 
                                System.out.println("Opcion: IMPRESION DE TODOS LOS ALUMNOS");
                                c.mostrarTodosAlumnos();
                                break;
                            case 3:
                                System.out.println("Opcion: IMPRESION DE LOS ALUMNOS DE UN CURSO ESPECIFICO");
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
                                System.out.println("Opcion: IMPRESION DE ASIGNATURAS Y UNIDADES DE TODOS LOS CURSOS");
                                c.mostrarTodosAsigYUnidades();
                                break;
                            case 5: 
                                System.out.println("Opcion: IMPRESION DE ASIGNATURAS Y UNIDADES DE UN CURSO ESPECIFICO");
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
                                System.out.println("Opcion: IMPRESION DE ASIGNATURAS Y UNIDADES DE UN CURSO ESPECIFICO");
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
                            default:
                                System.out.println("Ingrese opcion valida:");
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
                        System.out.println("1. Agregar Curso al sistema");
                        System.out.println("2. Agregar Asignatura a Curso");
                        System.out.println("3. Agregar Unidad a Asigantura");
                        System.out.println("4. Agregar Alumno a Curso");
                        System.out.println("5. Ingresar banco de preguntas");
                        System.out.println("0. Atras"); 
                        System.out.println("Ingrese opción: ");
                        opcion2 = lector.nextInt();         
                        lector.nextLine();
                        System.out.print("\n");
                        switch (opcion2){
                            case 0: 
                                atras = false;
                                break;
                                
                            case 1:
                                System.out.println("Opcion: AGREGAR CURSO AL SISTEMA");
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
                                System.out.println("Opcion: AGREGAR ASIGNATURA AL CURSO");
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
                                    System.out.println("Ingrese nombre de la Unidad a añadir a la Asignatura " + nombreAsig + ":");
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
                                System.out.println("Opcion: AGREGAR UNIDAD A ASIGNATURA");
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
                                System.out.println("Opcion: AGREGAR ALUMNO AL CURSO");
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
                                System.out.println("Opcion: AGREGAR BANCO DE PREGUNTAS A UNIDAD");
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
                                        System.out.println("Opcion: Llenado banco de preguntas de " +nombreCurso+"-"+ nombreAsig+"-"+nombreUnidad);
                                        System.out.println("1. Ingresar pregunta");
                                        System.out.println("2. Mostrar banco de preguntas");
                                        System.out.println("0. Atras"); 
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
                                System.out.println("Ingrese opcion valida");
                                opcion2 = lector.nextInt();
                        }
                    }while(atras);
                    
                    break;
                default:
                    System.out.println("Ingrese opción valida: ");
                    opcion = lector.nextInt();
            }
            
        }while(salir);
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