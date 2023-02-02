package com.cong.filecompare.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author cong
 * @date 2023/2/1 17:14
 */
@Data
public class CompareResult {

    @JsonProperty(value = "doc_id1")
    private String docId1;
    @JsonProperty(value = "doc_id2")
    private Long docId2;

    @JsonProperty(value = "standard_name")
    private String standardName;
    @JsonProperty(value = "compare_name")
    private String compareName;


    @JsonProperty(value = "task_id")
    private String taskId;
}
