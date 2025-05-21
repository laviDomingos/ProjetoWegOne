package org.example.dao;

import org.example.model.Orientacao;
import java.util.ArrayList;
import java.util.List;

public class OrientacaoDAO {
    private static Orientacao[] orientacoes = new Orientacao[50];
    private static int total = 0;

    // Para aplicação console
    public static void listarOrientacoes() {
        for (int i = 0; i < total; i++) {
            System.out.println("Título: " + orientacoes[i].getTitulo());
        }
    }

    // Para API REST
    public static List<Orientacao> listar() {
        List<Orientacao> lista = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            lista.add(orientacoes[i]);
        }
        return lista;
    }

    public static void adicionar(Orientacao orientacao) {
        if (total >= 50) return;

        String conteudo = orientacao.getConteudoOriginal();
        int idioma = detectarIdioma(conteudo);

        if (orientacao.getConteudo(idioma) == null) {
            orientacao.setConteudo(idioma, conteudo);
        }

        orientacoes[total++] = orientacao;
    }

    public static boolean atualizar(String titulo, Orientacao nova) {
        for (int i = 0; i < total; i++) {
            if (orientacoes[i].getTitulo().equalsIgnoreCase(titulo)) {
                int idioma = detectarIdioma(nova.getConteudoOriginal());
                if (nova.getConteudo(idioma) == null) {
                    nova.setConteudo(idioma, nova.getConteudoOriginal());
                }
                orientacoes[i] = nova;
                return true;
            }
        }
        return false;
    }

    public static boolean deletar(String titulo) {
        for (int i = 0; i < total; i++) {
            if (orientacoes[i].getTitulo().equalsIgnoreCase(titulo)) {
                for (int j = i; j < total - 1; j++) {
                    orientacoes[j] = orientacoes[j + 1];
                }
                orientacoes[--total] = null;
                return true;
            }
        }
        return false;
    }

    // Outros métodos para console (excluir, traduzir etc) podem ser adicionados aqui

    // Detecção simples de idioma
    private static int detectarIdioma(String texto) {
        texto = texto.toLowerCase();
        if (texto.contains("the") || texto.contains("and") || texto.contains("is")) {
            return 2; // Inglês
        } else if (texto.contains("und") || texto.contains("ist") || texto.contains("ein")) {
            return 3; // Alemão
        } else {
            return 1; // Português
        }
    }
}
