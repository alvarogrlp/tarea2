package es.ies.puerto.view;

import es.ies.puerto.model.Empleado;
import es.ies.puerto.model.fichero.OperacionesFicheros;

public class Programa {

    public static void main(String[] args) {
        OperacionesFicheros fichero = new OperacionesFicheros();
        Empleado empleado1 = new Empleado("11111111U", "Juan Pérez", "Desarrollador", 3000.50,
                "15/10/1995");
        Empleado empleado2 = new Empleado("00000000U", "Ana Gómez", "Diseñadora", 2800.75,
                "10/01/1990");
        Empleado empleado3 = new Empleado("22222222U", "Luis López", "Gerente", 4000.00,
                "30/07/2000");
        Empleado empleado4 = new Empleado("66666666U", "Alvaro Garcia", "Gerente", 10000.00,
                "04/02/2000");
                
        String hoy = "01/02/2025";
        String otroDia = "12/04/1999";

        fichero.create(empleado1);
        fichero.create(empleado2);
        fichero.create(empleado3);
        fichero.create(empleado4);

        System.out.println(empleado4.getEdad());

    }
}
