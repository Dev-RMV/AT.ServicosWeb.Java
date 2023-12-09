package com.br.DevRmv.At.Java.data;

import com.br.DevRmv.At.Java.controller.JogadorController;
import com.br.DevRmv.At.Java.model.Jogador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockData {
    public static List<Jogador> colocaDadosIniciais(){
        List<Jogador> jogadores=new ArrayList<>();
        List<String> list1=new ArrayList<>();
        Jogador j1=new Jogador ();
        j1.setNome("Rodrigo Vianna");
        j1.setCpf(1);
        list1.add("FFXIV");
        list1.add("Guild Wars 2");
        j1.setJogosNaSteam(list1);
        j1.setHoraCadastro(LocalDateTime.now());
        jogadores.add(j1);

        List<String> list2=new ArrayList<>();
        Jogador j2=new Jogador ();
        j2.setNome("Fulano da Silva");
        j2.setCpf(2);
        list2.add("Elden Ring");
        list2.add("Cyberpunk 2077");
        j2.setJogosNaSteam(list2);
        j2.setHoraCadastro(LocalDateTime.now());
        jogadores.add(j2);

        return jogadores;
    };
}
