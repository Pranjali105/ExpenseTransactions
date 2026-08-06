package com.example.expense_transactions.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.expense_transactions.dto.AccountDetailsDTO;
import com.example.expense_transactions.dto.AutoSetupPaymentDTO;
import com.example.expense_transactions.dto.LoanDetailsDTO;
import com.example.expense_transactions.repository.AccountDetailsRepository;
import com.example.expense_transactions.repository.AutoSetupPaymentRepository;

@Service
public class AutoSetupServiceImpl implements AutoSetupService {

	@Autowired
	AccountDetailsRepository accountDetailsRepository;

	@Autowired
	AutoSetupPaymentRepository autoSetupPaymentRepository;

	int totalInstallments = 0;

	@Override
	public ResponseEntity<String> addSetupAutoPayment(AutoSetupPaymentDTO autoSetupPaymentDTO) {

		AccountDetailsDTO accountDetailsDTOLst = null;
		LoanDetailsDTO loanDetailsDTO = null;

		String endDate = null;

		String accountNumber = autoSetupPaymentDTO.getAccountNo();

		boolean is12DigitNumber = is12DigitNumber(accountNumber);

		if (is12DigitNumber == true) {

			accountDetailsDTOLst = accountDetailsRepository.getAccountDetails(accountNumber);

			if (accountDetailsDTOLst != null) {
				autoSetupPaymentDTO.setBankName(accountDetailsDTOLst.getBankName());

				loanDetailsDTO = autoSetupPaymentRepository.getLoanDetails(autoSetupPaymentDTO.getSubCategoryName(),
						autoSetupPaymentDTO.getBankName());

				if (loanDetailsDTO != null) {
					autoSetupPaymentDTO.setRateOfInterest(loanDetailsDTO.getRateOfInterest());

					if (autoSetupPaymentDTO.getFrequency() == "OneTime") {
						autoSetupPaymentDTO.setTenure(null);
						autoSetupPaymentDTO.setRateOfInterest(null);
						autoSetupPaymentDTO.setEMIAmount(null);
						autoSetupPaymentDTO.setRemainingInstallment(null);
						autoSetupPaymentDTO.setRemainingAmount(null);
						autoSetupPaymentDTO.setEndDate(null);

					} else {
						if (autoSetupPaymentDTO.getEMIAmount() == null) {
							double EMIAmount = EMICalculator(autoSetupPaymentDTO);
							autoSetupPaymentDTO.setEMIAmount(EMIAmount);

							autoSetupPaymentDTO.setStartDate(getCurrentTimestampdate());

							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					        LocalDate startDate = LocalDateTime.parse(autoSetupPaymentDTO.getStartDate(), formatter).toLocalDate();

							if (autoSetupPaymentDTO.getFrequency().equalsIgnoreCase("Monthly")) {
								endDate = startDate.plusMonths(autoSetupPaymentDTO.getTenure()).toString();
							} else if (autoSetupPaymentDTO.getFrequency().equalsIgnoreCase("Quarterly")) {
								endDate = startDate.plusMonths(autoSetupPaymentDTO.getTenure() * 3).toString();
							} else if (autoSetupPaymentDTO.getFrequency().equalsIgnoreCase("Yearly")) {
								endDate = startDate.plusYears(autoSetupPaymentDTO.getTenure()).toString();
							}

							//need to correct the date format to match the startDate format
							autoSetupPaymentDTO.setEndDate(endDate);
						}

						
						//need to check
						autoSetupPaymentDTO.setRemainingAmount(
								autoSetupPaymentDTO.getAmount() - autoSetupPaymentDTO.getEMIAmount());

						autoSetupPaymentDTO.setTransactionDate(getCurrentTimestampdate());

						if (autoSetupPaymentDTO.getRemainingInstallment() == 0) {
							autoSetupPaymentDTO.setLoanStatus("Completed");
						} else {
							autoSetupPaymentDTO.setLoanStatus("In Progress");
						}

					}

					int n = autoSetupPaymentRepository.addSetupAutoPayment(autoSetupPaymentDTO.getCategoryName(),
							autoSetupPaymentDTO.getSubCategoryName(), autoSetupPaymentDTO.getAccountNo(),
							autoSetupPaymentDTO.getBankName(), autoSetupPaymentDTO.getAmount(),
							autoSetupPaymentDTO.getTenure(), autoSetupPaymentDTO.getRateOfInterest(),
							autoSetupPaymentDTO.getFrequency(), autoSetupPaymentDTO.getEMIAmount(),
							autoSetupPaymentDTO.getRemainingInstallment(), autoSetupPaymentDTO.getRemainingAmount(),
							autoSetupPaymentDTO.getPaymentMode(), autoSetupPaymentDTO.getPaymentModeType(),
							autoSetupPaymentDTO.getByWhom(), autoSetupPaymentDTO.getStartDate(),
							autoSetupPaymentDTO.getEndDate(), autoSetupPaymentDTO.getTransactionDate(),
							autoSetupPaymentDTO.getLoanStatus());

					if (n == 0) {
						return ResponseEntity.ok("Error occured while inserting the data");
					}
				} else {
					return ResponseEntity.ok("Loan details not found for the given subcategory and bank");
				}

			} else {
				return ResponseEntity.ok("Account number does not exist");

			}
		} else {
			return ResponseEntity.ok("Account number must be a 12-digit number");
		}
		autoSetupPaymentDTO.setRemainingInstallment(totalInstallments - 1);
		return ResponseEntity.ok("Data inserted successfully");

	}

	private double EMICalculator(AutoSetupPaymentDTO autoSetupPaymentDTO) {

		double ratePerPeriod;

		switch (autoSetupPaymentDTO.getFrequency().toLowerCase()) {
		case "monthly":
			ratePerPeriod = autoSetupPaymentDTO.getRateOfInterest() / (12 * 100);
			totalInstallments = autoSetupPaymentDTO.getTenure() * 12;
			break;

		case "quarterly":
			ratePerPeriod = autoSetupPaymentDTO.getRateOfInterest() / (4 * 100);
			totalInstallments = autoSetupPaymentDTO.getTenure() * 4;
			break;

		case "yearly":
			ratePerPeriod = autoSetupPaymentDTO.getRateOfInterest() / 100;
			totalInstallments = autoSetupPaymentDTO.getTenure();
			break;

		default:
			throw new IllegalArgumentException("Invalid frequency. Use monthly, quarterly, or yearly.");
		}

		if (ratePerPeriod == 0) {
			return autoSetupPaymentDTO.getAmount() / totalInstallments;
		}

		double emi = (autoSetupPaymentDTO.getAmount() * ratePerPeriod * Math.pow(1 + ratePerPeriod, totalInstallments))
				/ (Math.pow(1 + ratePerPeriod, totalInstallments) - 1);

		return emi;
	}

	private String getCurrentTimestampdate() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return now.format(formatter);
	}

	public static boolean is12DigitNumber(String accountNumber) {
		return accountNumber != null && accountNumber.matches("^\\d{12}$");
	}

}
