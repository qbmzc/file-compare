package com.cong.filecompare.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author cong
 * @date 2023/2/1 17:32
 */
@Configuration
public class WebClientConfig {

    private final static String BASE_URL="http://192.168.104.122:4850/";
    @Bean
    public WebClient createClient(){
       return WebClient.builder()
               //3d3bafb2-a2e2-11ed-b76e-0242ac110002
               .defaultHeader(ConstantEnum.X_TOKEN.value,"3d3bafb2-a2e2-11ed-b76e-0242ac110002")
               .baseUrl(BASE_URL)
               .build();
    }
}
