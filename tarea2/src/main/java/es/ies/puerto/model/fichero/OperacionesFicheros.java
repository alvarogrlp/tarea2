package es.ies.puerto.model.fichero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import es.ies.puerto.model.Empleado;
import es.ies.puerto.model.OperacionesInterfaces;

/**
 * Clase para operar con los ficheros 
 */
public class OperacionesFicheros implements OperacionesInterfaces {

    File fichero;
    String path = "C:\\Users\\alvar\\Desktop\\Programación\\Ejercicios\\tarea2\\src\\main\\resources\\archivo.txt";

    /**
     * Constructor del archivo
     */
    public OperacionesFicheros() {
        fichero = new File(path);
        if (!fichero.exists() || !fichero.isFile()) {
            throw new IllegalArgumentException("El recurso no es de tipo fichero" + path);
        }
    }

    /**
     * Metodo que crea un empleado en el  archivo
     * 
     * @param empleado que se quiere crear
     */
    @Override
    public boolean create(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null) {
            return false;
        }
        Set<Empleado> empleados = read(fichero);
        if (empleados.contains(empleado)) {
            return false;
        }
        return create(empleado.toString(), fichero);

    }

    /**
     * Metodo para crear un fichero
     * @param data
     * @param file
     * @return
     */
    private boolean create(String data, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(data);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo para leer un archivo entero
     * @param file que se quiere leer
     * @return Set del archivo
     */
    public Set<Empleado> read(File file) {
        Set<Empleado> empleados = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arrayLine = line.split(",");
                Empleado empleado = new Empleado(arrayLine[0], arrayLine[1], arrayLine[2],
                        Double.parseDouble(arrayLine[3]), arrayLine[4]);
                empleados.add(empleado);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return empleados;
    }

    /**
     * Metodo que lee segun el identificador
     * 
     * @param identificador del empleado que se quiere leer
     */
    @Override
    public Empleado read(String identificador) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arrayLine = line.split(",");
                if (arrayLine[0].equals(identificador)) {
                    return new Empleado(arrayLine[0], arrayLine[1], arrayLine[2],
                            Double.parseDouble(arrayLine[3]), arrayLine[4]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return null;
    }

    /**
     * Metodo que lee el empleado indicado
     * 
     * @param empleado que se quiere leer
     */
    @Override
    public Empleado read(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null) {
            return null;
        }
        return empleado;
    }

    /**
     * Metodo que actualiza un empleado
     * 
     * @param empleado con la nueva informacion
     */
    @Override
    public boolean update(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null) {
            return false;
        }
        Set<Empleado> empleados = read(fichero);
        if (!empleados.contains(empleado)) {
            return false;
        }
        Iterator<Empleado> iterator = empleados.iterator();
        while (iterator.hasNext()) {
            Empleado empleadoBuscado = iterator.next();
            if (empleadoBuscado.equals(empleado)) {
                iterator.remove();
                empleados.add(empleado);
                return updateFile(empleados, fichero);
            }
        }
        return true;
    }

    /**
     * Metodo para actualizar el archivo
     * @param empleados Set
     * @param file que se quiere actualizar
     * @return
     */
    private boolean updateFile(Set<Empleado> empleados, File file) {
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            return false;
        }
        for (Empleado empleado : empleados) {
            create(empleado);
        }
        return true;
    }

    
    /**
     * Metodo que elimina segun el identificador
     * 
     * @param identificador del empleado que se quiere eliminar
     */
    @Override
    public boolean delete(String identificador) {
        if (identificador == null) {
            return false;
        }
        Set<Empleado> empleados = read(fichero);
        for (Empleado empleadoBuscado : empleados) {
            if (empleadoBuscado.getIdentificador().equals(identificador)) {
                empleados.remove(empleadoBuscado);
                return updateFile(empleados, fichero);
            }
        }

        return true;
    }

    /**
     * Metodo que filtra los empleados por el puesto indicado
     * 
     * @param puesto que se quiere filtrar
     */
    @Override
    public Set<Empleado> empleadosPorPuesto(String puesto) {
        if (puesto == null) {
            return null;
        }
        Set<Empleado> empleados = read(fichero);
        if (empleados == null) {
            return null;
        }

        Iterator<Empleado> iterator = empleados.iterator();
        while (iterator.hasNext()) {
            Empleado empleadoBuscado = iterator.next();
            if (!empleadoBuscado.getPuesto().equals(puesto)) {
                iterator.remove();
            }
        }
        return empleados;
    }

    /**
     * Metodo que filtra por edad
     * 
     * @param fechaInicio primera fecha que se usara
     * @param fechaFin segunda fecha que se usara
     */
    @Override
    public Set<Empleado> empleadosPorEdad(String fechaInicio, String fechaFin) {
        String fechaInicial = fechaInicio;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inicio = LocalDate.parse(fechaInicial, formato);
        String fechaFinal = fechaFin;
        LocalDate fin = LocalDate.parse(fechaFinal, formato);
        Set<Empleado> empleados = read(fichero);
        if (empleados == null) {
            return null;
        }

        Iterator<Empleado> iterator = empleados.iterator();
        while (iterator.hasNext()) {
            Empleado empleadoBuscado = iterator.next();
            LocalDate fechaBuscada = empleadoBuscado.getFechaNacimientoDate();
            if (fechaBuscada.isAfter(inicio) || fechaBuscada.isBefore(fin)) {
                iterator.remove();
            }
        }
        return empleados;
    }
}
