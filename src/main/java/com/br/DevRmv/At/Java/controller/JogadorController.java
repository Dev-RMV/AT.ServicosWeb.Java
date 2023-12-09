package com.br.DevRmv.At.Java.controller;

import static com.br.DevRmv.At.Java.data.MockData.*;

import com.br.DevRmv.At.Java.util.Gw2Util;
import com.br.DevRmv.At.Java.model.Jogador;
import com.br.DevRmv.At.Java.model.ListaDeItensTimeGatedDoGw2;
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


    @GetMapping("/api")
    public String getApiExterna() {
        ListaDeItensTimeGatedDoGw2 listaDeItensTimeGatedDoGw2;
        Gw2Util util=new Gw2Util();
        listaDeItensTimeGatedDoGw2= util.getApiExterna();
        //loop para mostrar que o objeto foi populado com os dados
        listaDeItensTimeGatedDoGw2.getItensTimeGated().forEach((item->{
            LOGGER.info("Item Time Gated no objeto Java: "+item);
        }));
        return "LEIA OS LOGS DO CONSOLE";
    };

    /*
     * BUSCA COM 2 PARÂMETROS OPCIONAIS (cpf, nome) COMO PEDIDO NO AT
     * Somente a "/" retorna tudo o que está na lista (jogadores)
     * */
    @GetMapping("/")
    public ResponseEntity<?> search(@RequestParam(required = false) String cpf, @RequestParam(required = false) String nome) {
        if(cpf==null&&nome==null){
            LOGGER.info("GET BUSCA VAZIA->JOGADORES " + jogadores);
            return ResponseEntity.ok( jogadores);
        }
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
            if (jogador.getHoraCadastro()==null){
                LOGGER.info("POST->UPDATE falho " + jogador);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF não encontrado");
            }
            LOGGER.info("POST->UPDATE " + jogador);

            return ResponseEntity.ok(jogador);
        }
        LOGGER.info("POST->UPDATE " + jogador);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível fazer update. Favor rever dados");
    }
}
