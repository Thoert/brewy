package de.brewy;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "brewy")
public class Application implements Callable<Integer> {

    @Option(names = "--port", required = true, description = "application port")
    private Integer port;

    public static void main(String... args) {
        Application application = new Application();

        new CommandLine(application).execute(args);

        JettyServer jettyServer = new JettyServer();
        jettyServer.start(application.port);
    }

    @Override
    public Integer call() {
        return 0;
    }
}
