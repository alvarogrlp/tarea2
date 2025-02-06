package es.ies.puerto.model;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import es.ies.puerto.model.fichero.OperacionesFicheros;

public class OperacionesMap extends OperacionesFicheros{

    public OperacionesMap() {
        super();
    }

    public Map<String, Empleado> readMap(File file) {
        Map<String, Empleado> empleados = new TreeMap<>();
        Set<Empleado> empleadosSet = super.read(file);
        for (Empleado empleado : empleadosSet) {
            empleados.put(empleado.getIdentificador(), empleado);
        }
        return empleados;
    }
}
