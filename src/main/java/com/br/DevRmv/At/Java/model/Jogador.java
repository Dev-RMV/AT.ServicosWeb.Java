package com.br.DevRmv.At.Java.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Jogador {
    private int cpf;
    private String nome;
    private List<String> jogosNaSteam;
    private LocalDateTime horaCadastro;
}
