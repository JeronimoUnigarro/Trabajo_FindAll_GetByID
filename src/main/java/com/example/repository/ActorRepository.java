package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Actor;
import com.example.utils.DatabaseConnection;

public class ActorRepository implements Repository<Actor> {

    @Override
    public List<Actor> findAll() {
        List<Actor> actors = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM actor")) {
            
            while (rs.next()) {
                Actor actor = new Actor();
                actor.setActorID(rs.getInt("actor_id"));
                actor.setFirstName(rs.getString("first_name"));
                actor.setLastName(rs.getString("last_name"));
                actors.add(actor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actors;
    }

    @Override
    public Actor getByID(Integer id) {
        Actor actor = null;
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM actor WHERE actor_id = ?")) {
            
            stmt.setInt(1, id);  // Establecer el parámetro del ID
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    actor = new Actor();
                    actor.setActorID(rs.getInt("actor_id"));
                    actor.setFirstName(rs.getString("first_name"));
                    actor.setLastName(rs.getString("last_name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actor;
    }

    @Override
    public void save(Actor t) {
        
    }

    @Override
    public void delete(Integer id) {
        
    }
}