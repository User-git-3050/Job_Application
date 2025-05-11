package com.example.msjob.service;

import com.example.msjob.dao.request.CompanyRequest;
import com.example.msjob.dao.response.CompanyResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompanyService {
    List<CompanyResponse> getAllCompanies();

    CompanyResponse getCompanyById(Long id);

    String createCompany(CompanyRequest companyRequest,Long ownerId);

    String updateCompanyById(Long id, CompanyRequest companyRequest,String username);

    String deleteCompanyById(Long id,String username);

    CompanyResponse getCompanyByName(String companyName);

}
