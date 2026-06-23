package com.example.expense_transactions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.expense_transactions.dto.CreditDetailsDTO;
import com.example.expense_transactions.entity.CreditDetailsEntity;

import jakarta.transaction.Transactional;

@Repository
public interface CreditDetailsRepository extends JpaRepository<CreditDetailsEntity, Integer> {

	@Modifying
	@Transactional
	@Query(value = "insert into credit_details(account_no, amount, transaction_type) values ((select ad.account_no from account_details ad where ad.account_no = ?1), ?2, ?3)\r\n"
			+ "", nativeQuery = true)
	int addCreditDetails(String account_no, double amount, String transaction_type);

	@Query(value = "select DATE_FORMAT(cd.date, '%Y-%m-%d %H:%i:%s') as date, cd.account_no, cd.amount, cd.transaction_type, cd.transaction_status from credit_details cd where cd.account_no = ?1", nativeQuery = true)
	List<CreditDetailsDTO> getAllCreditDetailsRecords(String accountNo);

}
