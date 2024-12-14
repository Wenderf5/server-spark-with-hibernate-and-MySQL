package com.dbconnectionwithhibernate.modules.user_module.services;

import com.dbconnectionwithhibernate.config.db_config.DataBaseConnection;
import com.dbconnectionwithhibernate.entities.user_entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.vavr.control.Either;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import spark.Response;

import java.util.List;

public class GetAllUsersService {
    public static Either<String, JsonObject> getAllUsersService(Response res) {
        SessionFactory factory = DataBaseConnection.newFactory(User.class);
        Session session = DataBaseConnection.newSession(factory);

        try {
            session.beginTransaction();
            String hql = "FROM User";
            List<User> users = session.createQuery(hql, User.class).getResultList();
            session.getTransaction().commit();
            Gson gson = new Gson();
            String responseJson = gson.toJson(users);
            res.type("application/json");
            res.status(200);
            return Either.left(responseJson);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            res.type("application/json");
            res.status(400);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("message", "Erro ao buscar usuários!");
            return Either.right(responseJson);
        } finally {
            factory.close();
        }
    }
}
