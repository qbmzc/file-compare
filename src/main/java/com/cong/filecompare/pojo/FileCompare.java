package com.cong.filecompare.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author cong.zheng
 * @since 2023-02-08 16:51
 * 
 *        The FileCompare Pojo class is used to store data related to file
 *        comparison tasks.
 */
@Data
public class FileCompare {

    /**
     * 标准文档
     */
    private String standardFileId;

    /**
     * 被⽐对⽂档
     */
    private String compareFileId;

    @JsonProperty(value = "preview_url")
    private String previewUrl;

    @JsonProperty(value = "task_id")
    private String taskId;

    @JsonProperty(value = "ocr_result_url")
    private String ocrResultUrl;

    @JsonProperty(value = "similarity_url")
    private String similarityUrl;
}
