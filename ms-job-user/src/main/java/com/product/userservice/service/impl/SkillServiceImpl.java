package com.product.userservice.service.impl;

import com.product.userservice.dto.request.SkillRequest;
import com.product.userservice.dto.response.SkillResponse;
import com.product.userservice.entity.SkillEntity;
import com.product.userservice.exception.FieldAlreadyExists;
import com.product.userservice.exception.NotFoundException;
import com.product.userservice.mapper.SkillMapper;
import com.product.userservice.repository.SkillRepository;
import com.product.userservice.service.inter.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.product.userservice.enums.ErrorMessage.SKILL_EXISTS_WITH_NAME;
import static com.product.userservice.enums.ErrorMessage.SKILL_NOT_FOUND_WITH_ID;
import static com.product.userservice.enums.InfoMessage.*;
import static com.product.userservice.mapper.SkillMapper.SKILL_MAPPER;
import static java.lang.String.format;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }


    @Override
    public List<SkillResponse> getAllSkills() {
        return skillRepository.findAll().stream().map(SKILL_MAPPER::mapToResponse).toList();
    }

    @Override
    public SkillResponse getSkillById(Long id) {
        return SkillMapper.SKILL_MAPPER.mapToResponse(getSkillEntityById(id));
    }

    @Override
    public String createSkill(SkillRequest skillRequest) {
        isSkillExists(skillRequest.getName());
        skillRepository.save(SKILL_MAPPER.mapToEntity(skillRequest));
        return SKILL_CREATED.getMessage();
    }

    @Override
    public String updateSkillById(Long id, SkillRequest skillRequest) {
        SkillEntity skillEntity = getSkillEntityById(id);

        isSkillExists(skillRequest.getName());

        skillEntity.setName(skillRequest.getName());
        skillEntity.setDescription(skillRequest.getDescription());
        skillRepository.save(skillEntity);

        return SKILL_UPDATED.getMessage();

    }

    @Override
    public String deleteSkillById(Long id) {
        getSkillEntityById(id);
        skillRepository.deleteById(id);

        return SKILL_DELETED.getMessage();
    }

    @Override
    public List<SkillResponse> getAllSkillsByIds(List<Long> ids) {
        return skillRepository.findAllById(ids).stream().map(SKILL_MAPPER::mapToResponse).toList();
    }

    private void isSkillExists(String name){
         if(skillRepository.findByName(name).isPresent()){
             throw new FieldAlreadyExists(
                     format(
                             SKILL_EXISTS_WITH_NAME.getMessage(),
                             name
                     )
             );
         }
    }

    private SkillEntity getSkillEntityById(Long id){
        return skillRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                SKILL_NOT_FOUND_WITH_ID.getMessage(),
                                id
                        )
                ));
    }

}
