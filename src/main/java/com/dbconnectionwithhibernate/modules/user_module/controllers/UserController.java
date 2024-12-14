package com.dbconnectionwithhibernate.modules.user_module.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import static com.dbconnectionwithhibernate.modules.user_module.services.DeleteUserService.deleteUserService;
import static com.dbconnectionwithhibernate.modules.user_module.services.GetAllUsersService.getAllUsersService;
import static com.dbconnectionwithhibernate.modules.user_module.services.NewUserService.newUserService;
import static com.dbconnectionwithhibernate.modules.user_module.services.UpdateUserService.updateUserService;
import static spark.Spark.*;

public class UserController {
    public static void userController() {
        get("/user", (req, res) -> {
            return getAllUsersService(res);
        });

        post("/user", (req, res) -> {
            JsonObject bodyAsJson = new Gson().fromJson(req.body(), JsonObject.class);
            String name = bodyAsJson.get("name").getAsString();
            return newUserService(res, name);
        });

        patch("/user/:id", (req, res) -> {
            JsonObject bodyAsJson = new Gson().fromJson(req.body(), JsonObject.class);
            String name = bodyAsJson.get("name").getAsString();
            return updateUserService(res, req.params("id"), name);
        });

        delete("/user/:id", (req, res) -> {
            return deleteUserService(res, req.params("id"));
        });
    }
}
