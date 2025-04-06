package com.unicesumar;

import com.unicesumar.entities.User;
import com.unicesumar.repository.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:meubanco.db")) {
            createUsersTableIfNotExists(connection);

            UserRepository userRepo = new UserRepository(connection);
            Scanner scanner = new Scanner(System.in);

            int opcao;
            do {
                System.out.println("\n=== Menu de Usuários ===");
                System.out.println("1. Cadastrar usuário");
                System.out.println("2. Listar usuários");
                System.out.println("3. Buscar usuário por UUID");
                System.out.println("4. Deletar usuário por UUID");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Digite um número válido.");
                    scanner.next();
                    System.out.print("Escolha uma opção: ");
                }
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome: ");
                        String name = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Senha: ");
                        String password = scanner.nextLine();

                        User newUser = new User(UUID.randomUUID(), name, email, password);
                        userRepo.save(newUser);
                        System.out.println("✅ Usuário salvo com sucesso!");
                    }
                    case 2 -> {
                        List<User> users = userRepo.findAll();
                        if (users.isEmpty()) {
                            System.out.println("⚠️ Nenhum usuário cadastrado.");
                        } else {
                            System.out.println("📋 Lista de usuários:");
                            for (User u : users) {
                                System.out.println(u.getUuid() + " - " + u.getName() + " - " + u.getEmail());
                            }
                        }
                    }
                    case 3 -> {
                        System.out.print("Digite o UUID do usuário: ");
                        String uuidStr = scanner.nextLine();
                        try {
                            UUID uuid = UUID.fromString(uuidStr);
                            userRepo.findById(uuid)
                                    .ifPresentOrElse(
                                            u -> System.out.println("Usuário: " + u.getName() + " - " + u.getEmail()),
                                            () -> System.out.println("Usuário não encontrado.")
                                    );
                        } catch (IllegalArgumentException e) {
                            System.out.println("UUID inválido.");
                        }
                    }
                    case 4 -> {
                        System.out.print("Digite o UUID do usuário a ser deletado: ");
                        String deleteUuidStr = scanner.nextLine();
                        try {
                            UUID deleteUuid = UUID.fromString(deleteUuidStr);
                            userRepo.deleteById(deleteUuid);
                            System.out.println("✅ Usuário deletado (se existia).");
                        } catch (IllegalArgumentException e) {
                            System.out.println("UUID inválido.");
                        }
                    }
                    case 0 -> System.out.println("👋 Encerrando aplicação...");
                    default -> System.out.println("❌ Opção inválida.");
                }

            } while (opcao != 0);

        } catch (Exception e) {
            System.err.println("Erro na aplicação: " + e.getMessage());
        }
    }

    private static void createUsersTableIfNotExists(Connection connection) {
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                    uuid TEXT PRIMARY KEY,
                    name TEXT NOT NULL,
                    email TEXT NOT NULL,
                    password TEXT NOT NULL
                )
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.execute();
        } catch (Exception e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }
}