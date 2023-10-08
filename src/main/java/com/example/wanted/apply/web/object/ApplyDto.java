package com.example.wanted.apply.web.object;

import com.example.wanted.common.exception.ValidMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplyDto {

    @Min(value = 1, message = ValidMessage.FIELD_MINIMUM_CONTENT)
    @NotNull(message = ValidMessage.INPUT_FIELD_CONTENT)
    private Long recruitId;

    @Min(value = 1, message = ValidMessage.FIELD_MINIMUM_CONTENT)
    @NotNull(message = ValidMessage.INPUT_FIELD_CONTENT)
    private Long memberId;

}
