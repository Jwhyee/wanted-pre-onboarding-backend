package com.example.wanted.apply.service;

import com.example.wanted.apply.web.object.ApplyDto;
import com.example.wanted.apply.web.object.ApplyVO;

public interface ApplyFacade {
    ApplyVO saveApply(ApplyDto dto);
}
