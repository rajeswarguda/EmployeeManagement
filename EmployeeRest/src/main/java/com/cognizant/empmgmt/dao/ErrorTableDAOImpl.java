package com.cognizant.empmgmt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cognizant.empmgmt.dto.ErrorTableDTO;

@Repository
public class ErrorTableDAOImpl implements ErrorTableDAO{
	
	private static final String INSERT_ERROR_DETAILS="INSERT INTO ErrorTab VALUES(?,?,SYSDATE)";
	@Autowired
    @Qualifier("template")
	private JdbcTemplate jdbcTemplate;  

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
		this.jdbcTemplate = jdbcTemplate;  
	}  
	
	@Override
	public int insert(ErrorTableDTO empdto) {
		int result = jdbcTemplate.update(INSERT_ERROR_DETAILS, empdto.getErrorCode(), empdto.getErrorDesc());  
		return result;
	}

	
}
