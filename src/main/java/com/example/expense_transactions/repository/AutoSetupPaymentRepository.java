package com.example.expense_transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.expense_transactions.dto.AutoSetupPaymentDTO;
import com.example.expense_transactions.dto.LoanDetailsDTO;
import com.example.expense_transactions.entity.AutoSetupPaymentEntity;

import jakarta.transaction.Transactional;

@Repository
public interface AutoSetupPaymentRepository extends JpaRepository<AutoSetupPaymentEntity, Integer> {

	@Query(value = "select ld.sub_category_name, ld.bank_name, ld.rate_of_interest from loan_details ld where ld.sub_category_name = ?1 and ld.bank_name = ?2", nativeQuery = true)
	LoanDetailsDTO getLoanDetails(String subCategoryName, String bankName);

	@Modifying
	@Transactional
	@Query(value = "insert into auto_setup_payment(category_name, sub_category_name, account_no, bank_name, amount, tenure, rate_of_interest, frequency, monthly_emi_amount, remaining_emi_amount, total_installment_count, remaining_installment_count, payment_mode, payment_mode_type, by_whom, start_date, end_date, next_emi_date, transaction_date, loan_status) values (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16, ?17, ?18, ?19, ?20)", nativeQuery = true)
	int addSetupAutoPaymentDetails(String categoryName, String subCategoryName, String accountNo, String bankName,
			Double amount, Integer tenure, Double rateOfInterest, String frequency, Double monthlyEmiAmount,
			Double remainingEmiAmount, Integer totalInstallmentCount, Integer remainingInstallmentCount,
			String paymentMode, String paymentModeType, String byWhom, String startDate, String endDate, String nextEmiDate,
			String transactionDate, String loanStatus);

	@Query("SELECT new com.example.expense_transactions.dto.AutoSetupPaymentDTO(a.categoryName,a.subCategoryName,a.accountNo,a.bankName,a.amount,a.tenure,a.rateOfInterest,a.frequency,a.monthlyEmiAmount,a.remainingEmiAmount,a.totalInstallmentCount,a.remainingInstallmentCount,a.paymentMode,a.paymentModeType,a.byWhom,a.startDate,a.endDate,a.nextEmiDate,a.transactionDate,a.loanStatus) FROM AutoSetupPaymentEntity a WHERE a.id = (SELECT MAX(b.id) FROM AutoSetupPaymentEntity b WHERE b.categoryName = ?1 AND b.subCategoryName = ?2 AND  b.accountNo = ?3)")
	AutoSetupPaymentDTO getAutoSetupPayment(String categoryName, String subCategoryName, String accountNo);

}
