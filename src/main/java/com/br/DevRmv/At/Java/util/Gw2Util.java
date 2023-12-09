package com.br.DevRmv.At.Java.util;

import com.br.DevRmv.At.Java.model.ListaDeItensTimeGatedDoGw2;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class Gw2Util {
    public ListaDeItensTimeGatedDoGw2 getApiExterna(){
        HttpRequest request= null;
        Logger LOGGER = LoggerFactory.getLogger(Gw2Util.class);
        try {
            request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI("https://api.guildwars2.com/v2/dailycrafting"))
                    .version(HttpClient.Version.HTTP_2)
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        HttpClient client = HttpClient.newBuilder().build();
        try {
            HttpResponse<String> resposta= client.send(request, HttpResponse.BodyHandlers.ofString());
            LOGGER.info("Status da resposta: "+resposta.statusCode());
            LOGGER.info("Body da resposta: "+resposta.body());
            ObjectMapper mapper= JsonMapper.builder().addModules(new JavaTimeModule()).build();
            ListaDeItensTimeGatedDoGw2 itensTimeGatedDoGw2=new ListaDeItensTimeGatedDoGw2();
                    itensTimeGatedDoGw2.setItensTimeGated(mapper.readValue(resposta.body(),
                            List.class));
            return itensTimeGatedDoGw2;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
/*
 * DOCUMENTAÇÃO DA API
 * https://wiki.guildwars2.com/wiki/API:2/dailycrafting
 *
 * IMPORTANTE: Não usei nenhum dos parâmetros opcionais
 * */