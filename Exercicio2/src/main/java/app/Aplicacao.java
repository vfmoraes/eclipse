package app;

import java.util.List;
import java.util.Scanner;
import dao.JogoDAO;
import model.Jogo;

public class Aplicacao {
    
    private static Scanner scanner = new Scanner(System.in);
    private static JogoDAO jogoDAO = new JogoDAO();
    
    public static void main(String[] args) throws Exception {
        
        int escolha;
        do {
            exibirMenu();
            escolha = lerOpcao();
            
            switch (escolha) {
                case 1:
                    inserirJogo();
                    break;
                case 2:
                    listarJogos();
                    break;
                case 3:
                    atualizarJogo();
                    break;
                case 4:
                    excluirJogo();
                    break;
                case 5:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
            
        } while (escolha != 5);
        
        scanner.close();
    }
    
    private static void exibirMenu() {
        System.out.println("==== Menu ====");
        System.out.println("1) Inserir");
        System.out.println("2) Listar");
        System.out.println("3) Atualizar");
        System.out.println("4) Excluir");
        System.out.println("5) Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private static int lerOpcao() {
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }
    
    private static void inserirJogo() {
    	System.out.println("\n==== Inserir jogo === ");
        int codigo;
        String nome, genero;
        int ano;
       
        do {
            System.out.println("\n==== Insira o código ===");
            while (!scanner.hasNextInt()) {
                System.out.println("Código inválido. Insira um número válido: ");
                scanner.next();
            }
            codigo = scanner.nextInt();
        } while (codigo <= 0);

        scanner.nextLine();
        
        do {
            System.out.println("\n==== Insira o nome ===");
            nome = scanner.nextLine();
        } while (nome.isEmpty());

        do {
            System.out.println("\n==== Insira o genero ===");
            genero = scanner.nextLine();
        } while (genero.isEmpty());

        do {
            System.out.println("\n==== Insira o ano de lançamento ===");
            while (!scanner.hasNextInt()) {
                System.out.println("Ano inválido. Insira um ano válido: ");
                scanner.next();
            }
            ano = scanner.nextInt();
        } while (ano <= 0);

        scanner.nextLine();

        Jogo usuario = new Jogo(codigo, nome, genero, ano);
        if (jogoDAO.insert(usuario)) {
            System.out.println("Inserção com sucesso -> " + usuario.toString());
        } else {
            System.out.println("Erro ao inserir jogo.");
        }
    }
    
    private static void listarJogos() {
        System.out.println("\n==== Usuários === ");
        List<Jogo> usuarios = jogoDAO.getOrderByCodigo();
        for (Jogo u : usuarios) {
            System.out.println(u.toString());
        }
    }
    
    private static void atualizarJogo() {
        System.out.println("\n==== Atualizar Usuário === ");
        int codigo;
        String nome, genero;
        int ano;
        System.out.println("\n==== Insira o código do jogo ===");
        codigo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n==== Insira o novo nome do jogo ===");
        nome = scanner.nextLine();
        System.out.println("\n==== Insira a novo genero do jogo ===");
        genero = scanner.nextLine();
        System.out.println("\n==== Modifique o ano do jogo ===");
        ano = scanner.nextInt();
        scanner.nextLine();
        Jogo usuario = new Jogo(codigo, nome, genero, ano);
        jogoDAO.update(usuario);
    }
    
    private static void excluirJogo() {
        System.out.println("\n==== Excluir jogo === ");
        System.out.println("\n==== Insira o código do jogo para ser excluído ===");
        int codigo = scanner.nextInt();
        jogoDAO.delete(codigo);
    }
}
