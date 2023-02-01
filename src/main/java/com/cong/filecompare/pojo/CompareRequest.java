package com.cong.filecompare.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author cong
 * @date 2023/2/1 17:11
 */
@Data
public class CompareRequest {

    @JsonProperty(value = "compare_id")
    private String compareId;

    @JsonProperty(value = "doc_id1")
    private String docId1;

    @JsonProperty(value = "doc_id2")
    private Long docId2;
}
