package com.cognizant.empmgmt.manager;

import com.cognizant.empmgmt.dto.ErrorTableDTO;

public interface ErrorManager {
	public int logError(ErrorTableDTO errorDTO);
}
