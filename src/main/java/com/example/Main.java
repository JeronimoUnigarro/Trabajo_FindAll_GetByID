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
            

            System.out.println("------ Lista de Todos los Actores ------");
            List<Actor> actores = actorRepository.findAll();
            for (Actor actor : actores) {
                System.out.println(actor);
            }
            
            // ingresar id para buscar actor
            System.out.println("\nIngrese el ID del actor que desea buscar:");
            int actorId = scanner.nextInt(); // leer id
            Actor actor = actorRepository.getByID(actorId); // buscar actor
            
            if (actor != null) {
                System.out.println("\n------ Actor Encontrado ------");
                System.out.println(actor);
            } else {
                System.out.println("\nActor no encontrado.");
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error de conexión.");
            e.printStackTrace();
        }
    }
}