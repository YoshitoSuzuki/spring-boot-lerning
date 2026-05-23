package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // Webサーバーを擬似的に立ち上げて通信をシミュレートする魔法のツール
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // テストメソッドには @Test をつけます。メソッド名は日本語でもOKです（実務でもよくやります）
    @Test
    void 正しいIDとパスワードなら200OKとJWTが返ってくる() throws Exception {
        String requestBody = "{\"username\":\"yoshito\", \"password\":\"pass123\"}";

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk()) // HTTPステータスが200であることを期待
                .andExpect(jsonPath("$.token").exists()); // 返ってきたJSONに"token"があることを期待
    }

    @Test
    void 間違ったパスワードなら401エラーになる() throws Exception {
        String requestBody = "{\"username\":\"yoshito\", \"password\":\"wrong_pass\"}";

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isUnauthorized()); // HTTPステータスが401（未認証）であることを期待
    }
}
