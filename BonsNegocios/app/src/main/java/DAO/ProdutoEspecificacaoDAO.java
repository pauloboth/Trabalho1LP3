package DAO;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.ProdutoEspecificacao;
import Util.Conexao;

public class ProdutoEspecificacaoDAO {

    public List<ProdutoEspecificacao> Seleciona(int pro_id){
        try {
            String url = Conexao.url.concat("Produto");
            RestTemplate rest = new RestTemplate();
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            messageConverters.add(new MappingJackson2HttpMessageConverter());
            // Add the message converters to the restTemplate
            rest.setMessageConverters(messageConverters);
            rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ProdutoEspecificacao[] arrayProduto = rest.getForObject(url, ProdutoEspecificacao[].class); //(url,Produto.class,));
            return Arrays.asList(arrayProduto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
