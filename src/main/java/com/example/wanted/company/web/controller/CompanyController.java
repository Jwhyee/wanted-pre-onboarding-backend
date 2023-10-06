package com.example.wanted.company.web.controller;

import com.example.wanted.company.service.CompanyService;
import com.example.wanted.company.web.object.CompanyDTO;
import com.example.wanted.company.web.object.CompanyVO;
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
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<RecruitVO> saveCompanyApi(@RequestBody @Valid CompanyDTO dto) {
        CompanyVO companyVO = companyService.saveCompany(dto);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{companyId}")
                                .buildAndExpand(companyVO.getId())
                                .toUri())
                .build();
    }

}
