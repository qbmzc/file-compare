package com.cong.filecompare.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author cong
 * @date 2023/2/1 16:46
 */
@Data
public class CreateInfoResponse {

    /**
     * 标准文档名称
     */
    private String name;


    /**
     * 合并差异项
     */
    @JsonProperty(value = "merge_diff")
    private Integer mergeDiff;
}
