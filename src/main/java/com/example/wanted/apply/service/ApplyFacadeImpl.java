package com.example.wanted.apply.service;

import com.example.wanted.apply.domain.Apply;
import com.example.wanted.apply.domain.ApplyRepository;
import com.example.wanted.apply.web.object.ApplyDto;
import com.example.wanted.apply.web.object.ApplyVO;
import com.example.wanted.common.exception.runtime.NotFoundException;
import com.example.wanted.member.domain.Member;
import com.example.wanted.member.domain.MemberRepository;
import com.example.wanted.member.service.MemberService;
import com.example.wanted.recruit.domain.Recruit;
import com.example.wanted.recruit.domain.RecruitRepository;
import com.example.wanted.recruit.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplyFacadeImpl implements ApplyFacade{

    private final MemberService memberService;
    private final RecruitService recruitService;

    private final ApplyRepository applyRepository;

    @Override
    @Transactional
    public ApplyVO saveApply(ApplyDto dto) {
        Member currentMember = memberService.findById(dto.getMemberId());
        Recruit currentRecruit = recruitService.findById(dto.getRecruitId());

        Apply apply = applyRepository.save(Apply.builder()
                .member(currentMember)
                .recruit(currentRecruit)
                .build());

        return ApplyVO.fromEntity(apply);
    }
}
