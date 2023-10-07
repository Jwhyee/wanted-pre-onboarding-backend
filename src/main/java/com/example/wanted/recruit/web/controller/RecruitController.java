package com.example.wanted.recruit.web.controller;

import com.example.wanted.recruit.service.RecruitService;
import com.example.wanted.recruit.web.object.RecruitDTO;
import com.example.wanted.recruit.web.object.RecruitVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruit")
public class RecruitController {

    private final RecruitService recruitService;

    @PostMapping
    public ResponseEntity<RecruitVO> saveRecruitApi(@RequestBody @Valid RecruitDTO dto) {
        RecruitVO recruitVO = recruitService.saveRecruit(dto);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{recruitId}")
                                .buildAndExpand(recruitVO.getId())
                                .toUri())
                .build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRecruitApi(@PathVariable Long id, @RequestBody @Valid RecruitDTO dto) {
        recruitService.updateRecruit(id, dto);
    }

}
