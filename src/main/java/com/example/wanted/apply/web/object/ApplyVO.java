package com.example.wanted.apply.web.object;

import com.example.wanted.apply.domain.Apply;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplyVO {

    private Long id;

    public static ApplyVO fromEntity(Apply apply) {
        return builder()
                .id(apply.getId())
                .build();
    }
}
