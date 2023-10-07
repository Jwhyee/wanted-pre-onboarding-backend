package com.example.wanted.recruit.service;

import com.example.wanted.common.exception.runtime.NotFoundException;
import com.example.wanted.company.domain.Company;
import com.example.wanted.company.domain.CompanyRepository;
import com.example.wanted.recruit.domain.Recruit;
import com.example.wanted.recruit.domain.RecruitRepository;
import com.example.wanted.recruit.web.object.RecruitDTO;
import com.example.wanted.recruit.web.object.RecruitVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        return RecruitVO.fromEntity(recruit);
    }

    private Company findCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Recruit findById(Long id) {
        return recruitRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void updateRecruit(Long id, RecruitDTO dto) {
        Recruit currentRecruit = findById(id);
        currentRecruit.updateBasicInfo(dto.getPosition(), dto.getCompensation(), dto.getContent(), dto.getTechStack());
    }

    @Transactional
    public void deleteRecruit(Long id) {
        Recruit currentRecruit = findById(id);
        recruitRepository.delete(currentRecruit);
    }

    public List<RecruitVO> findAllRecruit(String search) {
        List<Recruit> recruitList = null;

        if(search == null || search.length() == 0) recruitList = recruitRepository.findAll();
        else recruitList = recruitRepository.findAllByKeyword(search);

        return recruitList.stream()
                .map(RecruitVO::fromEntity)
                .toList();
    }
}
