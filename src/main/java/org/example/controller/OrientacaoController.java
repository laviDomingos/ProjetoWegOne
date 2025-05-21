package org.example.controller;
import org.example.dao.OrientacaoDAO;
import org.example.model.Orientacao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orientacoes")
@CrossOrigin(origins = "*") // libera acesso para o frontend
public class OrientacaoController {

    @GetMapping
    public List<Orientacao> listar() {
        return OrientacaoDAO.listar();
    }

    @PostMapping
    public String adicionar(@RequestBody Orientacao orientacao) {
        OrientacaoDAO.adicionar(orientacao);
        return "Orientação adicionada.";
    }

    @PutMapping("/{titulo}")
    public String atualizar(@PathVariable String titulo, @RequestBody Orientacao nova) {
        boolean ok = OrientacaoDAO.atualizar(titulo, nova);
        return ok ? "Atualizado com sucesso." : "Orientação não encontrada.";
    }

    @DeleteMapping("/{titulo}")
    public String deletar(@PathVariable String titulo) {
        boolean ok = OrientacaoDAO.deletar(titulo);
        return ok ? "Deletado com sucesso." : "Orientação não encontrada.";
    }
}
