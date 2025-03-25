package src.main.java.com.unicesumar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Parâmetros de conexão
        String url = "jdbc:postgresql://localhost:5432/unicesumar_store";
        String usuario = "postgres";
        String senha = "password";

        // Tentativa de conexão
        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            if (conn != null) {
                System.out.println("Conectado com sucesso ao PostgreSQL!");
            } else {
                System.out.println("Falha na conexão.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n---MENU---");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Buscar Produto");
            System.out.println("3 - Sair");
            System.out.println("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Cadastrar Produto");
                    break;
                case 2:
                    System.out.println("Buscar Produto");
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente");;
            }

        } while (option != 3);

        scanner.close();
    }
}
