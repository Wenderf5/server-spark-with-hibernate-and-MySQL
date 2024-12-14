package com.dbconnectionwithhibernate.modules.user_module.services;

import com.dbconnectionwithhibernate.config.db_config.DataBaseConnection;
import com.dbconnectionwithhibernate.entities.user_entity.User;
import com.google.gson.JsonObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import spark.Response;

public class UpdateUserService {
    public static JsonObject updateUserService(Response res, String id, String name) {
        SessionFactory factory = DataBaseConnection.newFactory(User.class);
        Session session = DataBaseConnection.newSession(factory);

        try {
            session.beginTransaction();
            User userToUpdate = session.get(User.class, id);
            if (userToUpdate == null) {
                JsonObject responseJson = new JsonObject();
                responseJson.addProperty("message", "Esse usuário não existe!");
                res.type("application/json");
                res.status(404);
                return responseJson;
            }
            userToUpdate.setName(name);
            session.update(userToUpdate);
            session.getTransaction().commit();

            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("message", "Usuário atualizado com sucesso!");
            res.type("application/json");
            res.status(200);
            return responseJson;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("message", "Erro ao atualizar usuário!");
            res.type("application/json");
            res.status(400);
            return responseJson;
        } finally {
            factory.close();
        }
    }
}
