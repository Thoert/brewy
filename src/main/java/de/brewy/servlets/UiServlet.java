package de.brewy.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class UiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("text/html; charset=utf-8");
        try {
            resp.getWriter().print(
                    new String(
                            Files.readAllBytes(
                                    Path.of(getClass().getClassLoader().getResource("measurement.html").toURI())
                            )
                    )
            );
        } catch (URISyntaxException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
