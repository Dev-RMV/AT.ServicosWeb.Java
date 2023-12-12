package com.br.DevRmv.At.Java.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jogador {
    private int cpf;
    private String nome;
    private List<String> jogosNaSteam;
    private LocalDateTime horaCadastro;

    public void toString(Jogador j){
        System.out.println("--------------------------------");
        System.out.println("CPF: "+j.getCpf());
        System.out.println("NOME: "+j.getNome());
        System.out.println("JOGOS NA STEAM: "+j.getJogosNaSteam().toString() );
        System.out.println("DATA DE CADASTRO: "+j.getHoraCadastro());
        System.out.println("--------------------------------");
    }
}
