package com.br.DevRmv.At.Java.controller;


import static com.br.DevRmv.At.Java.data.MockData.*;

import com.br.DevRmv.At.Java.data.MockData;
import com.br.DevRmv.At.Java.model.Jogador;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("Jogador")
public class JogadorController {
    public static List<Jogador> jogadores = new ArrayList<Jogador>();
    @PostConstruct
    public void colocaDadosIniciais(){
        List<String> list1=new ArrayList<>();
        Jogador j1=new Jogador ();
        j1.setNome("Rodrigo Vianna");
        j1.setCpf(549576);
        list1.add("FFXIV");
        list1.add("Guild Wars 2");
        j1.setJogosNaSteam(list1);
        j1.setHoraCadastro(LocalDateTime.now());
        jogadores.add(j1);

        List<String> list2=new ArrayList<>();
        Jogador j2=new Jogador ();
        j2.setNome("Fulano da Silva");
        j2.setCpf(453345);
        list2.add("Elden Ring");
        list2.add("Cyberpunk 2077");
        j2.setJogosNaSteam(list2);
        j2.setHoraCadastro(LocalDateTime.now());
        jogadores.add(j2);
    };



    @GetMapping("/")
    public String getJogador(){
return jogadores.toString();
    }
}
