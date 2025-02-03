# Implementación de un Sistema CRUD con Ficheros

## Estructura del Programa
El programa estará compuesto por las siguientes entidades principales:

1. **Interfaz OperacionesIterfaces**: Define los métodos CRUD.
2. **Clase Empleado**: Representa a un empleado con atributos clave.
3. **Clase OperacionesFichero**: Implementa la interfaz y maneja los datos en archivos.
4. **Clase Programa**: Contiene el método `main` y realiza pruebas de las funciones CRUD.

## 1. Interfaz `Operations`
Esta interfaz define los métodos esenciales para la gestión de empleados:

- `create(Empleado empleado)`: Añade un nuevo empleado al archivo.
- `read(String identificador)`: Busca un empleado por su ID.
- `read(Empleado empleado)`: Recupera un empleado por su objeto.
- `update(Empleado empleado)`: Modifica los datos de un empleado existente.
- `delete(String identificador)`: Elimina un empleado del archivo.
- `empleadosPorPuesto(String puesto)`: Devuelve empleados con un puesto específico.
- `empleadosPorEdad(String fechaInicio, String fechaFin)`: Lista empleados dentro de un rango de edad.

## 2. Clase `Empleado`
Esta clase modela a un empleado con los siguientes atributos:

- `identificador`: ID único del empleado.
- `nombre`: Nombre completo.
- `puesto`: Cargo en la empresa.
- `salario`: Salario mensual.
- `fechaNacimiento`: Fecha de nacimiento en formato `dd/mm/aaaa`.

**Métodos implementados:**
- Constructores y métodos `getters` y `setters`.
- `toString()`: Convierte un empleado en una cadena de texto.
- `equals()` y `hashCode()`: Comparación basada en el ID.
- `getEdad()`: Calcula la edad del empleado.

## 3. Clase `FileOperations`
Esta clase implementa `Operations` y maneja la persistencia de empleados en un archivo de texto (`empleados.txt`).

**Métodos Implementados:**
- `create()`: Agrega un nuevo empleado al archivo.
- `read()`: Busca un empleado en el archivo y devuelve sus datos.
- `update()`: Modifica un empleado en el archivo.
- `delete()`: Elimina un empleado del archivo.
- `empleadosPorPuesto()`: Filtra empleados por puesto.
- `empleadosPorEdad()`: Busca empleados según su edad.

El formato del archivo será:
```
1, Juan Pérez, Desarrollador, 3000.50, 15/10/1995
2, Ana Gómez, Diseñadora, 2800.75, 10/01/1990
```
Cada línea representa un empleado separado por comas.

## 4. Clase Principal (`Main`)
La clase `Main` será responsable de probar las funciones CRUD.

Ejemplo de uso:
```java
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

        System.out.println(empleado4.getEdad());OperacionesFicheros fichero = new OperacionesFicheros();
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
```


