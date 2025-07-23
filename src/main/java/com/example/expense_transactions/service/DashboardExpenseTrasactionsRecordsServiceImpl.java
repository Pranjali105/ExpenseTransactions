package com.example.expense_transactions.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.expense_transactions.dto.DashboardExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.dto.DateDTO;
import com.example.expense_transactions.repository.DashboardExpenseTrasactionsRecordsRepository;

@Service
public class DashboardExpenseTrasactionsRecordsServiceImpl implements DashboardExpenseTrasactionsRecordsService {

	@Autowired
	DashboardExpenseTrasactionsRecordsRepository dashboardExpenseTrasactionsRecordsRepository;

	List<DashboardExpenseTrasactionsRecordsDTO> dashboardExpenseTrasactionsRecordsLst = new ArrayList<DashboardExpenseTrasactionsRecordsDTO>();

	@Override
	public ResponseEntity<List<DashboardExpenseTrasactionsRecordsDTO>> getTotalExpenseTrasactionsRecords(
			String expense_category, String month, int year, String paid_by) {

		DateDTO dateDTO = null;

		// start and end date when selected both month and yaer
		if (month != null) {
			String month_no = dashboardExpenseTrasactionsRecordsRepository.getMonthNumber(month);

			if (month_no != null) {
				dateDTO = dashboardExpenseTrasactionsRecordsRepository.geteStartEndDateFromMonthAndYear(month_no, year);
			}
		}
		else
		{
			dateDTO = dashboardExpenseTrasactionsRecordsRepository.getStartEndDateFromYear(year);
		}

		if (dateDTO.getStart_date() != null && dateDTO.getEnd_date() != null) {
			 if (expense_category != null && paid_by != null) {
				dashboardExpenseTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsRepository
						.getTotalExpenseTrasactionsRecordsByExpenseCategoryAndPaidBy(expense_category,
								dateDTO.getStart_date(), dateDTO.getEnd_date(), paid_by);
			} else if (expense_category != null) {
				dashboardExpenseTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsRepository
						.getTotalExpenseTrasactionsRecordsByExpenseCategory(expense_category, dateDTO.getStart_date(),
								dateDTO.getEnd_date());
			}
			else if(paid_by != null)
			{
				dashboardExpenseTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsRepository.getTotalExpenseTrasactionsRecordsByPaidBy(dateDTO.getStart_date(), dateDTO.getEnd_date(), paid_by);
			}
			else
			{
				dashboardExpenseTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsRepository.getTotalExpenseTrasactionsRecords(dateDTO.getStart_date(), dateDTO.getEnd_date());
			}
		}

		if (dashboardExpenseTrasactionsRecordsLst == null || dashboardExpenseTrasactionsRecordsLst.isEmpty()) {
			throw new NullPointerException("Expense transaction records list is null or empty.");
		}
		return ResponseEntity.ok(dashboardExpenseTrasactionsRecordsLst);

	}

}
