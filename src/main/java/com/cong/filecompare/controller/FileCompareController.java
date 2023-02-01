package com.cong.filecompare.controller;

import com.cong.filecompare.common.MapperEnum;
import com.cong.filecompare.pojo.CreateInfoRequest;
import com.cong.filecompare.pojo.CreateInfoResponse;
import com.cong.filecompare.service.FileCompareService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author cong
 * @date 2023/2/1 17:26
 */
@RestController
@RequestMapping("file/compare")
public class FileCompareController {

    @Autowired
    private WebClient webClient;
    @Autowired
    private FileCompareService service;

    @PostMapping("createInfo")
    public void createInfo(@RequestBody CreateInfoRequest createInfoRequest) throws JsonProcessingException {
        Mono<CreateInfoRequest> requestMono = Mono.just(createInfoRequest);
        String uri="";
        Mono<CreateInfoResponse> responseFlux = webClient.post().uri(uri).body(requestMono, CreateInfoRequest.class)
                .retrieve().bodyToMono(CreateInfoResponse.class);

        CreateInfoResponse response = responseFlux.block();
        System.out.println(MapperEnum.INSTANCE.getObjectMapper().writeValueAsString(response));

    }


}
