package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.AccountService;
import org.example.user.User;
import org.example.user.UserLogin;
import org.example.user.UserPassword;

import javax.naming.AuthenticationException;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        String content = req.getReader().readLine();

        String login = AuthUtils.getLoginFromBody(content);
        String password = AuthUtils.getPassFromBody(content);

        try {
            accountService.saveUser(User.of(
                    UserLogin.of(login),
                    UserPassword.of(password))
            );
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            resp.setStatus(507);
        }
    }

}
