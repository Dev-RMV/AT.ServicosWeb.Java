package com.br.DevRmv.At.Java;
import com.br.DevRmv.At.Java.controller.JogadorController;
import com.br.DevRmv.At.Java.model.Jogador;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JogadorControllerTests {
    @Test
    public void testeGetApiExterna() {
        JogadorController jogadorController = new JogadorController();
        String resultado = jogadorController.getApiExterna();
        assertEquals("LEIA OS LOGS DO CONSOLE", resultado);
    }

    @Test
    public void testeSearch() {
        JogadorController jogadorController = new JogadorController();
        ResponseEntity<?> resultado = jogadorController.search("12345678954435534", "Nome");
        assertTrue(resultado.getStatusCode() == HttpStatus.OK || resultado.getStatusCode() == HttpStatus.NOT_FOUND);
    }

    @Test
    public void testeInsert() {
        JogadorController jogadorController = new JogadorController();
        Jogador jogador = new Jogador();
        List<String> list1= new ArrayList<>();
        jogador.setNome("Rodrigo Vianna");
        jogador.setCpf(1543);
        list1.add("FFXIV");
        list1.add("Guild Wars 2");
        jogador.setJogosNaSteam(list1);
        jogador.setHoraCadastro(LocalDateTime.now());
        ResponseEntity<?> resultado = jogadorController.insert(jogador);
        assertTrue(resultado.getStatusCode() == HttpStatus.CREATED || resultado.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testeDelete() {
        JogadorController jogadorController = new JogadorController();
        ResponseEntity<?> resultado = jogadorController.delete("123456796");
        assertTrue(resultado.getStatusCode() == HttpStatus.OK || resultado.getStatusCode() == HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Falha no update")
    public void testeUpdate() {
        JogadorController jogadorController = new JogadorController();
        ResponseEntity<?> resultado = jogadorController.update(1, null);
        assertFalse(resultado.getStatusCode() == HttpStatus.OK );
    }
    @Test
    @DisplayName("Teste exception")
    public void testeDeleteException() {
        JogadorController jogadorController = new JogadorController();
        assertThrows(Exception.class, () -> {
            jogadorController.delete("aaaaaaa");
        });
    }

}
