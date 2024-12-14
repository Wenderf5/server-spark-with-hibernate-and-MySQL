package com.dbconnectionwithhibernate.modules.user_module.services;

import com.dbconnectionwithhibernate.config.db_config.DataBaseConnection;
import com.dbconnectionwithhibernate.entities.user_entity.User;
import com.google.gson.JsonObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import spark.Response;

public class DeleteUserService {
    public static JsonObject deleteUserService(Response res, String id) {
        SessionFactory factory = DataBaseConnection.newFactory(User.class);
        Session session = DataBaseConnection.newSession(factory);

        try {
            session.beginTransaction();
            User userToDelete = session.get(User.class, id);
            if (userToDelete == null) {
                res.type("application/json");
                res.status(404);
                JsonObject responseJson = new JsonObject();
                responseJson.addProperty("message", "Esse usuário não existe!");
                return responseJson;
            }
            session.delete(userToDelete);
            session.getTransaction().commit();
            res.type("application/json");
            res.status(200);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("message", "usuário apagado com sucesso!");
            return responseJson;
        } catch (Exception e) {
            res.type("application/json");
            res.status(400);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("message", "Erro ao apagar usuário!");
            return responseJson;
        } finally {
            factory.close();
        }
    }
}
