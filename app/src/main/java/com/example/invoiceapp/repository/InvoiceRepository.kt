package com.example.invoiceapp.repository

import androidx.lifecycle.LiveData
import com.example.invoiceapp.model.invoice.Invoice
import com.example.invoiceapp.model.invoice.InvoiceCrossItems
import com.example.invoiceapp.model.invoice.InvoiceCrossItemsDao
import com.example.invoiceapp.model.invoice.InvoiceDao
import com.example.invoiceapp.model.invoice.InvoiceWithIC
import com.example.invoiceapp.model.invoiceItem.InvoiceItem
import com.example.invoiceapp.model.invoiceItem.InvoiceItemDao

class InvoiceRepository(
    private val invoiceDao: InvoiceDao,
    private val invoiceItemDao: InvoiceItemDao,
    private val invoiceCrossItemsDao: InvoiceCrossItemsDao
) {

    fun getInvoices() = invoiceDao.getInvoicesWithIC()
    suspend fun insert(invoice: Invoice) {
        invoiceDao.insert(invoice)
    }

    // ---------- InvoiceItem
    fun getInvoiceItems() = invoiceItemDao.getInvoiceItemsWithProduct()
    suspend fun insert(invoiceItem: InvoiceItem) {
        invoiceItemDao.insert(invoiceItem)
    }

    // ---------- InvoiceCrossItems
    fun getInvoiceCrossItems() = invoiceCrossItemsDao.getLiveData()
    suspend fun insert(invoiceCrossItems: InvoiceCrossItems) {
        invoiceCrossItemsDao.insert(invoiceCrossItems)
    }


}
