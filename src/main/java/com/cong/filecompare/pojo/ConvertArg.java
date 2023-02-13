package com.cong.filecompare.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author cong
 * @date 2023/2/3 11:14
 */
@Data
public class ConvertArg {

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
