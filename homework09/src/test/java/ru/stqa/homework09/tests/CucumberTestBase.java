package ru.stqa.homework09.tests;

import io.cucumber.java8.En;
import ru.stqa.homework09.app.Application;

public class CucumberTestBase implements En {
    protected static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    protected Application app;

    public CucumberTestBase() {
        Before(() -> {
            if (tlApp.get() != null) {
                app = tlApp.get();
                return;
            }

            app = new Application();
            tlApp.set(app);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {app.quit();app = null;}));
        });
    }
}
