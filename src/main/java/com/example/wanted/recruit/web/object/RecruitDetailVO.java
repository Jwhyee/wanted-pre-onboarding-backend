package com.example.wanted.recruit.web.object;

import com.example.wanted.company.domain.Company;
import com.example.wanted.recruit.domain.Recruit;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecruitDetailVO {

    private Long id;

    /* Company */

    private String name;
    private String nation;
    private String location;

    /* Recruit */
    private String position;
    private int compensation;
    private String content;
    private String techStack;

    private List<Long> otherList;

    public static RecruitDetailVO fromEntity(Recruit recruit) {
        List<Long> list = recruit.getCompany().getRecruitList().stream()
                .filter(other -> !other.getId().equals(recruit.getId()))
                .map(Recruit::getId)
                .toList();

        return builder()
                .id(recruit.getId())
                .name(recruit.getCompany().getName())
                .nation(recruit.getCompany().getNation())
                .location(recruit.getCompany().getLocation())
                .position(recruit.getPosition())
                .compensation(recruit.getCompensation())
                .content(recruit.getContent())
                .techStack(recruit.getTechStack())
                .otherList(list)
                .build();
    }
}
