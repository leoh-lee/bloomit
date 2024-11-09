package com.leoh.bloomit.auth.dto;

import com.leoh.bloomit.domain.member.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpDto {

    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "비밀번호는 8~20자의 영어, 숫자, 특수기호(@$!%*?&) 중 하나 이상을 포함하여야 합니다.")
    private String password;

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    @NotBlank
    @Size(min = 2, max = 16)
    private String nickname;

    @NotNull
    private Gender gender;

    public void changePassword(String password) {
        this.password = password;
    }
}
