package org.example;

import org.example.dao.OrientacaoDAO;
import org.example.model.Orientacao;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Menu de Orientações ---");
            System.out.println("1 - Listar orientações");
            System.out.println("2 - Inserir nova orientação");
            System.out.println("3 - Atualizar orientação");
            System.out.println("4 - Excluir orientação");
            System.out.println("5 - Traduzir orientação");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> OrientacaoDAO.listarOrientacoes();
                case 2 -> {
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Tipo: ");
                    String tipo = scanner.nextLine();

                    System.out.print("Conteúdo (qualquer idioma): ");
                    String conteudo = scanner.nextLine();

                    Orientacao nova = new Orientacao(titulo, tipo, conteudo);

                    // O DAO vai detectar idioma e salvar corretamente
                    OrientacaoDAO.adicionar(nova);

                    System.out.println("Orientação adicionada.");
                }
                case 3 -> {
                    System.out.print("Digite o título da orientação a ser atualizada: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Novo título: ");
                    String novoTitulo = scanner.nextLine();

                    System.out.print("Novo tipo: ");
                    String novoTipo = scanner.nextLine();

                    System.out.print("Novo conteúdo (qualquer idioma): ");
                    String novoConteudo = scanner.nextLine();

                    Orientacao atualizada = new Orientacao(novoTitulo, novoTipo, novoConteudo);

                    boolean ok = OrientacaoDAO.atualizar(titulo, atualizada);
                    System.out.println(ok ? "Orientação atualizada." : "Orientação não encontrada.");
                }
                case 4 -> {
                    System.out.print("Digite o título da orientação a ser excluída: ");
                    String titulo = scanner.nextLine();

                    boolean ok = OrientacaoDAO.deletar(titulo);
                    System.out.println(ok ? "Orientação excluída." : "Orientação não encontrada.");
                }
                case 5 -> {
                    System.out.print("Digite o título da orientação: ");
                    String titulo = scanner.nextLine();

                    Orientacao o = null;
                    for (Orientacao orientacao : OrientacaoDAO.listar()) {
                        if (orientacao.getTitulo().equalsIgnoreCase(titulo)) {
                            o = orientacao;
                            break;
                        }
                    }
                    if (o == null) {
                        System.out.println("Orientação não encontrada.");
                        break;
                    }

                    System.out.println("Escolha o idioma: 1 - PT | 2 - EN | 3 - DE");
                    int idioma = Integer.parseInt(scanner.nextLine());

                    String conteudoTraduzido = o.getConteudo(idioma);
                    if (conteudoTraduzido != null) {
                        System.out.println("Conteúdo traduzido: " + conteudoTraduzido);
                    } else {
                        System.out.println("Tradução não disponível para o idioma escolhido.");
                    }
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
