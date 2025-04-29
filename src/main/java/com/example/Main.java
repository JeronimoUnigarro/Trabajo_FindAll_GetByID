package com.example;

import java.util.List;
import java.util.Scanner;

import com.example.model.Actor;
import com.example.repository.ActorRepository;

public class Main {
    public static void main(String[] args) {
        try {
            ActorRepository actorRepository = new ActorRepository();
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n------ MENÚ ------");
                System.out.println("1. Listar todos los actores");
                System.out.println("2. Buscar actor por ID");
                System.out.println("3. Crear nuevo actor");
                System.out.println("4. Actualizar actor existente");
                System.out.println("5. Eliminar actor");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // salto línea

                switch (opcion) {
                    case 1:
                        List<Actor> actores = actorRepository.findAll();
                        for (Actor actor : actores) {
                            System.out.println(actor);
                        }
                        break;

                    case 2:
                        System.out.print("Ingrese el ID del actor: ");
                        int idBuscar = scanner.nextInt();
                        Actor actor = actorRepository.getByID(idBuscar);
                        if (actor != null) {
                            System.out.println(actor);
                        } else {
                            System.out.println("Actor no encontrado.");
                        }
                        break;

                    case 3:
                        System.out.print("Ingrese el nombre: ");
                        String nombreNuevo = scanner.nextLine();
                        System.out.print("Ingrese el apellido: ");
                        String apellidoNuevo = scanner.nextLine();
                        Actor nuevoActor = new Actor();
                        nuevoActor.setFirstName(nombreNuevo);
                        nuevoActor.setLastName(apellidoNuevo);
                        actorRepository.save(nuevoActor);
                        System.out.println("Actor guardado exitosamente.");
                        break;

                    case 4:
                        System.out.print("Ingrese el ID del actor a actualizar: ");
                        int idActualizar = scanner.nextInt();
                        scanner.nextLine();
                        Actor actorExistente = actorRepository.getByID(idActualizar);
                        if (actorExistente != null) {
                            System.out.print("Nuevo nombre: ");
                            String nuevoNombre = scanner.nextLine();
                            System.out.print("Nuevo apellido: ");
                            String nuevoApellido = scanner.nextLine();
                            actorExistente.setFirstName(nuevoNombre);
                            actorExistente.setLastName(nuevoApellido);
                            actorRepository.update(actorExistente);
                            System.out.println("Actor actualizado exitosamente.");
                        } else {
                            System.out.println("Actor no encontrado.");
                        }
                        break;

                    case 5:
                        System.out.print("Ingrese el ID del actor a eliminar: ");
                        int idEliminar = scanner.nextInt();
                        actorRepository.delete(idEliminar);
                        System.out.println("Actor eliminado exitosamente.");
                        break;

                    case 6:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }

            } while (opcion != 6);

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error de conexión.");
            e.printStackTrace();
        }
    }
}
