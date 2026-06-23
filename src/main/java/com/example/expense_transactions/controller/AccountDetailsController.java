package com.example.expense_transactions.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_transactions.dto.AccountDetailsDTO;
import com.example.expense_transactions.dto.AccountPassbookDTO;
import com.example.expense_transactions.dto.CreditDetailsDTO;
import com.example.expense_transactions.service.AccountDetailsService;

@RestController
public class AccountDetailsController {
	
	@Autowired
	AccountDetailsService accountDetailsService;
	
	ResponseEntity<AccountDetailsDTO> accountDetailsDTOLst = null;
	
	ResponseEntity<String> addCreditDetails = null;

	ResponseEntity<List<AccountPassbookDTO>> accountPassbookResponseEntity = null;

	@GetMapping(value = "/getAccountDetails/{accountNo}")
	public ResponseEntity<AccountDetailsDTO> getAccountDetails(@PathVariable(required = true) String accountNo) {
		accountDetailsDTOLst = accountDetailsService.getAccountDetails(accountNo);
		
		return accountDetailsDTOLst;
		
	}
	
	@PostMapping(value = "/addCreditDetails")
	public ResponseEntity<String> addCreditDetails(@RequestBody CreditDetailsDTO creditDetailsDTO) {
		if (creditDetailsDTO != null) {
			addCreditDetails = accountDetailsService.addCreditDetails(creditDetailsDTO);
		}
		return addCreditDetails;
		
	}

	@PostMapping(value = "/getAllAccountTransactionRecords")
	public ResponseEntity<List<AccountPassbookDTO>> getAllAccountTransactionRecords(
			@RequestBody AccountDetailsDTO accountDetailsDTO) {

		if (accountDetailsDTO != null) {
			accountPassbookResponseEntity = accountDetailsService.getAllAccountTransactionRecords(accountDetailsDTO);
		}
		return accountPassbookResponseEntity;

	}

	@GetMapping(value = "/exportAllAccountTransactionRecords")
	public ResponseEntity<byte[]> exportAllAccountTransactionRecords(@RequestBody AccountDetailsDTO accountDetailsDTO)
			throws Exception {

		ResponseEntity<List<AccountPassbookDTO>> responseEntityAccountPassbookLst = null;

		List<AccountPassbookDTO> accountPassbookLst = null;

		if (accountDetailsDTO != null) {
			responseEntityAccountPassbookLst = accountDetailsService
					.exportAllAccountTransactionRecords(accountDetailsDTO);
		}

		accountPassbookLst = responseEntityAccountPassbookLst.getBody();

		// Create Excel workbook and sheet
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("ExpenseRecords");

		// Header row
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Serial No");
		header.createCell(1).setCellValue("Transaction Date");
		header.createCell(2).setCellValue("Transaction Description");
		header.createCell(3).setCellValue("Withdrwal Amount");
		header.createCell(4).setCellValue("Deposit Amount");
		header.createCell(5).setCellValue("Balance");

		// Data rows
		int rowNum = 1, serial_no = 0;

		for (AccountPassbookDTO accountPassbookDTO : accountPassbookLst) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(serial_no++);

			// Create a cell style for yyyy-MM-dd format
			CellStyle dateCellStyle = workbook.createCellStyle();
			CreationHelper creationHelper = workbook.getCreationHelper();
			dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));

			// Assuming trasactionsRecordsDTO.getDate() returns java.sql.Date
			String sqlDate = accountPassbookDTO.getTransaction_date();
			Cell dateCell = row.createCell(1);

			// Correct pattern: MM is Month, mm is Minute
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			LocalDateTime dateTime = LocalDateTime.parse(sqlDate, formatter);
			System.out.println("Parsed successfully: " + dateTime);

			dateCell.setCellValue(dateTime); // set the value
			dateCell.setCellStyle(dateCellStyle); // apply the format

			row.createCell(2).setCellValue(accountPassbookDTO.getTransaction_description());
			
			if(accountPassbookDTO.getWithdrwal_amount() != null)
			{
				row.createCell(3).setCellValue(accountPassbookDTO.getWithdrwal_amount());
				row.createCell(4).setCellType(CellType.BLANK);
			}
			else if(accountPassbookDTO.getDeposit_amount() != null)
			{
				row.createCell(4).setCellType(CellType.BLANK);
				row.createCell(4).setCellValue(accountPassbookDTO.getDeposit_amount());
			}
			
			row.createCell(5).setCellValue(accountPassbookDTO.getBalance());
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

		// Format current date as yyyy-MM-dd HH:mm:ss
		String currentDateTine = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String filename = "AccountPassbookRecords_" + accountDetailsDTO.getAccountNo() +"_"+ currentDateTine + ".xlsx";

		// Set Content-Disposition header
		headers.setContentDisposition(ContentDisposition.attachment().filename(filename).build());

		return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);

	}
}
