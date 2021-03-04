package de.brewy;

import de.brewy.servlets.MeasurementServlet;
import de.brewy.servlets.UiServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.resource.Resource;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class JettyServer {

    private static final String MEASUREMENT_PATH = "/measurement/*";
    private static final String UI_PATH = "/ui/*";

    private Server server;

    public JettyServer() {
        server = new Server();
    }

    public void start(int port) {
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);

        server.addConnector(connector);

        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(MeasurementServlet.class, MEASUREMENT_PATH);
        servletHandler.addServletWithMapping(UiServlet.class, UI_PATH);

        // Create and configure a ResourceHandler.
        ResourceHandler handler = new ResourceHandler();
// Configure the directory where static resources are located.

        try {
            URI resourceURI = getClass()
                    .getClassLoader()
                    .getResource("measurement.html")
                    .toURI()
                    .resolve("./")
                    .normalize();
            ;
            handler.setBaseResource(Resource.newResource(resourceURI));
        } catch (URISyntaxException | MalformedURLException exception) {
            System.err.println(exception.getMessage());
        }
// Configure directory listing.
        handler.setDirectoriesListed(true);
// Configure whether to accept range requests.
        handler.setAcceptRanges(true);

// Link the context to the server.
        ContextHandler contextHandler = new ContextHandler("/static");
        contextHandler.setHandler(handler);

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{contextHandler, servletHandler});

        server.setHandler(handlerList);

        try {
            server.start();
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }

}
