package org.example.model;

public class Orientacao {
    private String titulo;
    private String tipo;
    private String conteudoOriginal; // Conteúdo enviado inicialmente
    private String conteudoPT;
    private String conteudoEN;
    private String conteudoDE;

    public Orientacao() {}

    public Orientacao(String titulo, String tipo, String conteudoOriginal) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.conteudoOriginal = conteudoOriginal;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConteudoOriginal() {
        return conteudoOriginal;
    }

    public void setConteudoOriginal(String conteudoOriginal) {
        this.conteudoOriginal = conteudoOriginal;
    }

    public String getConteudo(int idioma) {
        return switch (idioma) {
            case 1 -> conteudoPT;
            case 2 -> conteudoEN;
            case 3 -> conteudoDE;
            default -> null;
        };
    }

    public void setConteudo(int idioma, String conteudo) {
        switch (idioma) {
            case 1 -> this.conteudoPT = conteudo;
            case 2 -> this.conteudoEN = conteudo;
            case 3 -> this.conteudoDE = conteudo;
        }
    }
}
