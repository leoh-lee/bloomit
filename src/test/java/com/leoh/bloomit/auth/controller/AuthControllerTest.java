package com.leoh.bloomit.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leoh.bloomit.auth.dto.SignUpDto;
import com.leoh.bloomit.auth.service.AuthService;
import com.leoh.bloomit.domain.member.enums.Gender;
import com.leoh.bloomit.security.TestSecurityConfig;
import com.leoh.bloomit.security.WithMockCustomUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@WithMockCustomUser
@Import(TestSecurityConfig.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @Nested
    @DisplayName("회원가입")
    class signUp {
        @Test
        @DisplayName("사용자 아이디가 5자 이하이거나, 20자 이상이면 상태코드 400")
        void usernameWrongSizeThen400() throws Exception {
            // given
            SignUpDto usernameSizeLessThanStandard = new SignUpDto("test", "qwertyui123!", "leoh", "nickname", Gender.MALE);
            String content1 = objectMapper.writeValueAsString(usernameSizeLessThanStandard);

            SignUpDto usernameSizeGreaterThanStandard = new SignUpDto("qwertyuioasdfghjklqwezxcnmasd", "qwertyui123!", "leoh", "nickname", Gender.MALE);
            String content2 = objectMapper.writeValueAsString(usernameSizeGreaterThanStandard);
            // when
            // then
            mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content1)
            ).andExpect(status().isBadRequest());

            mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content2)
            ).andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("사용자 아이디 공백이면 상태코드 400")
        void usernameEmptyThen400() throws Exception {
            // given
            SignUpDto signUpDto = new SignUpDto("", "qwertyui123!", "leoh", "nickname", Gender.MALE);

            String content = objectMapper.writeValueAsString(signUpDto);
            // when
            // then
            mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content)
            ).andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("비밀번호가 8자 이상, 20자 이하이어야 한다.")
        void passwordWrongSizeThen400() throws Exception {
            // given
            SignUpDto passwordSizeLessThanStandard = new SignUpDto("leohlee", "qwe12!", "leoh", "nickname", Gender.MALE);
            SignUpDto passwordSizeGreaterThanStandard = new SignUpDto("leohlee", "qwertyasdfghzxc123!!@#", "leoh", "nickname", Gender.MALE);

            String content1 = objectMapper.writeValueAsString(passwordSizeLessThanStandard);
            // when
            // then
            mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content1)
            ).andExpect(status().isBadRequest());

            String content2 = objectMapper.writeValueAsString(passwordSizeGreaterThanStandard);
            // when
            // then
            mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content2)
            ).andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("비밀번호가 영어, 숫자, 특수문자가 모두 포함되지 않으면 상태코드 400")
        void passwordWrongPatternThen400() throws Exception {
            // given
            SignUpDto passwordNotContainSymbol = new SignUpDto("leohlee", "qwerty123123", "leoh", "nickname", Gender.MALE);
            String content1 = objectMapper.writeValueAsString(passwordNotContainSymbol);

            SignUpDto passwordNotContainAlphabet = new SignUpDto("leohlee", "123123123!", "leoh", "nickname", Gender.MALE);
            String content2 = objectMapper.writeValueAsString(passwordNotContainAlphabet);

            SignUpDto passwordNotContainNumber = new SignUpDto("leohlee", "abcabcqwer!", "leoh", "nickname", Gender.MALE);
            String content3 = objectMapper.writeValueAsString(passwordNotContainNumber);

            // when
            // then
            mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content1)
            ).andExpect(status().isBadRequest());

            mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content2)
            ).andExpect(status().isBadRequest());

            mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content3)
            ).andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("이름이 공백이거나 50자 이상이면 상태코드 400")
        void nameWrongSize400() throws Exception {
            // given
            SignUpDto nameEmpty = new SignUpDto("leohlee", "qwertyui123!", "", "nickname", Gender.MALE);
            String content1 = objectMapper.writeValueAsString(nameEmpty);

            SignUpDto nameSizeGreaterThanMax = new SignUpDto("leohlee", "qwertyui123!", "qwjrqwrowqprjopqwrjopwqfmpowqmfopwqruoqwuroqwrasldmzasdko", "nickname", Gender.MALE);
            String content2 = objectMapper.writeValueAsString(nameSizeGreaterThanMax);
            // when
            // then
            mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content1)
            ).andExpect(status().isBadRequest());

            mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content2)
            ).andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("닉네임이 공백이거나 2자 이하거나 16자 이상이면 상태코드 400")
        void nicknameWrongSize400() throws Exception {
            // given
            SignUpDto nicknameEmpty = new SignUpDto("leohlee", "qwertyui123!", "leoh", "", Gender.MALE);
            String content1 = objectMapper.writeValueAsString(nicknameEmpty);

            SignUpDto nicknameSizeLessThanMin = new SignUpDto("leohlee", "qwertyui123!", "leoh", "a", Gender.MALE);
            String content2 = objectMapper.writeValueAsString(nicknameSizeLessThanMin);

            SignUpDto nicknameSizeGreaterThanMax = new SignUpDto("leohlee", "qwertyui123!", "leoh", "12345678901234567", Gender.MALE);
            String content3 = objectMapper.writeValueAsString(nicknameSizeGreaterThanMax);
            // when
            // then
            mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content1)
            ).andExpect(status().isBadRequest());

            mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content2)
            ).andExpect(status().isBadRequest());

            mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content3)
            ).andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("성별이 Null이면 상태코드 400")
        void genderNull400() throws Exception {
            // given
            SignUpDto nicknameEmpty = new SignUpDto("leohlee", "qwertyui123!", "leoh", "nimoh", null);
            String content = objectMapper.writeValueAsString(nicknameEmpty);

            // when
            // then
            mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content)
            ).andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("유효성 검증 성공")
        void success() throws Exception {
            // given
            SignUpDto nicknameEmpty = new SignUpDto("leohlee", "qwertyui123!", "leoh", "nimoh", Gender.MALE);
            String content = objectMapper.writeValueAsString(nicknameEmpty);

            // when
            // then
            mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content)
            ).andExpect(status().isOk());
        }

    }

}