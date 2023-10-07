package com.example.wanted.recruit.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {

    @Query("""
            SELECT A FROM Recruit A WHERE A.company.name LIKE %:keyword%
            OR A.content LIKE %:keyword%
            OR A.techStack LIKE %:keyword%
    """)
    List<Recruit> findAllByKeyword(@Param("keyword") String keyword);

}
