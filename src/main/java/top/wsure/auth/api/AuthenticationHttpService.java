package top.wsure.auth.api;

import io.javalin.Javalin;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import top.wsure.auth.entity.Role;
import top.wsure.auth.entity.User;
import top.wsure.auth.service.AuthenticationService;
import top.wsure.auth.service.RoleService;
import top.wsure.auth.service.UserService;

import java.util.ArrayList;
import java.util.Set;

public class AuthenticationHttpService {

    private final UserService userService;
    private final RoleService roleService;
    private final AuthenticationService authenticationService;
    private final Javalin app;

    public AuthenticationHttpService(
            int port,
            UserService userService,
            RoleService roleService,
            AuthenticationService authenticationService
    ) {
        this.userService = userService;
        this.roleService = roleService;
        this.authenticationService = authenticationService;
        app = Javalin.create().start(port);
        initRoutes();
    }

    void initRoutes() {
        userServiceRoutes();
        roleServiceRoutes();
        authenticateServiceRoutes();
    }

    void userServiceRoutes() {
       post("/user/create", (ctx) -> {
            User user = new User(ctx.formParam("username"), ctx.formParam("password"));
           return userService.createUser(user);
        });

        post("/user/delete", (ctx) -> {
            User user = new User(ctx.formParam("username"), "");
            return userService.deleteUser(user);
        });

    }

    void roleServiceRoutes() {
        post("/role/create",(ctx) -> {
            Role role = new Role(ctx.formParam("name"), ctx.formParam("description"));
            return roleService.createRole(role);
        });

        post("/role/delete",(ctx) -> {
            Role role = new Role(ctx.formParam("name"), "");
            return roleService.deleteRole(role);
        });

        post("/role/associate",(ctx) -> {
            Role role = new Role(ctx.formParam("rolename"), "");
            return roleService.associate(getUser(ctx), role);
        });

    }

    void authenticateServiceRoutes() {
        post("/auth/authenticate", (ctx) -> {
            User user = getUser(ctx);
            String token = authenticationService.authenticate(user);
            ctx.result(token);
            return !token.isEmpty();
        });

        post("/auth/invalidate", (ctx) -> {
            authenticationService.invalidate(getToken(ctx));
            return true;
        });

        post("/auth/checkRole", (ctx) -> {
            boolean hasRole = authenticationService.checkRole(getToken(ctx), getRole(ctx));
            ctx.result(String.valueOf(hasRole));
            return true;
        });

        post("/auth/allRoles", (ctx) -> {
            Set<Role> roles = authenticationService.allRoles(getToken(ctx));
            ArrayList<String> roleStrings = new ArrayList<>();
            for (Role r : roles) {
                roleStrings.add(r.getRoleName());
            }
            ctx.json(roleStrings);
            return true;
        });
    }

    User getUser(Context ctx) {
        return new User(ctx.formParam("username"), "");
    }

    Role getRole(Context ctx) {
        return new Role(ctx.formParam("rolename"), "");
    }

    String getToken(Context ctx) {
        return ctx.formParam("token");
    }

    void post(String path, ExceptionWrapper wrapper) throws HttpResponseException {
        app.post(path, (ctx) -> {
            boolean success ;
            try {
                success = wrapper.handle(ctx);
            } catch (Exception e) {
                throw new BadRequestResponse(e.getMessage());
            }

            if(!success) {
                throw new BadRequestResponse();
            } else {
                ctx.status(200);
            }
        });
    }


    @FunctionalInterface
    interface ExceptionWrapper {
        boolean handle(Context ctx) throws HttpResponseException;
    }

}