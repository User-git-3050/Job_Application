package com.product.userservice.service.inter;

import com.product.userservice.dto.request.SkillRequest;
import com.product.userservice.dto.response.SkillResponse;

import java.util.List;

public interface SkillService {
    List<SkillResponse> getAllSkills();

    SkillResponse getSkillById(Long id);

    String createSkill(SkillRequest skillRequest);

    String updateSkillById(Long id , SkillRequest skillRequest);

    String deleteSkillById(Long id);

    List<SkillResponse> getAllSkillsByIds(List<Long> ids);
}
