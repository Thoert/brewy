package de.brewy;


import de.brewy.servlets.MeasurementServlet;
import de.brewy.servlets.UiServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Application {

    public static void main(String... args){
        Server server = new Server();

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(4444);

        server.addConnector(connector);

        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.addServlet(MeasurementServlet.class, "/measurement/*");
        servletContextHandler.addServlet(UiServlet.class, "/ui/*");

        server.setHandler(servletContextHandler);

        try {
            server.start();
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }
}
