package com.example.wanted.member.web.controller;

import com.example.wanted.company.service.CompanyService;
import com.example.wanted.company.web.object.CompanyDTO;
import com.example.wanted.company.web.object.CompanyVO;
import com.example.wanted.member.service.MemberService;
import com.example.wanted.member.web.object.MemberDto;
import com.example.wanted.member.web.object.MemberVO;
import com.example.wanted.recruit.web.object.RecruitVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<RecruitVO> saveCompanyApi(@RequestBody @Valid MemberDto dto) {
        MemberVO memberVO = memberService.saveMember(dto);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(memberVO.getId())
                                .toUri())
                .build();
    }

}
