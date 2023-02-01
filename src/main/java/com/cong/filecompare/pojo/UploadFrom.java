package com.cong.filecompare.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author cong
 * @date 2023/2/1 16:54
 */
@Data
public class UploadFrom {

    private String from;

    private String filename;

    @JsonProperty(value = "compare_id")
    private Long compareId;

    @JsonProperty(value = "doc_index")
    private Integer docIndex;
    /**
     * 忽略印章
     */
    @JsonProperty(value = "remove_stamp")
    private Integer removeStamp;
    /**
     * 忽略批注
     */
    @JsonProperty(value = "remove_comments")
    private Integer removeComments;
    /**
     * 忽略页眉页脚
     */
    @JsonProperty(value = "remove_headerfooter")
    private Integer removeHeaderfooter;
    /**
     * 忽略脚注
     */
    @JsonProperty(value = "remove_footnote")
    private Integer removeFootnote;
    /**
     * 忽略标点符号
     */
    @JsonProperty(value = "remove_symbol")
    private Integer removeSymbol;
}
