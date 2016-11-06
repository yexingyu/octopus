package org.nxstudio.octopus.controller;

import java.util.List;

import org.nxstudio.octopus.mybatis.model.RawDataNasdaqCompany;
import org.nxstudio.octopus.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	/**
	 * getAllCompanies
	 *
	 * @return
	 */
	@RequestMapping("/")
	public List<RawDataNasdaqCompany> getAllCompanies() {
		return companyService.getAllCompanies();
	}

	/**
	 * getAvailableCompanies
	 *
	 * @return
	 */
	@RequestMapping("/available")
	public List<RawDataNasdaqCompany> getAvailableCompanies() {
		return companyService.getAvailableCompanies();
	}

}
