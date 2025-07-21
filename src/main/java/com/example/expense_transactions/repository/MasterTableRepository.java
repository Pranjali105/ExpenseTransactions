package com.example.expense_transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.expense_transactions.entity.ExpensesCategoryEntity;

import jakarta.transaction.Transactional;

public interface MasterTableRepository extends JpaRepository<ExpensesCategoryEntity, Integer>{

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO expenses_category(expenses_category_id, expenses_category_name)\r\n"
			+ "SELECT (SELECT IFNULL(MAX(expenses_category_id), 0) + 1 FROM expenses_category) as expenses_category_id, ?1\r\n"
			+ "FROM DUAL\r\n" + "WHERE NOT EXISTS (\r\n"
			+ "    SELECT 1 FROM expenses_category WHERE expenses_category_name = ?1)", nativeQuery = true)
	int addCategory(String category);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO expenses_sub_category(sub_category_id, expenses_category_id, sub_category_name)\r\n"
			+ "	SELECT (SELECT IFNULL(MAX(sub_category_id), 0) + 1 FROM expenses_sub_category) as sub_category_id, (select ec.expenses_category_id from expenses_category ec where ec.expenses_category_name = ?1), ?2\r\n"
			+ "	FROM DUAL WHERE NOT EXISTS \r\n"
			+ "		(SELECT 1 FROM expenses_category ec, expenses_sub_category esc WHERE ec.expenses_category_name = ?1 and esc.sub_category_name = ?2)", nativeQuery = true)
	int addSubCategory(String category, String subCategory);
	
}
