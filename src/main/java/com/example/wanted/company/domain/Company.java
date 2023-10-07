package com.example.wanted.company.domain;

import com.example.wanted.recruit.domain.Recruit;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nation;

    @Column(nullable = false)
    private String location;

    @OneToMany
    private List<Recruit> recruitList;

}
