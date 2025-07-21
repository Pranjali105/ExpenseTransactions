package com.example.expense_transactions.dto;

import java.sql.Date;

public class DateDTO {
	
	private Date start_date;
	
	private Date end_date;

	public DateDTO(Date start_date, Date end_date) {
		super();
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

}
