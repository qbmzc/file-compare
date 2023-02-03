package com.cong.filecompare.controller;

import com.cong.filecompare.common.MapperEnum;
import com.cong.filecompare.pojo.*;
import com.cong.filecompare.service.FileCompareService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
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
    public CreateInfoResponse createInfo(@RequestBody CreateInfoRequest createInfoRequest) throws JsonProcessingException {
        System.out.println(MapperEnum.INSTANCE.getObjectMapper().writeValueAsString(createInfoRequest));
        String uri="/doc_compare/create_info";
        Mono<String> responseFlux = webClient.post().uri(uri).
                contentType(MediaType.APPLICATION_JSON)
                .bodyValue(createInfoRequest)
                .retrieve().bodyToMono(String.class);

        String createInfoResponse = responseFlux.block();
        assert createInfoResponse != null;
        log.info(createInfoResponse);
        CreateInfoResponse response = MapperEnum.INSTANCE.getObjectMapper().readValue(createInfoResponse, CreateInfoResponse.class);
        return response;
    }


    @SneakyThrows
    @GetMapping("getToken")
    public String getToken(){
        String url="/doc_compare/user/login";
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


    /**
     * 文件上传
     * @param uploadFrom
     */
    @SneakyThrows
    @PostMapping("upload")
    public void uploadFile(UploadFrom uploadFrom,File file){
        String url="/file/upload?from=doc_compare&filename={filename}&compare_id={compareId}&doc_index={docIndex}&remove_stamp=1&remove_comments=0&remove_headerfooter=0&remove_footnote=0&remove_symbol=1";
        log.info("uploadFile {}",file.getName());
        Resource resource = new FileSystemResource(file);
        uploadFrom.setFilename(file.getName());
        Mono<String> stringMono = webClient.post()
                .uri(url,uploadFrom.getFilename(),uploadFrom.getCompareId(),uploadFrom.getDocIndex())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromMultipartData("file",resource))
                .retrieve().bodyToMono(String.class);
        stringMono.block();
    }

    /**
     * 更新任务
     * @param updateTask
     * @return
     */
    @PostMapping("updateTask")
    public Result updateTask(@RequestBody UpdateTask updateTask){
        String url="/doc_compare/update_task";
        Mono<Result> resultMono = webClient.post().uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updateTask)
                .retrieve().bodyToMono(Result.class);
        return resultMono.block();
    }


    @SneakyThrows
    @GetMapping("compare")
    public Result compareFile(){
        String doc1="/Users/cong/Downloads/source.pdf";
        String doc2="/Users/cong/Downloads/target.pdf";
        CreateInfoRequest createInfoRequest = new CreateInfoRequest();
        createInfoRequest.setName("source.pdf");
        createInfoRequest.setMergeDiff(1);
        CreateInfoResponse createInfoResponse = this.createInfo(createInfoRequest);
        log.info("createInfoResponse {}",createInfoResponse.toString());
        UploadFrom uploadFrom = new UploadFrom();
        uploadFrom.setCompareId(createInfoResponse.getCompareId());
        uploadFrom.setDocIndex(1);
        this.uploadFile(uploadFrom,new File(doc1));

        UploadFrom uploadFrom2 = new UploadFrom();
        uploadFrom2.setCompareId(createInfoResponse.getCompareId());
        uploadFrom2.setDocIndex(2);
        this.uploadFile(uploadFrom2,new File(doc2));

        //更新任务
        UpdateTask updateTask = new UpdateTask();
        updateTask.setCompareId(createInfoResponse.getTaskId());
        Result result = this.updateTask(updateTask);
        log.info("upodate Task {}",result);
        return result;
    }

    @SneakyThrows
    @GetMapping("new")
    public void newCompare(){
        String url="/doc_compare/create";
        String doc1="/Users/cong/Downloads/source.pdf";
        String doc2="/Users/cong/Downloads/target.pdf";

        CompareDto compareDto = new CompareDto();
        ConvertArg convertArg = new ConvertArg();
        convertArg.setRemoveStamp(1);
        convertArg.setRemoveComments(0);
        convertArg.setRemoveFootnote(0);
        convertArg.setRemoveHeaderfooter(0);
        convertArg.setRemoveSymbol(1);
        compareDto.setConvertArg(convertArg);

        Document standard = new Document();
        byte[] standardBytes = Files.readAllBytes(Path.of(doc1));
        String standDoc = Base64.getEncoder().encodeToString(standardBytes);
        standard.setFiledata(standDoc);
        standard.setFilename("source.pdf");

        Document compare = new Document();
        byte[] cBytes = Files.readAllBytes(Path.of(doc2));
        String cDoc = Base64.getEncoder().encodeToString(cBytes);
        standard.setFiledata(cDoc);
        standard.setFilename("target.pdf");

        ArrayList<Document> standardDoc = new ArrayList<>(1);
        standardDoc.add(standard);
        ArrayList<Document> compareDoc = new ArrayList<>();
        compareDoc.add(compare);
        compareDto.setStandardDoc(standardDoc);
        compareDto.setCompareDoc(compareDoc);

        Mono<String> mono = webClient.post().uri(url).contentType(MediaType.APPLICATION_JSON)
                .bodyValue(compareDto).retrieve().bodyToMono(String.class);
        System.out.println(mono.block());

    }

}
