package ru.stqa.homework08.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.stqa.homework08.app.Application;

public class TestBase {
    protected Application app;

    @BeforeEach
    void beforeEach() {
        app = new Application();
    }

    @AfterEach
    void afterEach() {
        app.quit();
    }
}
