package com.mycompany.proyectopoo;
import java.io.*;
import java.util.Scanner;
public class Proyecto {
    
    public static void main(String [] arg) throws IOException 
    {
        Scanner lector = new Scanner(System.in);
        int opcion;
        int opcion2;
        String nombreCurso=null;
        String nombreAsig=null;
        String nombreUnidad=null;
        String pregunta=null;
        boolean atras;
        boolean salir = true;
        
        ManejoDeCursos c = new ManejoDeCursos();
        c.crearCursos();
        
        do{
            
            // Menu
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. Impresión de datos");
            System.out.println("2. Llenado de datos");
            System.out.println("0. Salir");
            System.out.println("Ingrese opción: ");
            opcion = lector.nextInt(); // Ingresan opcion            
            lector.nextLine(); // Limpiar '\n'
            switch (opcion){
                case 0: 
                    salir=false;
                    break;
                case 1: // Impresion de datos
                    atras=true;
                    do{
                        // Menu
                        System.out.print("\n");
                        System.out.println("Opcion: IMPRESION DE DATOS");
                        System.out.println("1. Mostrar cursos");
                        System.out.println("2. Mostrar Alumnos de TODOS los cursos");
                        System.out.println("3. Mostrar Alumnos de curso especifico");
                        System.out.println("4. Mostrar Asignaturas y Unidades de TODOS los cursos");
                        System.out.println("5. Mostrar Asignaturas y Unidades de curso especifico");
                        System.out.println("0. Atras"); 
                        System.out.println("Ingrese opción: ");
                        opcion2 = lector.nextInt();         
                        lector.nextLine();
                        System.out.print("\n");
                        switch (opcion2){
                            case 0: 
                                atras=false;
                                break;
                            case 1:
                                System.out.println("Cursos en el sistema:");
                                c.mostrarNombreCursos();
                                break;
                            case 2: 
                                System.out.println("Alumnos en el sistema:");
                                c.mostrarTodosAlumnos();
                                break;
                            case 3:
                                do
                                {
                                    System.out.println("Cursos en el sistema:");
                                    c.mostrarNombreCursos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese nombre del curso:");
                                    nombreCurso=lector.nextLine();
                                }while(c.validarCurso(nombreCurso)==false);
                                c.mostrarAlumnosCurso(nombreCurso);
                                break;
                            case 4: 
                                c.mostrarTodosAsigYUnidades();
                                break;
                            case 5: 
                                do
                                {
                                    System.out.println("Cursos en el sistema:");
                                    c.mostrarNombreCursos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese nombre del curso:");
                                    nombreCurso=lector.nextLine();
                                }while(c.validarCurso(nombreCurso)==false);
                                c.mostrarAsigYUnidadesCurso(nombreCurso);
                                break;
                            default:
                                System.out.println("Ingrese opcion valida:");
                                opcion2 = lector.nextInt();
                        }
                    }while(atras);
                    break;
                case 2: // Llenado
                    atras=true;
                    do{
                        // Menu
                        System.out.print("\n");
                        System.out.println("Opcion: LLENADO DE DATOS");
                        System.out.println("1. Ingresar banco de preguntas");
                        System.out.println("0. Atras"); 
                        System.out.println("Ingrese opción: ");
                        opcion2 = lector.nextInt();         
                        lector.nextLine();
                        System.out.print("\n");
                        switch (opcion2){
                            case 0: 
                                atras=false;
                                break;
                            case 1:
                                System.out.println("Para ingresar un banco de preguntas, debe especificar Curso, Asigantura y Unidad de las preguntas");
                                do
                                {
                                    System.out.println("Cursos en el sistema:");
                                    c.mostrarNombreCursos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese Curso de la Asignatura:");
                                    nombreCurso = lector.nextLine();  
                                    System.out.print("\n");
                                }while(c.validarCurso(nombreCurso)==false);
                        
                                do
                                {
                                    System.out.println("Asginaturas del Curso "+ nombreCurso+":");
                                    c.mostrarNombresAsig(nombreCurso);
                                    System.out.print("\n");
                                    System.out.println("Ingrese Asginatura de la Unidad:");
                                    nombreAsig = lector.nextLine();
                                    System.out.print("\n");
                                }while(c.validarAsignatura(nombreCurso,nombreAsig)==false);
                                
                                do
                                {
                                    System.out.println("Unidades de la Asignatura "+ nombreAsig+":");
                                    c.mostrarNombresUnidades(nombreCurso,nombreAsig); 
                                    System.out.print("\n");
                                    System.out.println("Ingrese Unidad de la Asignatura:");
                                    nombreUnidad = lector.nextLine();
                                }while(c.validarUnidad(nombreCurso,nombreAsig,nombreUnidad)==false);
                                atras=true;
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
                                            atras=false;
                                            break;
                                        case 1:
                                            String respuesta;
                                            do
                                            {
                                                System.out.println("Ingrese pregunta a añadir:");
                                                pregunta=lector.nextLine();
                                                System.out.print("\n");
                                                do
                                                {
                                                    System.out.println("¿Desea añadir " + "'"+pregunta+"'?");
                                                    System.out.println("Ingrese opción: (Si/No)");
                                                    respuesta=lector.nextLine().toLowerCase();
                                                }while((respuesta.equals("no")!=true)&&(respuesta.equals("si")!=true));
                                            }while (respuesta.equals("no"));
                                            
                                            c.addPreguntaUnidad(nombreCurso,nombreAsig,nombreUnidad,pregunta,true);
                                            break;
                                        case 2:
                                            c.mostrarBancoPreguntasUnidad(nombreCurso,nombreAsig,nombreUnidad);
                                            break;
                                        default:
                                            System.out.println("Ingrese opcion valida:");
                                            opcion2 = lector.nextInt();
                                    }
                                }while(atras);
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