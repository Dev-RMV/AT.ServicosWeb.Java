package com.br.DevRmv.At.Java.controller;


import static com.br.DevRmv.At.Java.data.MockData.*;

import com.br.DevRmv.At.Java.model.Jogador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("jogador")
public class JogadorController {
    public static List<Jogador> jogadores = colocaDadosIniciais();
    Logger LOGGER = LoggerFactory.getLogger(JogadorController.class);


    @GetMapping("/all")
    public List<Jogador> getJogadores() {
        LOGGER.info("GET->jogadores "+jogadores);
        return jogadores;
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<?> getCpf(@PathVariable String cpf) {
        Jogador jogador = jogadores
                .stream()
                .filter(j -> Integer.toString(j.getCpf()).equals(cpf))
                .findFirst()
                .orElse(null);

        if (jogador == null) {
            LOGGER.info("GET->CPF "+jogador);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF não encontrado");
        } else {
            LOGGER.info("GET->CPF "+jogador);
            return ResponseEntity.ok(jogador);
        }
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> insert(@RequestBody Jogador jogador) {
        if (jogador != null) {
            jogador.setHoraCadastro(LocalDateTime.now());
            jogadores.add(jogador);
            LOGGER.info("POST->INSERIR "+jogador);
            return ResponseEntity.ok(jogador);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível inserir. Favor rever dados");
    }

    @GetMapping("/delete/{cpf}")
    public ResponseEntity<?> delete(@PathVariable String cpf) {
        Jogador jogador = jogadores
                .stream()
                .filter(j -> Integer.toString(j.getCpf()).equals(cpf))
                .findFirst()
                .orElse(null);

        if (jogador == null) {
            LOGGER.info("DELETE->CPF "+jogador);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF não encontrado");
        } else {
            LOGGER.info("DELETE->CPF "+jogador);
            jogadores.remove(jogador);
            return ResponseEntity.ok(jogador);
        }
    }
}
