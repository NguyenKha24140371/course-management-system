package com.cms.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Tạo constructor rỗng nếu cần thiết cho các thư viện mapping JSON
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    // Constructor custom chỉ nhận vào 1 tham số
    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}