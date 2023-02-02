package com.cong.filecompare.pojo;

import lombok.Data;

/**
 * @author cong
 * @date 2023/2/2 13:37
 */
@Data
public class LoginResponse {

    private LoginData data;

    private String token;
}
