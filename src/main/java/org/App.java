package org.example;

import Interfaces.User;
import components.AppRouter;
import server.MessageTranslator;
import server.ServerChatConnection;

import java.io.IOException;

public class App {

    private final String HOST = "127.0.0.1";
    private final int PORT = 4000;
    public App() throws IOException {
        System.out.println(" -- Client Console -- ");
        try {
            AppRouter app = new AppRouter();
            Thread appThread = new Thread(app);
            appThread.start();

            ServerChatConnection serverChatConnection = new ServerChatConnection();

            serverChatConnection.setUserDataCallback(userData -> {
                String[] userDataDivided = userData.split(";");

                app.user.name = userDataDivided[1];
                app.user.email = userDataDivided[2];
                app.user.password = userDataDivided[3];
                app.user.phoneNumber = userDataDivided[4];
                app.user.language = userDataDivided[5];

                MessageTranslator translator = new MessageTranslator(app.user.language);
                serverChatConnection.translator = translator;
            });

            app.server = serverChatConnection;

            serverChatConnection.start(this.HOST, this.PORT, app.request);
        } catch (IOException error) {
            System.out.println("Error on Connecting to Server: " + error.getMessage());
        }
        System.out.println(" -- Client Console Stopped -- ");
    }
}