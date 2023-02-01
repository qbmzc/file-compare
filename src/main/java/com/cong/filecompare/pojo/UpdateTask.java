package com.cong.filecompare.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author cong
 * @date 2023/2/1 17:07
 */
@Data
public class UpdateTask {

    @JsonProperty(value = "compare_id")
    private String compareId;
}
