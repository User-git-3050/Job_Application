package com.product.userservice.controller;

import com.product.userservice.dto.request.SkillRequest;
import com.product.userservice.dto.response.SkillResponse;
import com.product.userservice.service.inter.SkillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/skill/")
public class SkillController {
    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public ResponseEntity<List<SkillResponse>> getAllSkills(){
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    @GetMapping("{id}")
    public ResponseEntity<SkillResponse> getSkillById(@PathVariable Long id){
        return ResponseEntity.ok(skillService.getSkillById(id));
    }

    @GetMapping("ids")
    public ResponseEntity<List<SkillResponse>> getSkillByIds(@RequestParam List<Long> ids){
        return ResponseEntity.ok(skillService.getAllSkillsByIds(ids));
    }

    @PostMapping
    public ResponseEntity<String> createSkill(@Valid @RequestBody SkillRequest skillRequest){
        return ResponseEntity.ok(skillService.createSkill(skillRequest));
    }

    @PutMapping ("{id}")
    public ResponseEntity<String> updateSkill(@Valid @RequestBody SkillRequest skillRequest,@PathVariable Long id){
        return ResponseEntity.ok(skillService.updateSkillById(id,skillRequest));
    }

    @DeleteMapping("{skillId}")
    public ResponseEntity<String> deleteSkill(@PathVariable Long skillId){
        return ResponseEntity.ok(skillService.deleteSkillById(skillId));
    }

}
