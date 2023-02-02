package com.cong.filecompare.controller;

import com.cong.filecompare.common.MapperEnum;
import com.cong.filecompare.pojo.CreateInfoRequest;
import com.cong.filecompare.pojo.CreateInfoResponse;
import com.cong.filecompare.pojo.LoginResponse;
import com.cong.filecompare.pojo.Result;
import com.cong.filecompare.service.FileCompareService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cong
 * @date 2023/2/1 17:26
 */
@Slf4j
@RestController
@RequestMapping("file/compare")
public class FileCompareController {

    @Autowired
    private WebClient webClient;
    @Autowired
    private FileCompareService service;

    @PostMapping("createInfo")
    public void createInfo(@RequestBody CreateInfoRequest createInfoRequest) throws JsonProcessingException {
        System.out.println(MapperEnum.INSTANCE.getObjectMapper().writeValueAsString(createInfoRequest));
        String uri="create_info";
        Mono<CreateInfoResponse> responseFlux = webClient.post().uri(uri).
                contentType(MediaType.APPLICATION_JSON)
                .bodyValue(createInfoRequest)
                .retrieve().bodyToMono(CreateInfoResponse.class);

        CreateInfoResponse response = responseFlux.block();
        System.out.println(MapperEnum.INSTANCE.getObjectMapper().writeValueAsString(response));

    }


    @SneakyThrows
    @GetMapping("getToken")
    public String getToken(){
        String url="/user/login";
        Map<String, String> map = new HashMap<>();
        map.put("account","admin");
        map.put("password","e71cd8c35cf4f57fe146a3e4f46721d7");
        Mono<Result> bodyToMono = webClient.post().uri(url).contentType(MediaType.APPLICATION_JSON)
                .bodyValue(map).retrieve().bodyToMono(Result.class);
        Result result = bodyToMono.block();
        if (null!=result && result.getCode()==200){
            LoginResponse loginResponse = MapperEnum.INSTANCE.getObjectMapper().convertValue(result.getResult(), LoginResponse.class);
            log.info("token:{}",loginResponse.getToken());
            return loginResponse.getToken();
        }
        return "";
    }
}
