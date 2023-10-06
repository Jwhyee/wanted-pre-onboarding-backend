package com.example.wanted.recruit.web.object;

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
public class RecruitDTO {

    private Long companyId;
    @NotBlank(message = ValidMessage.INPUT_FIELD_CONTENT)
    private String position;
    private int compensation;
    @NotBlank(message = ValidMessage.INPUT_FIELD_CONTENT)
    private String content;
    @NotBlank(message = ValidMessage.INPUT_FIELD_CONTENT)
    private String techStack;

}
