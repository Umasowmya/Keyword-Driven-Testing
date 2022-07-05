package com.qa.hs.tests;

import com.qa.hs.keyword.engine.KeyWordEngine;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    public KeyWordEngine keyWordEngine;

    @Test
    public void loginTest() throws IOException, InterruptedException {

        keyWordEngine = new KeyWordEngine();
        keyWordEngine.startExecution("login");
    }
}
