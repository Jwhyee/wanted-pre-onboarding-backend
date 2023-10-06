package com.example.wanted.company.web.object;

import com.example.wanted.company.domain.Company;
import com.example.wanted.recruit.domain.Recruit;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyVO {

    private Long id;
    private String name;
    private String nation;
    private String location;

    public static CompanyVO fromEntity(Company company) {
        return builder()
                .id(company.getId())
                .name(company.getName())
                .nation(company.getNation())
                .location(company.getLocation())
                .build();
    }
}
