package com.example.wanted.member.web.object;

import com.example.wanted.common.exception.ValidMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDto {

    @NotBlank(message = ValidMessage.INPUT_FIELD_CONTENT)
    private String username;
    @NotBlank(message = ValidMessage.INPUT_FIELD_CONTENT)
    private String password;

}
