package com.cong.filecompare.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author cong
 * @date 2023/2/3 11:13
 */
@Data
public class CompareDto {
    @JsonProperty("convert_arg")
    private  ConvertArg convertArg;


    private Config config;

    @JsonProperty(value = "standard_doc")
    private List<Document> standardDoc;

    @JsonProperty(value = "compare_doc")
    private List<Document> compareDoc;
}
