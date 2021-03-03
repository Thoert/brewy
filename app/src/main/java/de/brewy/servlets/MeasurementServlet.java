package de.brewy.servlets;

import de.brewy.domain.Measurement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MeasurementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getWriter().print(new StringBuilder()
                .append("{")
                .append("\"sensor1\":")
                .append(Measurement.getInstance().getTemperatureSensorOne())
                .append(",")
                .append("\"sensor2\":")
                .append(Measurement.getInstance().getTemperatureSensorTwo())
                .append("}")
        );

        Measurement.getInstance().setTemperatureSensorOne(
                String.valueOf(Integer.parseInt(Measurement.getInstance().getTemperatureSensorOne()) + 1)
        );

        Measurement.getInstance().setTemperatureSensorTwo(
                String.valueOf(Integer.parseInt(Measurement.getInstance().getTemperatureSensorTwo()) + 2)
        );
    }

}
