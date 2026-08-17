

(function () {
  'use strict';

  // ---- Mock seed data (replace with a fetch to your backend) ----
  // TODO: replace with `fetch('/api/payments').then(r => r.json())`
  let payments = [
    { id: 'SHP-10432', customer: 'R. Menon',    method: 'UPI',            date: '2026-08-10', amount: 1450.00, status: 'paid' },
    { id: 'SHP-10419', customer: 'A. Fernandes',method: 'Card',           date: '2026-08-11', amount: 3200.00, status: 'paid' },
    { id: 'SHP-10405', customer: 'S. Iyer',     method: 'Cash on Delivery', date: '2026-08-12', amount: 890.00, status: 'pending' },
    { id: 'SHP-10398', customer: 'K. Rao',      method: 'Bank Transfer',  date: '2026-08-12', amount: 5600.00, status: 'failed' },
    { id: 'SHP-10388', customer: 'P. Sharma',   method: 'UPI',            date: '2026-08-13', amount: 2100.00, status: 'paid' }
  ];

  let nextRef = 10441; // running ledger doc number

  // ---- DOM refs ----
  const form           = document.getElementById('paymentForm');
  const shipmentInput  = document.getElementById('shipmentId');
  const customerInput  = document.getElementById('customerName');
  const amountInput    = document.getElementById('amount');
  const dateInput      = document.getElementById('payDate');
  const notesInput     = document.getElementById('notes');
  const methodGroup    = document.getElementById('methodGroup');
  const submitBtn      = document.getElementById('submitBtn');
  const confirmBanner  = document.getElementById('confirmBanner');
  const confirmText    = document.getElementById('confirmText');
  const ledgerBody     = document.getElementById('ledgerBody');
  const searchInput    = document.getElementById('searchInput');
  const statusFilter   = document.getElementById('statusFilter');
  const ledgerRefEl    = document.getElementById('ledgerRef');
  const sumCollected   = document.getElementById('sumCollected');
  const sumPending     = document.getElementById('sumPending');
  const sumFailed      = document.getElementById('sumFailed');

  let selectedMethod = 'UPI';

  // ---- Init ----
  dateInput.value = new Date().toISOString().slice(0, 10);
  updateLedgerRef();
  renderLedger();
  updateSummary();

  // ---- Method selector ----
  methodGroup.addEventListener('click', function (e) {
    const opt = e.target.closest('.method-option');
    if (!opt) return;
    methodGroup.querySelectorAll('.method-option').forEach(function (el) {
      el.classList.remove('selected');
    });
    opt.classList.add('selected');
    selectedMethod = opt.dataset.method;
  });

  // ---- Form submit ----
  form.addEventListener('submit', function (e) {
    e.preventDefault();
    if (!validateForm()) return;

    submitBtn.disabled = true;
    submitBtn.textContent = 'Recording…';

    const record = {
      id: shipmentInput.value.trim(),
      customer: customerInput.value.trim(),
      method: selectedMethod,
      date: dateInput.value,
      amount: parseFloat(amountInput.value),
      // Cash on Delivery settles later, everything else confirms immediately
      status: selectedMethod === 'Cash on Delivery' ? 'pending' : 'paid'
    };

    // TODO: replace with a real POST:
    // fetch('/api/payments', { method: 'POST', headers: {'Content-Type':'application/json'}, body: JSON.stringify(record) })
    //   .then(r => r.json())
    //   .then(saved => { payments.unshift(saved); ... });
    setTimeout(function () {
      payments.unshift(record);
      nextRef += 1;
      updateLedgerRef();
      renderLedger();
      updateSummary();
      showConfirmation(record);
      form.reset();
      dateInput.value = new Date().toISOString().slice(0, 10);
      resetMethodSelector();
      submitBtn.disabled = false;
      submitBtn.textContent = 'Record payment';
    }, 350);
  });

  function validateForm() {
    let valid = true;
    valid = validateField('field-shipment', shipmentInput.value.trim().length > 0) && valid;
    valid = validateField('field-customer', customerInput.value.trim().length > 0) && valid;
    const amountOk = amountInput.value !== '' && parseFloat(amountInput.value) > 0;
    valid = validateField('field-amount', amountOk) && valid;
    return valid;
  }

  function validateField(fieldId, isValid) {
    const el = document.getElementById(fieldId);
    el.classList.toggle('invalid', !isValid);
    return isValid;
  }

  function resetMethodSelector() {
    methodGroup.querySelectorAll('.method-option').forEach(function (el) {
      el.classList.remove('selected');
    });
    methodGroup.querySelector('[data-method="UPI"]').classList.add('selected');
    selectedMethod = 'UPI';
  }

  function showConfirmation(record) {
    const stampLabel = record.status === 'pending' ? 'Pending' : 'Paid';
    const stampClass = record.status === 'pending' ? 'pending' : 'paid';
    confirmBanner.querySelector('.stamp').className = 'stamp ' + stampClass + ' enter';
    confirmBanner.querySelector('.stamp').textContent = stampLabel;
    confirmText.textContent = record.id + ' — ' + formatCurrency(record.amount) + ' recorded for ' + record.customer + '.';
    confirmBanner.style.display = 'flex';
    setTimeout(function () {
      confirmBanner.style.display = 'none';
    }, 4000);
  }

  // ---- Ledger rendering ----
  function renderLedger() {
    const query = searchInput.value.trim().toLowerCase();
    const status = statusFilter.value;

    const filtered = payments.filter(function (p) {
      const matchesQuery = !query ||
        p.id.toLowerCase().includes(query) ||
        p.customer.toLowerCase().includes(query);
      const matchesStatus = status === 'all' || p.status === status;
      return matchesQuery && matchesStatus;
    });

    if (filtered.length === 0) {
      ledgerBody.innerHTML = '<tr class="empty-row"><td colspan="6">No payments match this search.</td></tr>';
      return;
    }

    ledgerBody.innerHTML = filtered.map(function (p) {
      return (
        '<tr>' +
          '<td class="cell-id">' + escapeHtml(p.id) + '</td>' +
          '<td>' + escapeHtml(p.customer) + '</td>' +
          '<td>' + escapeHtml(p.method) + '</td>' +
          '<td>' + escapeHtml(p.date) + '</td>' +
          '<td class="cell-amount">' + formatCurrency(p.amount) + '</td>' +
          '<td><span class="stamp ' + p.status + '">' + capitalize(p.status) + '</span></td>' +
        '</tr>'
      );
    }).join('');
  }

  searchInput.addEventListener('input', renderLedger);
  statusFilter.addEventListener('change', renderLedger);

  // ---- Summary totals ----
  function updateSummary() {
    const totals = payments.reduce(function (acc, p) {
      acc[p.status] = (acc[p.status] || 0) + p.amount;
      return acc;
    }, {});
    sumCollected.textContent = formatCurrency(totals.paid || 0);
    sumPending.textContent = formatCurrency(totals.pending || 0);
    sumFailed.textContent = formatCurrency(totals.failed || 0);
  }

  function updateLedgerRef() {
    ledgerRefEl.textContent = 'PAY-' + String(nextRef).padStart(5, '0');
  }

  // ---- Helpers ----
  function formatCurrency(value) {
    return '₹' + value.toLocaleString('en-IN', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
  }

  function capitalize(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
  }

  function escapeHtml(str) {
    const div = document.createElement('div');
    div.textContent = str;
    return div.innerHTML;
  }
})();