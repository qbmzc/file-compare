package com.cong.filecompare.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.websocket.OnClose;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author cong
 * @date 2023/2/1 17:11
 */
@Data
public class CompareResponse {

    private Integer code;

    @JsonProperty(value = "apply_stamp")
    private Integer applyStamp;

    @JsonProperty(value = "compare_id")
    private Long compareId;

    @JsonProperty(value = "create_time")
    private Date createTime;
    @JsonProperty(value = "diff_list")
    private List<Object> diffList;

    @JsonProperty(value = "doc_id2")
    private Long docId2;
}
