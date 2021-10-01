package com.mycompany.proyectopoo;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Proyecto {
    
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
        boolean atras;
        boolean salir = true;
        
        ManejoDeCursos c = new ManejoDeCursos();
        c.crearCursos();
        
        
        do{
            
            // Menu
            System.out.println("---MENU PRINCIPAL---");
            System.out.println("[1] Impresión de datos");
            System.out.println("[2] Agregar/Llenar datos");
            System.out.println("[3] Eliminar datos");
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
                        System.out.println("Opcion: IMPRESIÓN DE DATOS");
                        System.out.println("[1] Mostrar cursos");
                        System.out.println("[2] Mostrar Alumnos de TODOS los cursos");
                        System.out.println("[3] Mostrar Alumnos de curso específico");
                        System.out.println("[4] Mostrar Asignaturas y Unidades de TODOS los cursos");
                        System.out.println("[5] Mostrar Asignaturas y Unidades de curso específico");
                        System.out.println("[6] Mostrar Notas de alumnos de un curso");
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
                                System.out.println("Opción: IMPRESION DE ASIGNATURAS Y UNIDADES DE UN CURSO ESPECÍFICO");
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
                        System.out.println("[5] Ingresar banco de preguntas");
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
                                System.out.println("Ingrese opcion valida");
                                opcion2 = lector.nextInt();
                        }
                    }while(atras);
                    break;
                case 3: // opcion eliminar
                    atras=true;
                    do
                    {
                        System.out.print("\n");
                        System.out.println("Opcion: ELIMINAR DATOS");
                        System.out.println("[1] Eliminar Alumno de Curso");
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
                                    System.out.println("Opcion: ELIMINAR ALUMNO DE CURSO");
                                    System.out.println("Alumnos en el sistema: ");
                                    c.mostrarTodosAlumnos();
                                    System.out.print("\n");
                                    System.out.println("Ingrese RUT del alumno sin puntos y con guión (Ej:20132111-k)():");
                                    rutAlumno = lector.nextLine();  
                                    System.out.print("\n");
                                }while( c.validarAlumno(rutAlumno)!=true || rutAlumno.equals("-1"));
                                if (rutAlumno.equals("-1")) break;
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
                        }
                                
                    }while(atras);
                    break;
                default:
                    System.out.println("Ingrese opción valida: ");
                    opcion = lector.nextInt();
            }
            
        }while(salir);
    }
    // se genera un reporte de los datos guardados, generando un reporte de cada curso con sus asignaturas, unidades, alumnos y sus notas
    public static void generarReporte(ManejoDeCursos c) throws IOException
    {
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
            int ultimaFila=listaRutAlumnos.size()+3;
            Row fila3 = pagina.createRow(ultimaFila);
            Cell celda3 = fila3.createCell(0);
            celda3.setCellValue("Banco de Preguntas");
            
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
        
        // se guarda y se cierra el archivo
        try {
            FileOutputStream salida = new FileOutputStream(archivo);
            libro.write(salida);
            libro.close();

           

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void opcionImpresion()
    {
        
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