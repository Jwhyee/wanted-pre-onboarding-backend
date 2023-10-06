package com.example.wanted.recruit.web.controller;

import com.example.wanted.recruit.service.RecruitService;
import com.example.wanted.recruit.web.object.RecruitDTO;
import com.example.wanted.recruit.web.object.RecruitVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruit")
public class RecruitController {

    private final RecruitService recruitService;

    @PostMapping
    public ResponseEntity<RecruitVO> saveRecruit(@RequestBody @Valid RecruitDTO dto) {
        RecruitVO recruitVO = recruitService.saveRecruit(dto);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{recruitId}")
                                .buildAndExpand(recruitVO.getId())
                                .toUri())
                .build();
    }

}
