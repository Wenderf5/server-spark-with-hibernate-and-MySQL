package com.dbconnectionwithhibernate.modules.user_module.services;

import com.dbconnectionwithhibernate.config.db_config.DataBaseConnection;
import com.dbconnectionwithhibernate.entities.user_entity.User;
import com.google.gson.JsonObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import spark.Response;

import java.util.UUID;

public class NewUserService {
    public static JsonObject newUserService(Response res, String name) {
        SessionFactory factory = DataBaseConnection.newFactory(User.class);
        Session session = DataBaseConnection.newSession(factory);

        try {
            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            User user = new User(uuidAsString, name);

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("message", "Usuário cadastrado com sucesso!");
            res.type("application/json");
            res.status(201);
            return responseJson;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("message", "Erro ao cadastrar usuário!");
            res.type("application/json");
            res.status(400);
            return responseJson;
        } finally {
            factory.close();
        }
    }
}
