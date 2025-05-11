package com.example.msjob.service.impl;

import com.example.msjob.client.UserClient;
import com.example.msjob.dao.request.CompanyRequest;
import com.example.msjob.dao.response.CompanyResponse;
import com.example.msjob.dao.response.UserResponse;
import com.example.msjob.entity.CompanyEntity;
import com.example.msjob.exception.NotFoundException;
import com.example.msjob.exception.UnauthorizedException;
import com.example.msjob.repository.CompanyRepository;
import com.example.msjob.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.example.msjob.enums.ErrorMessage.COMPANY_NOT_FOUND;
import static com.example.msjob.enums.ErrorMessage.COMPANY_NOT_FOUND_WITH_ID;
import static com.example.msjob.enums.InfoMessage.*;
import static com.example.msjob.mapper.CompanyMapper.COMPANY_MAPPER;
import static java.lang.String.format;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserClient userClient;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, UserClient userClient) {
        this.companyRepository = companyRepository;
        this.userClient = userClient;
    }


    @Override
    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.findAll().stream().map(COMPANY_MAPPER::mapToResponse).toList();
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(COMPANY_MAPPER::mapToResponse)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                COMPANY_NOT_FOUND_WITH_ID.getMessage(),
                                id
                        )
                ));
    }

    @Override
    public String createCompany(CompanyRequest companyRequest,Long ownerId) {
        companyRepository.save(COMPANY_MAPPER.mapToEntity(companyRequest,ownerId));

        return COMPANY_CREATED.getMessage();
    }

    @Override
    public String updateCompanyById(Long id, CompanyRequest companyRequest, String username) {
        UserResponse userResponse = userClient.findUserByUsername(username).getBody();

        CompanyEntity companyEntity = companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                COMPANY_NOT_FOUND_WITH_ID.getMessage(),
                                id
                        )
                ));

        if (userResponse != null && hasPermission(userResponse, companyEntity)) {
            companyEntity.setName(companyRequest.getName());
            companyEntity.setAddress(companyRequest.getAddress());
            companyEntity.setAbout(companyRequest.getAbout());
            companyEntity.setDetails(companyRequest.getDetails());

            companyRepository.save(companyEntity);

            return COMPANY_UPDATED.getMessage();
        }

        throw new UnauthorizedException(NO_PERMISSION.getMessage());


    }

    @Override
    public String deleteCompanyById(Long id, String username) {
        UserResponse userResponse = userClient.findUserByUsername(username).getBody();

        CompanyEntity companyEntity = companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                COMPANY_NOT_FOUND_WITH_ID.getMessage(),
                                id
                        )
                ));

        if (userResponse != null && hasPermission(userResponse, companyEntity)) {
            companyRepository.delete(companyEntity);
            return COMPANY_DELETED.getMessage();
        }
        throw new UnauthorizedException(NO_PERMISSION.getMessage());


    }

    @Override
    public CompanyResponse getCompanyByName(String companyName) {
        return companyRepository.findByName(companyName)
                .map(COMPANY_MAPPER::mapToResponse)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                COMPANY_NOT_FOUND.getMessage()
                        )
                ));
    }

    private Boolean hasPermission(UserResponse userResponse, CompanyEntity companyEntity) {
        return userResponse.getRole().equals("ADMIN") ||
                userResponse.getRole().equals("OWNER") && Objects.equals(companyEntity.getOwnerId(), userResponse.getId());
    }


}
