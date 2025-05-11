package com.example.msjob.mapper;

import com.example.msjob.dao.request.CompanyRequest;
import com.example.msjob.dao.response.CompanyResponse;
import com.example.msjob.entity.CompanyEntity;

public enum CompanyMapper {
    COMPANY_MAPPER;

    public CompanyEntity mapToEntity(CompanyRequest companyRequest,Long ownerId){
        return CompanyEntity.builder()
                .name(companyRequest.getName())
                .about(companyRequest.getAbout())
                .details(companyRequest.getDetails())
                .address(companyRequest.getAddress())
                .ownerId(ownerId)
                .build();
    }

    public CompanyResponse mapToResponse(CompanyEntity companyEntity){
        return CompanyResponse.builder()
                .id(companyEntity.getId())
                .name(companyEntity.getName())
                .details(companyEntity.getDetails())
                .about(companyEntity.getAbout())
                .address(companyEntity.getAddress())
                .build();
    }

}
