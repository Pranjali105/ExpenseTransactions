package com.example.expense_transactions.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_transactions.dto.ExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.dto.TotalExpenseTrasactionsRecordsDTO;
import com.example.expense_transactions.service.ExpenseTrasactionsRecordsService;

@RestController
public class ExpenseTrasactionsRecordsController {

	@Autowired
	ExpenseTrasactionsRecordsService expenseTrasactionsRecordsService;

	@GetMapping("/getExpenseTrasactionsRecords")
	public ResponseEntity<List<ExpenseTrasactionsRecordsDTO>> getExpenseTrasactionsRecords() {
		return expenseTrasactionsRecordsService.getExpenseTrasactionsRecords();
	}

	@PostMapping(value = "/addExpenseTrasactionsRecords", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addExpenseTrasactionsRecords(
			@RequestBody ExpenseTrasactionsRecordsDTO expenseTrasactionsRecordsDTO) {
		ResponseEntity<String> expenseTrasactionsRecordsDTOObj = null;

		if (expenseTrasactionsRecordsDTO != null) {
			expenseTrasactionsRecordsDTOObj = expenseTrasactionsRecordsService
					.addExpenseTrasactionsRecords(expenseTrasactionsRecordsDTO);
		}

		return expenseTrasactionsRecordsDTOObj;
	}

	@PutMapping(value = "/updateExpenseTrasactionsRecords/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateExpenseTrasactionsRecords(@PathVariable(required = true) int id,
			@RequestBody ExpenseTrasactionsRecordsDTO expenseTrasactionsRecordsDTO) {

		ResponseEntity<String> expenseTrasactionsRecordsDTOObj = null;

		if (expenseTrasactionsRecordsDTO != null) {
			expenseTrasactionsRecordsDTOObj = expenseTrasactionsRecordsService.updateExpenseTrasactionsRecords(id,
					expenseTrasactionsRecordsDTO);
		}
		return expenseTrasactionsRecordsDTOObj;
	}

	@GetMapping("/exportExpenseTrasactionsRecords")
	public ResponseEntity<byte[]> exportExpenseTrasactionsRecords() throws Exception {

		ResponseEntity<List<ExpenseTrasactionsRecordsDTO>> expenseTrasactionsRecordsDTOLst = expenseTrasactionsRecordsService
				.exportExpenseTrasactionsRecords();

		List<ExpenseTrasactionsRecordsDTO> recordsDTOs = expenseTrasactionsRecordsDTOLst.getBody();

		// Create Excel workbook and sheet
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("ExpenseRecords");

		// Header row
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Serial No");
		header.createCell(1).setCellValue("Date");
		header.createCell(2).setCellValue("Expense");
		header.createCell(3).setCellValue("Type");
		header.createCell(4).setCellValue("Amount");
		header.createCell(5).setCellValue("Payment Mode");
		header.createCell(6).setCellValue("Payment Mode Type");
		header.createCell(7).setCellValue("Paid By");

		// Data rows
		int rowNum = 1;
		for (ExpenseTrasactionsRecordsDTO trasactionsRecordsDTO : recordsDTOs) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(trasactionsRecordsDTO.getRow_no());

			// Create a cell style for yyyy-MM-dd format
			CellStyle dateCellStyle = workbook.createCellStyle();
			CreationHelper creationHelper = workbook.getCreationHelper();
			dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));

			// Assuming trasactionsRecordsDTO.getDate() returns java.sql.Date
			Date sqlDate = trasactionsRecordsDTO.getDate(); // java.sql.Date
			Cell dateCell = row.createCell(1);
			dateCell.setCellValue(sqlDate); // set the value
			dateCell.setCellStyle(dateCellStyle); // apply the format

			row.createCell(2).setCellValue(trasactionsRecordsDTO.getExpense());
			row.createCell(3).setCellValue(trasactionsRecordsDTO.getType());
			row.createCell(4).setCellValue(trasactionsRecordsDTO.getAmount());
			row.createCell(5).setCellValue(trasactionsRecordsDTO.getPayment_mode());
			row.createCell(6).setCellValue(trasactionsRecordsDTO.getPayment_mode_type());
			row.createCell(7).setCellValue(trasactionsRecordsDTO.getBy_whom());
		}

		// Write Excel to byte array
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		workbook.write(bos);
		workbook.close();
		byte[] excelBytes = bos.toByteArray();

		// Prepare response
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));

		// Format current date as yyyy-MM-dd
		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String filename = "ExpenseRecords_" + currentDate + ".xlsx";

		// Set Content-Disposition header
		headers.setContentDisposition(ContentDisposition.attachment().filename(filename).build());

		return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
	}

	@PostMapping(value = "/getTotalExpenseTrasactionsRecords/{expense_category}/{month}/{year}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TotalExpenseTrasactionsRecordsDTO>> getTotalExpenseTrasactionsRecords(
			@PathVariable(required = true) String expense_category, @PathVariable(required = true) String month,
			@PathVariable(required = true) int year, @RequestParam(required = false) String expense_sub_category,
			@RequestParam(required = false) String Paid_by) {

		ResponseEntity<List<TotalExpenseTrasactionsRecordsDTO>> totalExpenseTrasactionsRecordsDTODTOLst = expenseTrasactionsRecordsService
				.getTotalExpenseTrasactionsRecords(expense_category, month, year, expense_sub_category, Paid_by);

		return totalExpenseTrasactionsRecordsDTODTOLst;
	}

}
