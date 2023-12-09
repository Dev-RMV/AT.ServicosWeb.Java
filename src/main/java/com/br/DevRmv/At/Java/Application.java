package com.br.DevRmv.At.Java;
import static com.br.DevRmv.At.Java.controller.JogadorController.*;
import com.br.DevRmv.At.Java.controller.JogadorController;
import com.br.DevRmv.At.Java.model.Jogador;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		Scanner sc=new Scanner(System.in);

		int opcao=-1;
		while(opcao!=1){
			System.out.println("\n\n\n\n**Bem vindo ao AT da disciplina Desenvolvimento de Serviços Web e Testes com Java**");
			System.out.println("\n1-SAIR");
			System.out.println("2-Listar todos os jogadores");
			System.out.println("3-Buscar jogador por CPF");
			System.out.println("4-Inserir jogador");
			System.out.print("Opcao: ");
			opcao=sc.nextInt();
			sc.nextLine();

			switch (opcao){
				case 1:
					System.out.println("\nVoce saiu!"); break;
				case 2:
					

			}
		}


	}


}
