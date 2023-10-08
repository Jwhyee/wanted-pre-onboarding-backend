package com.example.wanted.apply.service;

import com.example.wanted.apply.domain.Apply;
import com.example.wanted.apply.domain.ApplyRepository;
import com.example.wanted.apply.web.object.ApplyDto;
import com.example.wanted.apply.web.object.ApplyVO;
import com.example.wanted.common.exception.runtime.NotFoundException;
import com.example.wanted.member.domain.Member;
import com.example.wanted.member.domain.MemberRepository;
import com.example.wanted.recruit.domain.Recruit;
import com.example.wanted.recruit.domain.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final MemberRepository memberRepository;
    private final RecruitRepository recruitRepository;
    private final ApplyRepository applyRepository;

    @Transactional
    public ApplyVO saveApply(ApplyDto dto) {
        Member currentMember = findByMemberId(dto.getMemberId());
        Recruit currentRecruit = findByRecruitId(dto.getRecruitId());

        Apply apply = applyRepository.save(Apply.builder()
                .member(currentMember)
                .recruit(currentRecruit)
                .build());

        return ApplyVO.fromEntity(apply);
    }

    @Transactional(readOnly = true)
    public Member findByMemberId(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Recruit findByRecruitId(Long id) {
        return recruitRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }
}
