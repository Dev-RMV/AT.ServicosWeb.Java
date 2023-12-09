package com.br.DevRmv.At.Java.controller;

import static com.br.DevRmv.At.Java.data.MockData.*;

import com.br.DevRmv.At.Java.model.Jogador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("jogador")
public class JogadorController {
    public static List<Jogador> jogadores = colocaDadosIniciais();
    Logger LOGGER = LoggerFactory.getLogger(JogadorController.class);


    @GetMapping
    public List<Jogador> getJogadores() {
        LOGGER.info("GET->JOGADORES " + jogadores);
        return jogadores;
    }

    /*
     * BUSCA COM 2 PARÂMETROS OPCIONAIS (cpf, nome) COMO PEDIDO NO AT
     * */
    @GetMapping("/")
    public ResponseEntity<?> search(@RequestParam(required = false) String cpf, @RequestParam(required = false) String nome) {
        List<Jogador> resposta = new ArrayList<>();
        if (cpf != null) {
            Jogador jogador = jogadores
                    .stream()
                    .filter(j -> Integer.toString(j.getCpf()).equals(cpf))
                    .findFirst()
                    .orElse(null);
            if (jogador != null) {
                resposta.add(jogador);
            }
        }
        if (nome != null) {
            jogadores.forEach(jogador -> {
                if (jogador.getNome().contains(nome) && !(resposta.contains(jogador))) {
                    resposta.add(jogador);
                };
            });
        };
        if (resposta.isEmpty()) {
            LOGGER.info("GET->BUSCA " + resposta);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nada foi encontrado");
        } else {
            LOGGER.info("GET->BUSCA " + resposta);
            return ResponseEntity.ok(resposta);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> insert(@RequestBody Jogador jogador) {
        if (jogador != null) {
            jogador.setHoraCadastro(LocalDateTime.now());
            jogadores.add(jogador);
            LOGGER.info("POST->INSERIR " + jogador);
            return ResponseEntity.ok(jogador);
        }
        LOGGER.info("POST->INSERIR " + jogador);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível inserir. Favor rever dados");
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> delete(@PathVariable String cpf) {
        Jogador jogador = jogadores
                .stream()
                .filter(j -> Integer.toString(j.getCpf()).equals(cpf))
                .findFirst()
                .orElse(null);

        if (jogador == null) {
            LOGGER.info("DELETE->CPF " + jogador);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF não encontrado");
        } else {
            LOGGER.info("DELETE->CPF " + jogador);
            jogadores.remove(jogador);
            return ResponseEntity.ok(jogador);
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<?> update(@PathVariable int cpf, @RequestBody Jogador jogador) {
        if (jogador != null) {
            jogador.setCpf(cpf);
            jogadores.forEach(item -> {
                if (item.getCpf() == cpf) {
                    item.setNome(jogador.getNome());
                    item.setJogosNaSteam(jogador.getJogosNaSteam());
                    jogador.setHoraCadastro(item.getHoraCadastro());
                }
            });
            LOGGER.info("POST->UPDATE " + jogador);
            return ResponseEntity.ok(jogador);
        }
        LOGGER.info("POST->UPDATE " + jogador);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível fazer update. Favor rever dados");
    }
}
