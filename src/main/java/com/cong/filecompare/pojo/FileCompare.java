package com.cong.filecompare.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author cong
 * @date 2023/2/13 15:16
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
    /**
     * 预览地址
     */
    @JsonProperty(value = "preview_url")
    private String previewUrl;

    @JsonProperty(value = "task_id")
    private String taskId;

    @JsonProperty(value = "ocr_result_url")
    private String ocrResultUrl;

    @JsonProperty(value = "similarity_url")
    private String similarityUrl;
}
