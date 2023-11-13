package org;

import java.sql.SQLException;

import pages.AppRouter;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Crie uma instância de AppRouter sem especificar um idioma
        new AppRouter();
    }
}