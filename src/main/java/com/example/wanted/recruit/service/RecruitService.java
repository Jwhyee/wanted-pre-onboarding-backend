package com.example.wanted.recruit.service;

import com.example.wanted.common.exception.runtime.NotFoundException;
import com.example.wanted.company.domain.Company;
import com.example.wanted.company.domain.CompanyRepository;
import com.example.wanted.company.service.CompanyService;
import com.example.wanted.recruit.domain.Recruit;
import com.example.wanted.recruit.domain.RecruitRepository;
import com.example.wanted.recruit.web.object.RecruitDTO;
import com.example.wanted.recruit.web.object.RecruitVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitRepository recruitRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public RecruitVO saveRecruit(RecruitDTO dto) {
        Company currentCompany = findCompanyById(dto.getCompanyId());
        Recruit recruit = recruitRepository.save(Recruit.builder()
                .position(dto.getPosition())
                .compensation(dto.getCompensation())
                .company(currentCompany)
                .content(dto.getContent())
                .techStack(dto.getTechStack())
                .build());

        currentCompany.getRecruitList().add(recruit);

        return RecruitVO.fromEntity(recruit);
    }

    private Company findCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }
}
