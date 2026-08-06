package com.example.expense_transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.expense_transactions.dto.LoanDetailsDTO;
import com.example.expense_transactions.entity.AutoSetupPaymentEntity;

import jakarta.transaction.Transactional;

@Repository
public interface AutoSetupPaymentRepository extends JpaRepository<AutoSetupPaymentEntity, Integer> {

	@Query(value = "select ld.sub_category_name, ld.bank_name, ld.rate_of_interest from loan_details ld where ld.sub_category_name = ?1 and ld.bank_name = ?2", nativeQuery = true)
	LoanDetailsDTO getLoanDetails(String subCategoryName, String bankName);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO auto_setup_payment(category_name, sub_category_name, account_no, bank_name, amount, total_installment, rate_of_interest, frequency, emi_amount, remaining_installment, remaining_amount, payment_mode, payment_mode_type, by_whom, start_date, end_date, transaction_date, loan_status)\r\n"
			+ "SELECT ?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16, ?17, ?18\r\n"
			+ "FROM DUAL\r\n" + "WHERE NOT EXISTS (\r\n"
			+ "    SELECT 1 FROM auto_setup_payment WHERE category_name = ?1 AND sub_category_name = ?2 AND account_no = ?3 AND bank_name = ?4 AND transaction_date = ?17)", nativeQuery = true)
	int addSetupAutoPayment(String categoryName, String subCategoryName, String accountNo, String bankName,
			double amount, int totalInstallment, double rateOfInterest, String frequency, double emiAmount,
			int remainingInstallment, double remainingAmount, String paymentMode, String paymentModeType, String byWhom,
			String startDate, String endDate, String transactionDate, String loanStatus);
	
}
