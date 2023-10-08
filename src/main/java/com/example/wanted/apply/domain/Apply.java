package com.example.wanted.apply.domain;

import com.example.wanted.company.domain.Company;
import com.example.wanted.member.domain.Member;
import com.example.wanted.recruit.domain.Recruit;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Apply {

    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Recruit recruit;

    @ManyToOne
    private Member member;

}
