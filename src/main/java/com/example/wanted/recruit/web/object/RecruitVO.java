package com.example.wanted.recruit.web.object;

import com.example.wanted.recruit.domain.Recruit;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecruitVO {

    private Long id;
    private String position;
    private int compensation;
    private String content;
    private String techStack;

    public static RecruitVO fromEntity(Recruit recruit) {
        return builder()
                .id(recruit.getId())
                .position(recruit.getPosition())
                .compensation(recruit.getCompensation())
                .content(recruit.getContent())
                .techStack(recruit.getTechStack())
                .build();
    }
}
