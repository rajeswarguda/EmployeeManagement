package com.cognizant.empmgmt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cognizant.empmgmt.dto.EmployeeDTO;
import com.cognizant.empmgmt.manager.ErrorManagerImpl;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private static final String GET_EMP_DETAILS_BY_NO = "select empno,ename,hiredate,department,last_modified_date from employee where empno=?";
	private static final String INSERT_EMP_DETAILS = "INSERT INTO Employee VALUES(?,?,?,?,SYSDATE)";
	private static final String UPDATE_EMP_DETAILS = "UPDATE Employee set ename=?, hiredate=?, department=?, last_modified_date=SYSDATE where empno=?";

	private static final Logger LOG = LoggerFactory.getLogger(ErrorManagerImpl.class);

	@Autowired
	@Qualifier("template")
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int insert(EmployeeDTO empdto) {
		int result = jdbcTemplate.update(INSERT_EMP_DETAILS, empdto.getEmpId(), empdto.getEmpName(),
				new java.sql.Date(empdto.getJoiningDate().getTime().getTime()), empdto.getDepartment());
		LOG.debug("result=" + result);
		return result;
	}

	public int update(EmployeeDTO empdto) {
		System.out.println("empdto=" + empdto);
		int result = jdbcTemplate.update(UPDATE_EMP_DETAILS, empdto.getEmpName(),
				new java.sql.Date(empdto.getJoiningDate().getTime().getTime()), empdto.getDepartment(),
				empdto.getEmpId());
		LOG.debug("result=" + result);
		return result;
	}

	public EmployeeDTO getEmployee(int empId) {
		EmployeeDTO empdto = null;

		try {
			empdto = jdbcTemplate.queryForObject(GET_EMP_DETAILS_BY_NO, new Object[] { empId }, new EmpRowMapper());
			LOG.debug("empdto=" + empdto);
		} catch (EmptyResultDataAccessException e) {
		}

		return empdto;
	}

	public List<EmployeeDTO> getAllEmployees() {
		List<EmployeeDTO> empList = null;
		return empList;
	}

	private static final class EmpRowMapper implements RowMapper<EmployeeDTO> {

		@Override
		public EmployeeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

			EmployeeDTO empdto = new EmployeeDTO();

			empdto.setEmpId(rs.getInt(1));
			empdto.setEmpName(rs.getString(2));

			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(rs.getTimestamp(3));
			empdto.setJoiningDate(cal1);

			empdto.setDepartment(rs.getString(4));

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(rs.getTimestamp(5));
			empdto.setJoiningDate(cal2);
			empdto.setLastModifidDate(cal2);

			return empdto;
		}

	}

}
