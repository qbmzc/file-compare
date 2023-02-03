package com.cong.filecompare.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author cong
 * @date 2023/2/3 11:15
 */
@Data
public class Config {

    private String engine;

    @JsonProperty(value = "use_pdf_parser")
    private String usePdfParser;
}
