package com.dbconnectionwithhibernate.routes;

import static com.dbconnectionwithhibernate.modules.user_module.controllers.UserController.userController;

public class Routes {
    public static void start() {
        userController();
    }
}
