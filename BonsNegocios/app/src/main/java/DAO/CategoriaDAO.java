package DAO;


import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.Categoria;
import Util.Conexao;

public class CategoriaDAO {

    public List<Categoria> Seleciona(){
        try {
            String url = Conexao.url.concat("Categoria");
            RestTemplate rest = new RestTemplate();
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            messageConverters.add(new MappingJackson2HttpMessageConverter());
            // Add the message converters to the restTemplate
            rest.setMessageConverters(messageConverters);
            rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Categoria[] array = rest.getForObject(url, Categoria[].class); //(url,Produto.class,));
            return Arrays.asList(array);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
