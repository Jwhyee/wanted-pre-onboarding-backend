package com.example.wanted.apply.web.controller;

import com.example.wanted.apply.service.ApplyService;
import com.example.wanted.apply.web.object.ApplyDto;
import com.example.wanted.apply.web.object.ApplyVO;
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
@RequestMapping("/api/apply")
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping
    public ResponseEntity<String> saveApplyApi(@RequestBody @Valid ApplyDto dto) {
        ApplyVO applyVO = applyService.saveApply(dto);

        return ResponseEntity.created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(applyVO.getId())
                                .toUri())
                .build();
    }

}
