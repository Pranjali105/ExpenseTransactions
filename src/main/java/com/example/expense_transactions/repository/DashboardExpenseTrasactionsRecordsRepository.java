package com.example.expense_transactions.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.expense_transactions.dto.DashboardExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.dto.DateDTO;
import com.example.expense_transactions.entity.ExpenseTrasactionsRecordsEntity;

public interface DashboardExpenseTrasactionsRecordsRepository
		extends JpaRepository<ExpenseTrasactionsRecordsEntity, Integer> {

	@Query(value = "SELECT MONTH(STR_TO_DATE(?1, '%M')) AS month_number", nativeQuery = true)
	String getMonthNumber(String month);

	@Query(value = "SELECT STR_TO_DATE(CONCAT(?2, '-', ?1, '-01'), '%Y-%m-%d') AS start_date, LAST_DAY(STR_TO_DATE(CONCAT(?2, '-', ?1, '-01'), '%Y-%m-%d')) AS end_date ", nativeQuery = true)
	DateDTO geteStartEndDateFromMonthAndYear(String month_no, int year);

	@Query(value = "SELECT DATE(CONCAT(?1, '-01-01')) AS start_date, LAST_DAY(CONCAT(?1, '-12-01')) AS end_date", nativeQuery = true)
	DateDTO getStartEndDateFromYear(int year);

	@Query(value = "SELECT etr.expenses_category_name, etr.sub_category_name, sum(etr.amount)\r\n"
			+ "FROM expense_trasactions_records etr\r\n"
			+ "WHERE etr.date BETWEEN ?2 AND ?3 and etr.expenses_category_name = ?1 and etr.by_whom = ?4 group by etr.sub_category_name order by ?1", nativeQuery = true)
	List<DashboardExpenseTrasactionsRecordsDTO> getTotalExpenseTrasactionsRecordsByExpenseCategoryAndPaidBy(
			String expense_category, Date start_date, Date end_date, String by_whom);

	@Query(value = "SELECT etr.expenses_category_name, etr.sub_category_name, sum(etr.amount)\r\n"
			+ "FROM expense_trasactions_records etr\r\n"
			+ "WHERE etr.date BETWEEN ?2 AND ?3 and etr.expenses_category_name = ?1 group by etr.sub_category_name order by ?1", nativeQuery = true)
	List<DashboardExpenseTrasactionsRecordsDTO> getTotalExpenseTrasactionsRecordsByExpenseCategory(
			String expense_category, Date start_date, Date end_date);

	@Query(value = "select etr.expenses_category_name, sum(etr.amount) from expense_trasactions_records etr where etr.date between ?1 and ?2 and etr.by_whom = ?3 group by etr.expenses_category_name", nativeQuery = true)
	List<DashboardExpenseTrasactionsRecordsDTO> getTotalExpenseTrasactionsRecordsByPaidBy(Date start_date, Date end_date, String paid_by);

	@Query(value = "select etr.expenses_category_name, sum(etr.amount) from expense_trasactions_records etr where etr.date between ?1 and ?2 group by etr.expenses_category_name", nativeQuery = true)
	List<DashboardExpenseTrasactionsRecordsDTO> getTotalExpenseTrasactionsRecords(Date start_date, Date end_date);

}
