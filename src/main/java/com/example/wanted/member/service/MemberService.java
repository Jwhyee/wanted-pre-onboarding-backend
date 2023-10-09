package com.example.wanted.member.service;

import com.example.wanted.common.exception.runtime.NotFoundException;
import com.example.wanted.member.domain.Member;
import com.example.wanted.member.domain.MemberRepository;
import com.example.wanted.member.web.object.MemberDto;
import com.example.wanted.member.web.object.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberVO saveMember(MemberDto dto) {

        Member member = memberRepository.save(Member.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build());

        return MemberVO.fromEntity(member);

    }

    @Transactional(readOnly = true)
    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }
}
