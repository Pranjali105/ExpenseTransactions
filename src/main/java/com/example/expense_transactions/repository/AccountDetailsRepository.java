package com.example.expense_transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.expense_transactions.dto.AccountDetailsDTO;
import com.example.expense_transactions.entity.AccountDetailsEntity;

import jakarta.transaction.Transactional;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetailsEntity, Integer> {

	@Query(value = "select ad.account_no, ad.account_holder_name, ad.bank_name, ad.balance from account_details ad where ad.account_no = ?1", nativeQuery = true)
	AccountDetailsDTO getAccountDetails(String accountNo);

	@Modifying
	@Transactional
	@Query(value = "update account_details ad set ad.balance = ?2 where ad.account_no = ?1", nativeQuery = true)
	int updateAccountDetails(String accountNo, double updatedBalance);

}
