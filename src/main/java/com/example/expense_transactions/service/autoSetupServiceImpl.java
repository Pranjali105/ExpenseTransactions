package com.example.expense_transactions.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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

	@Override
	public ResponseEntity<String> addSetupAutoPaymentDetails(AutoSetupPaymentDTO autoSetupPaymentDTO) {

		AccountDetailsDTO accountDetailsDTOLst = null;
		LoanDetailsDTO loanDetailsDTO = null;
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

					autoSetupPaymentDTO.setStartDate(getCurrentTimestampdate());

					autoSetupPaymentDTO.setTransactionDate(getCurrentTimestampdate());

					if (autoSetupPaymentDTO.getFrequency().toUpperCase().equalsIgnoreCase("ONETIME")) {
						autoSetupPaymentDTO.setTenure(0);
						autoSetupPaymentDTO.setRateOfInterest(0.0);
						autoSetupPaymentDTO.setMonthlyEmiAmount(0.0);
						autoSetupPaymentDTO.setRemainingEmiAmount(0.0);
						autoSetupPaymentDTO.setTotalInstallmentCount(0);
						autoSetupPaymentDTO.setRemainingInstallmentCount(0);
						autoSetupPaymentDTO.setNextEmiDate("0000-00-00 00:00:00");
						autoSetupPaymentDTO.setEndDate(getCurrentTimestampdate());

						autoSetupPaymentDTO.setLoanStatus("Closed");

					} else {
						if ("monthly".equalsIgnoreCase(autoSetupPaymentDTO.getFrequency())
								|| "yearly".equalsIgnoreCase(autoSetupPaymentDTO.getFrequency())
								|| "quarterly".equalsIgnoreCase(autoSetupPaymentDTO.getFrequency())) {

							autoSetupPaymentDTO.setLoanStatus("Opened");

							EMICalculator(autoSetupPaymentDTO);

							System.out.println("Start Date: " + autoSetupPaymentDTO.getStartDate());

							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
							LocalDate startDate = LocalDateTime.parse(autoSetupPaymentDTO.getStartDate(), formatter)
									.toLocalDate();

							LocalDate endDate = null;
							
							if (autoSetupPaymentDTO.getFrequency().equalsIgnoreCase("Monthly")) {

								autoSetupPaymentDTO.setNextEmiDate(
										LocalDateTime.parse(autoSetupPaymentDTO.getStartDate(), formatter).toLocalDate()
												.plusMonths(1).toString());

								endDate = startDate.plusMonths(autoSetupPaymentDTO.getTenure());
							} else if (autoSetupPaymentDTO.getFrequency().equalsIgnoreCase("Quarterly")) {

								autoSetupPaymentDTO.setNextEmiDate(
										LocalDateTime.parse(autoSetupPaymentDTO.getStartDate(), formatter).toLocalDate()
												.plusMonths(3).toString());

								endDate = startDate.plusMonths(autoSetupPaymentDTO.getTenure() * 3);
							} else if (autoSetupPaymentDTO.getFrequency().equalsIgnoreCase("Yearly")) {

								autoSetupPaymentDTO.setNextEmiDate(
										LocalDateTime.parse(autoSetupPaymentDTO.getStartDate(), formatter).toLocalDate()
												.plusYears(1).toString());

								endDate = startDate.plusYears(autoSetupPaymentDTO.getTenure());
							}

							// need to correct the date format to match the startDate format
							System.out.println("End Date: " + endDate);

							LocalDateTime endDateTime = endDate.atStartOfDay();

							System.out.println("End Date Time: " + endDateTime.toString());

							autoSetupPaymentDTO.setEndDate(endDateTime.toString());

						}

					}

					int n = autoSetupPaymentRepository.addSetupAutoPaymentDetails(autoSetupPaymentDTO.getCategoryName(),
							autoSetupPaymentDTO.getSubCategoryName(), autoSetupPaymentDTO.getAccountNo(),
							autoSetupPaymentDTO.getBankName(), autoSetupPaymentDTO.getAmount(),
							autoSetupPaymentDTO.getTenure(), autoSetupPaymentDTO.getRateOfInterest(),
							autoSetupPaymentDTO.getFrequency(), autoSetupPaymentDTO.getMonthlyEmiAmount(),
							autoSetupPaymentDTO.getRemainingEmiAmount(), autoSetupPaymentDTO.getTotalInstallmentCount(),
							autoSetupPaymentDTO.getRemainingInstallmentCount(), autoSetupPaymentDTO.getPaymentMode(),
							autoSetupPaymentDTO.getPaymentModeType(), autoSetupPaymentDTO.getByWhom(),
							autoSetupPaymentDTO.getStartDate(), autoSetupPaymentDTO.getEndDate(), autoSetupPaymentDTO.getNextEmiDate(),
							autoSetupPaymentDTO.getTransactionDate(), autoSetupPaymentDTO.getLoanStatus());

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

		return ResponseEntity.ok("Data inserted successfully");

	}

	private String RoundUpDoubleValue(double amount) {
		DecimalFormat df = new DecimalFormat("0.00");

		// Set rounding mode to UP (always rounds away from zero)
		df.setRoundingMode(RoundingMode.UP);

		String result = df.format(amount);

		System.out.println(Double.parseDouble(result));
		return result;
	}

	private void EMICalculator(AutoSetupPaymentDTO autoSetupPaymentDTO) {

		/// 3. Convert duration to years
		double timeInYears = 0.0;
		double principal = autoSetupPaymentDTO.getAmount();
		double annualRate = autoSetupPaymentDTO.getRateOfInterest();
		double originalTime = autoSetupPaymentDTO.getTenure();
		int totalInstallmentsCount = 0;
		double installmentPayment = 0.0;
		Double remianingAmont;

		switch (autoSetupPaymentDTO.getFrequency().toUpperCase()) {
		case "MONTHLY":
			timeInYears = originalTime / 12.0;
			totalInstallmentsCount = (int) originalTime * 12; // Total number of monthly installments
			break;
		case "QUARTERLY":
			timeInYears = originalTime / 4.0;
			totalInstallmentsCount = (int) originalTime * 4; // Total number of quarterly installments
			break;
		case "YEARLY":
			timeInYears = originalTime;
			totalInstallmentsCount = (int) originalTime; // Total number of yearly installments
			break;
		default:
			System.out.println("Invalid choice. Defaulting to Years.");
			timeInYears = autoSetupPaymentDTO.getTenure();
			break;
		}

		// 4. Calculate total simple interest and total payment
		double simpleInterest = (principal * annualRate * timeInYears) / 100.0;

		double totalAmount = principal + simpleInterest;

		if (autoSetupPaymentDTO.getFrequency().toUpperCase().equalsIgnoreCase("MONTHLY")) {

			installmentPayment = totalAmount / totalInstallmentsCount;

		} else if (autoSetupPaymentDTO.getFrequency().toUpperCase().equalsIgnoreCase("QUARTERLY")) {

			installmentPayment = totalAmount / totalInstallmentsCount;

		} else if (autoSetupPaymentDTO.getFrequency().toUpperCase().equalsIgnoreCase("YEARLY")) {

			installmentPayment = totalAmount / totalInstallmentsCount;
		}

		if (autoSetupPaymentDTO.getLoanStatus().equalsIgnoreCase("Opened")) {

			autoSetupPaymentDTO.setTotalInstallmentCount(totalInstallmentsCount);
			autoSetupPaymentDTO.setRemainingInstallmentCount(totalInstallmentsCount);

			autoSetupPaymentDTO.setRemainingEmiAmount(totalAmount);

			String installmentPaymentString = RoundUpDoubleValue(installmentPayment);

			autoSetupPaymentDTO.setMonthlyEmiAmount(Double.parseDouble(installmentPaymentString));

		} else if (autoSetupPaymentDTO.getLoanStatus().equalsIgnoreCase("In Progress")) {
			String installmentPaymentString = RoundUpDoubleValue(installmentPayment);

			remianingAmont = autoSetupPaymentDTO.getRemainingEmiAmount()
					- (Double.parseDouble(installmentPaymentString));

			String remainingAmountStr = RoundUpDoubleValue(remianingAmont);

			if (remianingAmont <= 0) {
				autoSetupPaymentDTO.setRemainingEmiAmount(0.0);
			} else {
				autoSetupPaymentDTO.setRemainingEmiAmount(Double.parseDouble(remainingAmountStr));
			}

			if (autoSetupPaymentDTO.getRemainingInstallmentCount() == 0) {
				autoSetupPaymentDTO.setRemainingInstallmentCount(0);
			} else {
				autoSetupPaymentDTO
						.setRemainingInstallmentCount(autoSetupPaymentDTO.getRemainingInstallmentCount() - 1);
			}
		}
	}

	private String getCurrentTimestampdate() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return now.format(formatter);
	}

	public static boolean is12DigitNumber(String accountNumber) {
		return accountNumber != null && accountNumber.matches("^\\d{12}$");
	}

	@Override
	public ResponseEntity<String> addSetupAutoPayment(AutoSetupPaymentDTO autoSetupPaymentDTO) {

		autoSetupPaymentDTO = autoSetupPaymentRepository.getAutoSetupPayment(autoSetupPaymentDTO.getCategoryName(),
				autoSetupPaymentDTO.getSubCategoryName(), autoSetupPaymentDTO.getAccountNo());

		if (autoSetupPaymentDTO != null) {

			if (autoSetupPaymentDTO.getRemainingEmiAmount() != null) {

				autoSetupPaymentDTO.setTransactionDate(getCurrentTimestampdate());

				if (autoSetupPaymentDTO.getRemainingInstallmentCount() == 0) {
					autoSetupPaymentDTO.setLoanStatus("Closed");
					autoSetupPaymentDTO.setMonthlyEmiAmount(0.0);
					autoSetupPaymentDTO.setRemainingEmiAmount(0.0);
					autoSetupPaymentDTO.setNextEmiDate("0000-00-00 00:00:00");
				} else {
					autoSetupPaymentDTO.setLoanStatus("In Progress");
					EMICalculator(autoSetupPaymentDTO);
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					if (autoSetupPaymentDTO.getFrequency().equalsIgnoreCase("Monthly")) {

						autoSetupPaymentDTO.setNextEmiDate(
								LocalDateTime.parse(autoSetupPaymentDTO.getNextEmiDate(), formatter).toLocalDate()
										.plusMonths(1).toString());

						
					} else if (autoSetupPaymentDTO.getFrequency().equalsIgnoreCase("Quarterly")) {

						autoSetupPaymentDTO.setNextEmiDate(
								LocalDateTime.parse(autoSetupPaymentDTO.getNextEmiDate(), formatter).toLocalDate()
										.plusMonths(3).toString());

						
					} else if (autoSetupPaymentDTO.getFrequency().equalsIgnoreCase("Yearly")) {

						autoSetupPaymentDTO.setNextEmiDate(
								LocalDateTime.parse(autoSetupPaymentDTO.getNextEmiDate(), formatter).toLocalDate()
										.plusYears(1).toString());

						
					}
					
				}

			}

			int n = autoSetupPaymentRepository.addSetupAutoPaymentDetails(autoSetupPaymentDTO.getCategoryName(),
					autoSetupPaymentDTO.getSubCategoryName(), autoSetupPaymentDTO.getAccountNo(),
					autoSetupPaymentDTO.getBankName(), autoSetupPaymentDTO.getAmount(), autoSetupPaymentDTO.getTenure(),
					autoSetupPaymentDTO.getRateOfInterest(), autoSetupPaymentDTO.getFrequency(),
					autoSetupPaymentDTO.getMonthlyEmiAmount(), autoSetupPaymentDTO.getRemainingEmiAmount(),
					autoSetupPaymentDTO.getTotalInstallmentCount(), autoSetupPaymentDTO.getRemainingInstallmentCount(),
					autoSetupPaymentDTO.getPaymentMode(), autoSetupPaymentDTO.getPaymentModeType(),
					autoSetupPaymentDTO.getByWhom(), autoSetupPaymentDTO.getStartDate(),
					autoSetupPaymentDTO.getEndDate(), autoSetupPaymentDTO.getNextEmiDate() ,autoSetupPaymentDTO.getTransactionDate(),
					autoSetupPaymentDTO.getLoanStatus());

			if (n == 0) {
				return ResponseEntity.ok("Error occured while inserting the data");
			}
		} else {
			return ResponseEntity
					.ok("No auto setup payment details found for the given category, subcategory, and account number");
		}

		return ResponseEntity.ok("Data inserted successfully");
	}

}
