package com.example.expense_transactions.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.expense_transactions.dto.DashboardExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.dto.DateDTO;
import com.example.expense_transactions.dto.TotalExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.repository.DashboardExpenseTrasactionsRecordsRepository;

@Service
public class DashboardExpenseTrasactionsRecordsServiceImpl implements DashboardExpenseTrasactionsRecordsService {

	@Autowired
	DashboardExpenseTrasactionsRecordsRepository dashboardExpenseTrasactionsRecordsRepository;

	List<DashboardExpenseTrasactionsRecordsDTO> dashboardExpenseTrasactionsRecordsLst = new ArrayList<DashboardExpenseTrasactionsRecordsDTO>();

	@Override
	public ResponseEntity<List<DashboardExpenseTrasactionsRecordsDTO>> getTotalExpenseTrasactionsRecords(
			TotalExpenseTrasactionsRecordsDTO totalExpenseTrasactionsRecordsDTO) {

		DateDTO dateDTO = null;

		// start and end date when selected both month and year
		if (totalExpenseTrasactionsRecordsDTO.getMonth() != null) {
			String month_no = dashboardExpenseTrasactionsRecordsRepository.getMonthNumber(totalExpenseTrasactionsRecordsDTO.getMonth());

			if (month_no != null) {
				dateDTO = dashboardExpenseTrasactionsRecordsRepository.geteStartEndDateFromMonthAndYear(month_no, totalExpenseTrasactionsRecordsDTO.getYear());
			}
		}
		else
		{
			// start and end date when selected only year
			dateDTO = dashboardExpenseTrasactionsRecordsRepository.getStartEndDateFromYear(totalExpenseTrasactionsRecordsDTO.getYear());
		}

		if (dateDTO.getStartDate() != null && dateDTO.getEndDate() != null) {
			 if (totalExpenseTrasactionsRecordsDTO.getExpenseCategory() != null && totalExpenseTrasactionsRecordsDTO.getPaidBy() != null) {
				dashboardExpenseTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsRepository
						.getTotalExpenseTrasactionsRecordsByExpenseCategoryAndPaidBy(totalExpenseTrasactionsRecordsDTO.getExpenseCategory(),
								dateDTO.getStartDate(), dateDTO.getEndDate(), totalExpenseTrasactionsRecordsDTO.getPaidBy());
			} else if (totalExpenseTrasactionsRecordsDTO.getExpenseCategory() != null) {
				dashboardExpenseTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsRepository
						.getTotalExpenseTrasactionsRecordsByExpenseCategory(totalExpenseTrasactionsRecordsDTO.getExpenseCategory(), dateDTO.getStartDate(),
								dateDTO.getEndDate());
			}
			else if(totalExpenseTrasactionsRecordsDTO.getPaidBy() != null)
			{
				dashboardExpenseTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsRepository.getTotalExpenseTrasactionsRecordsByPaidBy(dateDTO.getStartDate(), dateDTO.getEndDate(), totalExpenseTrasactionsRecordsDTO.getPaidBy());
			}
			else
			{
				dashboardExpenseTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsRepository.getTotalExpenseTrasactionsRecords(dateDTO.getStartDate(), dateDTO.getEndDate());
			}
		}

		if (dashboardExpenseTrasactionsRecordsLst == null || dashboardExpenseTrasactionsRecordsLst.isEmpty()) {
			throw new NullPointerException("Expense transaction records list is null or empty.");
		}
		return ResponseEntity.ok(dashboardExpenseTrasactionsRecordsLst);

	}

}
