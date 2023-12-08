package com.br.DevRmv.At.Java.data;

import com.br.DevRmv.At.Java.controller.JogadorController;
import com.br.DevRmv.At.Java.model.Jogador;

import java.util.ArrayList;
import java.util.List;

public class MockData {
    public void colocaDadosIniciais(){
        List<String> list=new ArrayList<>();

        Jogador j1=new Jogador ();
        j1.setNome("Rodrigo Vianna");
        j1.setCpf(549576);
        list.add("FFXIV");
        list.add("Guild Wars 2");
        j1.setJogosNaSteam(list);
        JogadorController.jogadores.add(j1);
        list.clear();

        Jogador j2=new Jogador ();
        j2.setNome("Fulano da Silva");
        j2.setCpf(453345);
        list.add("Elden Ring");
        list.add("Cyberpunk 2077");
        j2.setJogosNaSteam(list);
        JogadorController.jogadores.add(j2);
        list.clear();

    };
}
