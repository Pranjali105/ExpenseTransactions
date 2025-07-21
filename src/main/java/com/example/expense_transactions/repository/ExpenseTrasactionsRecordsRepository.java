package com.example.expense_transactions.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.expense_transactions.dto.DateDTO;
import com.example.expense_transactions.dto.ExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.dto.TotalExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.entity.ExpenseTrasactionsRecordsEntity;

import jakarta.transaction.Transactional;

public interface ExpenseTrasactionsRecordsRepository extends JpaRepository<ExpenseTrasactionsRecordsEntity, Integer> {

	@Query(value = "SELECT etr.id, etr.date, etr.expenses_category_name, etr.sub_category_name, etr.amount, etr.payment_mode_name, etr.type_name, etr.by_whom\r\n"
			+ "FROM expense_trasactions_records etr,expenses_category ec,expenses_sub_category esc,payment_mode pm, payment_mode_type pmt,paid_by pb\r\n"
			+ "where etr.expenses_category_name = ec.expenses_category_name \r\n"
			+ "and ec.expenses_category_id = esc.expenses_category_id and etr.sub_category_name = esc.sub_category_name \r\n"
			+ "and etr.payment_mode_name = pm.payment_mode_name \r\n"
			+ "and pm.payment_mode_id = pmt.payment_mode_id and etr.type_name = pmt.type_name\r\n"
			+ "and etr.by_whom = pb.by_whom ORDER BY etr.date desc", nativeQuery = true)
	List<ExpenseTrasactionsRecordsDTO> getExpenseTrasactionsRecords();

	@Modifying
	@Transactional
	@Query(value = "insert into expense_trasactions_records(date,expenses_category_name, sub_category_name, amount, payment_mode_name, type_name, by_whom) values (cast(?1 as date), (select ec.expenses_category_name from expenses_category ec where ec.expenses_category_name = ?2), (select esc.sub_category_name from expenses_sub_category esc, expenses_category ec where ec.expenses_category_name = ?2 && ec.expenses_category_id = esc.expenses_category_id && esc.sub_category_name = ?3), ?4, (select pm.payment_mode_name from payment_mode pm where pm.payment_mode_name = ?5), (select pmt.type_name from payment_mode_type pmt, payment_mode pm where pm.payment_mode_name = ?5 && pmt.payment_mode_id=pm.payment_mode_id && pmt.type_name = ?6), (select pb.by_whom from paid_by pb where pb.by_whom = ?7))", nativeQuery = true)
	int addExpenseTrasactionsRecords(Date date, String expense, String type, double amount, String payment_mode,
			String payment_mode_type, String by_whom);

	@Modifying
	@Transactional
	@Query(value = "update expense_trasactions_records etr set etr.date = cast(?2 as date), etr.expenses_category_name = (SELECT ec.expenses_category_name FROM expenses_category ec where ec.expenses_category_name = ?3), etr.sub_category_name = (select esc.sub_category_name from expenses_sub_category esc, expenses_category ec where ec.expenses_category_name = ?3 && ec.expenses_category_id = esc.expenses_category_id && esc.sub_category_name = ?4), etr.amount = ?5, etr.payment_mode_name = (select pm.payment_mode_name from payment_mode pm where pm.payment_mode_name = ?6), etr.type_name = (select pmt.type_name from payment_mode_type pmt, payment_mode pm where pm.payment_mode_name = ?6 && pmt.payment_mode_id=pm.payment_mode_id && pmt.type_name = ?7), etr.by_whom = (select pb.by_whom from paid_by pb where pb.by_whom = ?8) where etr.id = ?1", nativeQuery = true)
	int updateExpenseTrasactionsRecords(int id, Date date, String expense, String type, double amount,
			String payment_mode, String payment_mode_type, String by_whom);

	@Query(value = "SELECT etr.id, ROW_NUMBER() OVER (ORDER BY etr.date desc) as row_no, cast(etr.date as date), etr.expenses_category_name, etr.sub_category_name, etr.amount, etr.payment_mode_name, etr.type_name, etr.by_whom\r\n"
			+ "FROM expense_trasactions_records etr,expenses_category ec,expenses_sub_category esc,payment_mode pm, payment_mode_type pmt,paid_by pb\r\n"
			+ "where etr.expenses_category_name = ec.expenses_category_name \r\n"
			+ "and ec.expenses_category_id = esc.expenses_category_id and etr.sub_category_name = esc.sub_category_name \r\n"
			+ "and etr.payment_mode_name = pm.payment_mode_name \r\n"
			+ "and pm.payment_mode_id = pmt.payment_mode_id and etr.type_name = pmt.type_name\r\n"
			+ "and etr.by_whom = pb.by_whom ORDER BY etr.date desc", nativeQuery = true)
	List<ExpenseTrasactionsRecordsDTO> getExportExpenseTrasactionsRecords();

	@Query(value = "SELECT STR_TO_DATE(CONCAT(?2, '-', ?1, '-01'), '%Y-%m-%d') AS month_start, LAST_DAY(STR_TO_DATE(CONCAT(?2, '-', ?1, '-01'), '%Y-%m-%d')) AS month_end ", nativeQuery = true)
	DateDTO getDateStartEndDate(String month, int year);

	@Query(value = "SELECT etr.expenses_category_name, etr.sub_category_name, sum(etr.amount), etr.by_whom\r\n"
			+ "FROM expense_trasactions_records etr\r\n"
			+ "WHERE date BETWEEN ?2 AND ?3 and etr.expenses_category_name = ?1 and etr.by_whom = ?4 group by ?1, ?4, etr.amount order by ?1;", nativeQuery = true)
	List<TotalExpenseTrasactionsRecordsDTO> getTotalExpenseTrasactionsRecordsByExpenseCategoryAndPaidBy(
			String expense_category, Date start_date, Date end_date, String by_whom);

	@Query(value = "SELECT etr.expenses_category_name, etr.sub_category_name, sum(etr.amount), etr.by_whom\r\n"
			+ "FROM expense_trasactions_records etr\r\n"
			+ "WHERE date BETWEEN ?3 AND ?4 and etr.expenses_category_name = ?1 and etr.sub_category_name = ?2 and etr.by_whom = ?5 group by ?1, ?2, ?5, etr.amount order by ?1;", nativeQuery = true)
	List<TotalExpenseTrasactionsRecordsDTO> getTotalExpenseTrasactionsRecordsByExpenseCategoryAndExpenseSubCategoryAndPaidBy(
			String expense_category, String expense_sub_category, Date start_date, Date end_date, String by_whom);

	@Query(value = "SELECT etr.expenses_category_name, etr.sub_category_name, sum(etr.amount)\r\n"
			+ "FROM expense_trasactions_records etr\r\n"
			+ "WHERE date BETWEEN ?2 AND ?3 and etr.expenses_category_name = ?1 group by ?1, etr.amount order by ?1;", nativeQuery = true)
	List<TotalExpenseTrasactionsRecordsDTO> getTotalExpenseTrasactionsRecordsByExpenseCategory(String expense_category,
			Date start_date, Date end_date);

	@Query(value = "SELECT etr.expenses_category_name, etr.sub_category_name, sum(etr.amount)\r\n"
			+ "FROM expense_trasactions_records etr\r\n"
			+ "WHERE date BETWEEN ?3 AND ?4 and etr.expenses_category_name = ?1 and etr.sub_category_name = ?2 group by ?1, ?2", nativeQuery = true)
	List<TotalExpenseTrasactionsRecordsDTO> getTotalExpenseTrasactionsRecordsByExpenseCategoryAndExpenseSubCategory(
			String expense_category, String expense_sub_category, Date start_date, Date end_date);

	@Query(value = "SELECT MONTH(STR_TO_DATE(?1, '%M')) AS month_number", nativeQuery = true)
	String getMonthNumber(String month);

	

}
