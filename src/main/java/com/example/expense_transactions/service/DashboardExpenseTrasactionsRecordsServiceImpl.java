package com.example.expense_transactions.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.expense_transactions.dto.DashboardExpenseCategoryTrasactionsRecordsDTO;
import com.example.expense_transactions.dto.DashboardExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.dto.DateDTO;
import com.example.expense_transactions.dto.TotalExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.repository.DashboardExpenseTrasactionsRecordsRepository;

@Service
public class DashboardExpenseTrasactionsRecordsServiceImpl implements DashboardExpenseTrasactionsRecordsService {

	@Autowired
	DashboardExpenseTrasactionsRecordsRepository dashboardExpenseTrasactionsRecordsRepository;

	List<DashboardExpenseTrasactionsRecordsDTO> dashboardExpenseTrasactionsRecordsLst = new ArrayList<DashboardExpenseTrasactionsRecordsDTO>();

	List<DashboardExpenseCategoryTrasactionsRecordsDTO> dashboardExpenseCategoryTrasactionsRecordsLst = new ArrayList<DashboardExpenseCategoryTrasactionsRecordsDTO>();

	DashboardExpenseCategoryTrasactionsRecordsDTO dashboardExpenseCategoryTrasactionsRecordsDTO = new DashboardExpenseCategoryTrasactionsRecordsDTO();

	@Override
	public ResponseEntity<List<DashboardExpenseTrasactionsRecordsDTO>> getTotalExpenseTrasactionsRecords(
			TotalExpenseTrasactionsRecordsDTO totalExpenseTrasactionsRecordsDTO) {

		DateDTO dateDTO = getStartDateEndDateByYear(totalExpenseTrasactionsRecordsDTO);

		if (dateDTO.getStartDate() != null && dateDTO.getEndDate() != null) {
			if (totalExpenseTrasactionsRecordsDTO.getExpenseCategory() != null
					&& totalExpenseTrasactionsRecordsDTO.getPaidBy() != null) {
				dashboardExpenseTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsRepository
						.getTotalExpenseTrasactionsRecordsByExpenseCategoryAndPaidBy(
								totalExpenseTrasactionsRecordsDTO.getExpenseCategory(), dateDTO.getStartDate(),
								dateDTO.getEndDate(), totalExpenseTrasactionsRecordsDTO.getPaidBy());
			} else if (totalExpenseTrasactionsRecordsDTO.getExpenseCategory() != null) {
				dashboardExpenseTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsRepository
						.getTotalExpenseTrasactionsRecordsByExpenseCategory(
								totalExpenseTrasactionsRecordsDTO.getExpenseCategory(), dateDTO.getStartDate(),
								dateDTO.getEndDate());
			} else if (totalExpenseTrasactionsRecordsDTO.getPaidBy() != null) {
				dashboardExpenseTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsRepository
						.getTotalExpenseTrasactionsRecordsByPaidBy(dateDTO.getStartDate(), dateDTO.getEndDate(),
								totalExpenseTrasactionsRecordsDTO.getPaidBy());
			} else {
				dashboardExpenseTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsRepository
						.getTotalExpenseTrasactionsRecords(dateDTO.getStartDate(), dateDTO.getEndDate());
			}
		}

		if (dashboardExpenseTrasactionsRecordsLst == null || dashboardExpenseTrasactionsRecordsLst.isEmpty()) {
			// Return an empty list instead of throwing an exception so callers receive a
			// valid response
			return ResponseEntity.ok(Collections.emptyList());
		}
		return ResponseEntity.ok(dashboardExpenseTrasactionsRecordsLst);

	}

	private DateDTO getStartDateEndDateByYear(TotalExpenseTrasactionsRecordsDTO totalExpenseTrasactionsRecordsDTO) {
		DateDTO dateDTO = null;

		// start and end date when selected both month and year
		if (totalExpenseTrasactionsRecordsDTO.getMonth() != null) {
			String month_no = dashboardExpenseTrasactionsRecordsRepository
					.getMonthNumber(totalExpenseTrasactionsRecordsDTO.getMonth());

			if (month_no != null) {
				dateDTO = dashboardExpenseTrasactionsRecordsRepository.geteStartEndDateFromMonthAndYear(month_no,
						totalExpenseTrasactionsRecordsDTO.getYear());
			}
		} else {
			// start and end date when selected only year
			dateDTO = dashboardExpenseTrasactionsRecordsRepository
					.getStartEndDateFromYear(totalExpenseTrasactionsRecordsDTO.getYear());
		}
		return dateDTO;
	}

	@Override
	public ResponseEntity<DashboardExpenseCategoryTrasactionsRecordsDTO> getTotalExpenseCategoryTrasactionsRecords(
			TotalExpenseTrasactionsRecordsDTO totalExpenseTrasactionsRecordsDTO) {

		double amount = 0;

		if (totalExpenseTrasactionsRecordsDTO.getExpenseCategory() == null) {
			throw new NullPointerException("Expense category is null.");
		} else {
			dashboardExpenseTrasactionsRecordsLst = this
					.getTotalExpenseTrasactionsRecords(totalExpenseTrasactionsRecordsDTO).getBody().stream().toList();

			if (dashboardExpenseTrasactionsRecordsLst == null || dashboardExpenseTrasactionsRecordsLst.isEmpty()) {
				throw new NullPointerException("Expense category transaction records list is null or empty.");

			} else {
				for (DashboardExpenseTrasactionsRecordsDTO dashboardExpenseTrasactionsRecordsDTO : dashboardExpenseTrasactionsRecordsLst) {
					if (dashboardExpenseTrasactionsRecordsDTO.getCategoryName() != null) {

						amount = amount + dashboardExpenseTrasactionsRecordsDTO.getAmount();
					}

					dashboardExpenseCategoryTrasactionsRecordsDTO.setAmount(amount);
					dashboardExpenseCategoryTrasactionsRecordsDTO
							.setCategoryName(dashboardExpenseTrasactionsRecordsDTO.getCategoryName());

				}

			}

			return ResponseEntity.ok(dashboardExpenseCategoryTrasactionsRecordsDTO);
		}
	}

	@Override
	public ResponseEntity<List<DashboardExpenseCategoryTrasactionsRecordsDTO>> getTotalExpenseTrasactionsRecordsPaidBy(
			TotalExpenseTrasactionsRecordsDTO totalExpenseTrasactionsRecordsDTO) {

		DateDTO dateDTO = getStartDateEndDateByYear(totalExpenseTrasactionsRecordsDTO);

		if (dateDTO != null) {
			if (totalExpenseTrasactionsRecordsDTO.getPaidBy() == null) {

				dashboardExpenseCategoryTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsRepository
						.getTotalAllExpenseTrasactionsRecords(dateDTO.getStartDate(), dateDTO.getEndDate());
				if (dashboardExpenseCategoryTrasactionsRecordsLst != null) {
					for (DashboardExpenseCategoryTrasactionsRecordsDTO dashboardExpenseCategoryTrasactionsRecordsDTO : dashboardExpenseCategoryTrasactionsRecordsLst) {
						dashboardExpenseCategoryTrasactionsRecordsDTO.setCategoryName(null);
					}
				}

			} else {
				dashboardExpenseCategoryTrasactionsRecordsLst = dashboardExpenseTrasactionsRecordsRepository
						.getTotalAllExpenseTrasactionsRecordsByPaidBy(dateDTO.getStartDate(), dateDTO.getEndDate(),
								totalExpenseTrasactionsRecordsDTO.getPaidBy());
			}
		}

		return ResponseEntity.ok(dashboardExpenseCategoryTrasactionsRecordsLst);
	}

}
