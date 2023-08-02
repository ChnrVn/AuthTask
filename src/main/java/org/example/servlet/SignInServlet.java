package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.AccountService;

import javax.naming.AuthenticationException;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        String content = req.getReader().readLine();

        String curLogin = AuthUtils.getLoginFromBody(content);
        String curPassword = AuthUtils.getPassFromBody(content);

       // System.out.println("signIn: " + curLogin + " " + curPassword);

        try {
            if(accountService.checkAuth(curLogin, curPassword)){
                resp.setStatus(200);
                resp.getWriter()
                        .println("Authorized: " + curLogin);
            } else{
                resp.setStatus(401);
                resp.getWriter()
                        .println("Unauthorized");
            }
        } catch (AuthenticationException e) {
            resp.setStatus(507);
        }
    }
}
