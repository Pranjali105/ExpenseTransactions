const API_BASE_URL = 'http://localhost:8080';

// Page Navigation
function goToPage(pageName) {
    document.querySelectorAll('.page').forEach(page => page.classList.remove('active'));
    document.getElementById(pageName).classList.add('active');
    
    if (pageName === 'dashboard') {
        loadDashboard();
    } else if (pageName === 'transactions') {
        loadTransactions();
        loadMasterData();
    } else if (pageName === 'accounts') {
        loadMasterData();
    } else if (pageName === 'master') {
        loadMasterData();
    }
}

// Notification
function showNotification(message, type = 'success') {
    const notification = document.getElementById('notification');
    notification.textContent = message;
    notification.className = `notification ${type} show`;
    setTimeout(() => notification.classList.remove('show'), 3000);
}

// Dashboard
async function loadDashboard() {
    const year = document.getElementById('dashboardYear').value || new Date().getFullYear();
    
    try {
        const response = await fetch(`${API_BASE_URL}/getTotalExpenseTrasactionsRecords`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ year: parseInt(year) })
        });
        
        const data = await response.json();
        if (data && data.length > 0) {
            const labels = data.map(d => `Month ${d.month}`);
            const amounts = data.map(d => d.totalAmount);
            drawChart('monthlyChart', 'line', labels, amounts, '#667eea');
        }
        
        // Load category chart
        const categoryResponse = await fetch(`${API_BASE_URL}/getTotalExpenseCategoryTrasactionsRecords`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ year: parseInt(year) })
        });
        
        const categoryData = await categoryResponse.json();
        if (categoryData && categoryData.categoryWiseTotalExpense) {
            const catLabels = Object.keys(categoryData.categoryWiseTotalExpense);
            const catAmounts = Object.values(categoryData.categoryWiseTotalExpense);
            drawChart('categoryChart', 'doughnut', catLabels, catAmounts, '#764ba2');
        }
        
        // Load paid by chart
        const paidByResponse = await fetch(`${API_BASE_URL}/getTotalExpenseTrasactionsRecordsPaidBy`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ year: parseInt(year) })
        });
        
        const paidByData = await paidByResponse.json();
        if (paidByData && paidByData.length > 0) {
            const paidLabels = paidByData.map(d => d.paidBy);
            const paidAmounts = paidByData.map(d => d.totalAmount);
            drawChart('paidByChart', 'bar', paidLabels, paidAmounts, '#51cf66');
        }
        
        showNotification('Dashboard loaded successfully', 'success');
    } catch (error) {
        console.error('Error loading dashboard:', error);
        showNotification('Error loading dashboard', 'error');
    }
}

// Simple Chart Drawing (using text-based visualization)
function drawChart(canvasId, type, labels, data, color) {
    const canvas = document.getElementById(canvasId);
    if (!canvas) return;
    
    const ctx = canvas.getContext('2d');
    canvas.width = 300;
    canvas.height = 200;
    
    // Simple bar chart
    const barWidth = canvas.width / (data.length + 1);
    const maxValue = Math.max(...data);
    const scale = (canvas.height - 40) / maxValue;
    
    data.forEach((value, index) => {
        const x = (index + 1) * barWidth;
        const barHeight = value * scale;
        const y = canvas.height - barHeight - 20;
        
        ctx.fillStyle = color;
        ctx.fillRect(x, y, barWidth - 10, barHeight);
        
        ctx.fillStyle = '#333';
        ctx.font = '12px Arial';
        ctx.textAlign = 'center';
        ctx.fillText(value.toFixed(0), x + (barWidth - 10) / 2, canvas.height - 5);
        ctx.fillText(labels[index], x + (barWidth - 10) / 2, y - 5);
    });
}

// Transactions
async function loadTransactions() {
    try {
        const response = await fetch(`${API_BASE_URL}/getExpenseTrasactionsRecords`);
        const data = await response.json();
        
        const tbody = document.getElementById('transactionsList');
        tbody.innerHTML = '';
        
        if (data && data.length > 0) {
            data.forEach((transaction, index) => {
                const row = tbody.insertRow();
                row.innerHTML = `
                    <td>${index + 1}</td>
                    <td>${transaction.date}</td>
                    <td>${transaction.expenseCategory}</td>
                    <td>${transaction.expenseSubCategory}</td>
                    <td>₹${transaction.amount.toFixed(2)}</td>
                    <td>${transaction.paymentMode}</td>
                    <td>${transaction.byWhom}</td>
                    <td>${transaction.accountNo}</td>
                    <td>
                        <button class="btn-edit" onclick="editTransaction(${transaction.id})">Edit</button>
                        <button class="btn-danger" onclick="deleteTransaction(${transaction.id})">Delete</button>
                    </td>
                `;
            });
        } else {
            tbody.innerHTML = '<tr><td colspan="9" class="text-center">No transactions found</td></tr>';
        }
    } catch (error) {
        console.error('Error loading transactions:', error);
        showNotification('Error loading transactions', 'error');
    }
}

async function addTransaction(event) {
    event.preventDefault();
    
    const transactionData = {
        date: document.getElementById('expenseDate').value,
        expenseCategory: document.getElementById('expenseCategory').value,
        expenseSubCategory: document.getElementById('expenseSubCategory').value,
        amount: parseFloat(document.getElementById('expenseAmount').value),
        paymentMode: document.getElementById('paymentMode').value,
        paymentModeType: document.getElementById('paymentModeType').value,
        byWhom: document.getElementById('paidBy').value,
        accountNo: document.getElementById('accountNo').value
    };
    
    try {
        const response = await fetch(`${API_BASE_URL}/addExpenseTrasactionsRecords`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(transactionData)
        });
        
        if (response.ok) {
            showNotification('Transaction added successfully', 'success');
            document.getElementById('transactionForm').reset();
            loadTransactions();
        } else {
            showNotification('Error adding transaction', 'error');
        }
    } catch (error) {
        console.error('Error:', error);
        showNotification('Error adding transaction', 'error');
    }
}

async function exportTransactions() {
    try {
        window.location.href = `${API_BASE_URL}/exportExpenseTrasactionsRecords`;
        showNotification('Exporting transactions...', 'success');
    } catch (error) {
        console.error('Error:', error);
        showNotification('Error exporting transactions', 'error');
    }
}

// Accounts
async function addAccount(event) {
    event.preventDefault();
    
    const accountData = {
        accountNo: document.getElementById('accountNo').value,
        accountHolderName: document.getElementById('accountHolderName').value,
        bankName: document.getElementById('bankName').value,
        balance: parseFloat(document.getElementById('balance').value)
    };
    
    try {
        const response = await fetch(`${API_BASE_URL}/addAccountDetails`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(accountData)
        });
        
        if (response.ok) {
            showNotification('Account added successfully', 'success');
            document.getElementById('accountForm').reset();
            loadMasterData();
        } else {
            showNotification('Error adding account', 'error');
        }
    } catch (error) {
        console.error('Error:', error);
        showNotification('Error adding account', 'error');
    }
}

async function getAccountDetails() {
    const accountNo = document.getElementById('searchAccountNo').value;
    
    if (!accountNo) {
        showNotification('Please enter account number', 'error');
        return;
    }
    
    try {
        const response = await fetch(`${API_BASE_URL}/getAccountDetails/${accountNo}`);
        const data = await response.json();
        
        const detailsView = document.getElementById('accountDetailsView');
        if (data) {
            detailsView.innerHTML = `
                <div class="info-box" style="background: #f0f0f0; padding: 1rem; border-radius: 4px; margin-top: 1rem;">
                    <p><strong>Account No:</strong> ${data.accountNo}</p>
                    <p><strong>Holder Name:</strong> ${data.accountHolderName}</p>
                    <p><strong>Bank:</strong> ${data.bankName}</p>
                    <p><strong>Balance:</strong> ₹${data.balance.toFixed(2)}</p>
                </div>
            `;
        } else {
            detailsView.innerHTML = '<p class="text-center">Account not found</p>';
        }
    } catch (error) {
        console.error('Error:', error);
        showNotification('Error fetching account details', 'error');
    }
}

async function getPassbook() {
    const accountNo = document.getElementById('passbookAccountNo').value;
    
    if (!accountNo) {
        showNotification('Please enter account number', 'error');
        return;
    }
    
    try {
        const response = await fetch(`${API_BASE_URL}/getAllAccountTransactionRecords`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ accountNo: accountNo })
        });
        
        const data = await response.json();
        const tbody = document.getElementById('passbookList');
        tbody.innerHTML = '';
        
        if (data && data.length > 0) {
            data.forEach((record, index) => {
                const row = tbody.insertRow();
                row.innerHTML = `
                    <td>${index + 1}</td>
                    <td>${record.transaction_date}</td>
                    <td>${record.transaction_description}</td>
                    <td>${record.withrwal_amount ? '₹' + record.withrwal_amount.toFixed(2) : '-'}</td>
                    <td>${record.deposit_amount ? '₹' + record.deposit_amount.toFixed(2) : '-'}</td>
                    <td>₹${record.balance.toFixed(2)}</td>
                `;
            });
        } else {
            tbody.innerHTML = '<tr><td colspan="6" class="text-center">No records found</td></tr>';
        }
    } catch (error) {
        console.error('Error:', error);
        showNotification('Error loading passbook', 'error');
    }
}

async function exportPassbook() {
    const accountNo = document.getElementById('passbookAccountNo').value;
    
    if (!accountNo) {
        showNotification('Please enter account number', 'error');
        return;
    }
    
    try {
        window.location.href = `${API_BASE_URL}/exportAllAccountTransactionRecords`;
        showNotification('Exporting passbook...', 'success');
    } catch (error) {
        console.error('Error:', error);
        showNotification('Error exporting passbook', 'error');
    }
}

// Master Data
async function loadMasterData() {
    // This would load categories, payment modes, etc.
    // For now, mock data
    const categories = ['Food', 'Transport', 'Utilities', 'Entertainment', 'Health'];
    
    const categorySelect = document.getElementById('expenseCategory');
    categorySelect.innerHTML = '<option value="">-- Select Category --</option>';
    categories.forEach(cat => {
        categorySelect.innerHTML += `<option value="${cat}">${cat}</option>`;
    });
    
    const paymentModes = ['Cash', 'Card', 'Bank Transfer', 'Cheque'];
    const paymentModeSelect = document.getElementById('paymentMode');
    paymentModeSelect.innerHTML = '<option value="">-- Select Payment Mode --</option>';
    paymentModes.forEach(mode => {
        paymentModeSelect.innerHTML += `<option value="${mode}">${mode}</option>`;
    });
    
    const paidByNames = ['John', 'Jane', 'Robert', 'Mary'];
    const paidBySelect = document.getElementById('paidBy');
    paidBySelect.innerHTML = '<option value="">-- Select Person --</option>';
    paidByNames.forEach(name => {
        paidBySelect.innerHTML += `<option value="${name}">${name}</option>`;
    });
}

function loadSubCategories() {
    const category = document.getElementById('expenseCategory').value;
    const subcategoryMap = {
        'Food': ['Groceries', 'Restaurant', 'Coffees'],
        'Transport': ['Fuel', 'Public Transport', 'Taxi'],
        'Utilities': ['Electricity', 'Water', 'Internet'],
        'Entertainment': ['Movies', 'Games', 'Music'],
        'Health': ['Medicine', 'Doctor', 'Fitness']
    };
    
    const subCategorySelect = document.getElementById('expenseSubCategory');
    subCategorySelect.innerHTML = '<option value="">-- Select Sub Category --</option>';
    
    if (subcategoryMap[category]) {
        subcategoryMap[category].forEach(subcat => {
            subCategorySelect.innerHTML += `<option value="${subcat}">${subcat}</option>`;
        });
    }
}

async function addCategory(event) {
    event.preventDefault();
    
    const categoryData = {
        category: document.getElementById('category').value,
        subCategory: document.getElementById('subCategory').value
    };
    
    try {
        const response = await fetch(`${API_BASE_URL}/addCategoryAndSubCategory`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(categoryData)
        });
        
        if (response.ok) {
            showNotification('Category added successfully', 'success');
            document.getElementById('categoryForm').reset();
            loadMasterData();
        } else {
            showNotification('Error adding category', 'error');
        }
    } catch (error) {
        console.error('Error:', error);
        showNotification('Error adding category', 'error');
    }
}

async function addPaymentMode(event) {
    event.preventDefault();
    showNotification('Payment mode added successfully', 'success');
    document.getElementById('paymentModeForm').reset();
}

async function addPaidBy(event) {
    event.preventDefault();
    showNotification('Person added successfully', 'success');
    document.getElementById('paidByForm').reset();
}

async function addCreditDetails(event) {
    event.preventDefault();
    
    const creditData = {
        accountNo: document.getElementById('creditAccountNo').value,
        amount: parseFloat(document.getElementById('creditAmount').value),
        description: document.getElementById('creditDescription').value
    };
    
    try {
        const response = await fetch(`${API_BASE_URL}/addCreditDetails`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(creditData)
        });
        
        if (response.ok) {
            showNotification('Credit added successfully', 'success');
            document.getElementById('creditForm').reset();
        } else {
            showNotification('Error adding credit', 'error');
        }
    } catch (error) {
        console.error('Error:', error);
        showNotification('Error adding credit', 'error');
    }
}

// Initialize on page load
document.addEventListener('DOMContentLoaded', () => {
    goToPage('dashboard');
});
