package com.example.wanted.company.service;

import com.example.wanted.company.domain.Company;
import com.example.wanted.company.domain.CompanyRepository;
import com.example.wanted.company.web.object.CompanyDTO;
import com.example.wanted.company.web.object.CompanyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyVO saveCompany(CompanyDTO dto) {
        Company entity = companyRepository.save(Company.builder()
                .name(dto.getName())
                .nation(dto.getNation())
                .location(dto.getLocation())
                .build());

        return CompanyVO.fromEntity(entity);
    }
}