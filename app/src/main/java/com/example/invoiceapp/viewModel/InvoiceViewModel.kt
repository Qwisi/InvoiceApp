package com.example.invoiceapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.invoiceapp.model.entities.invoice.Invoice
import com.example.invoiceapp.model.entities.invoice.InvoiceCrossItems
import com.example.invoiceapp.model.entities.invoice.InvoiceWithIC
import com.example.invoiceapp.model.entities.invoiceItem.InvoiceItem
import com.example.invoiceapp.model.repository.InvoiceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InvoiceViewModel(private val repository: InvoiceRepository) : ViewModel() {

    val invoices = repository.getInvoices()
    fun insertInvoice(invoice: Invoice) = viewModelScope.launch {
        repository.insert(invoice)
    }
    fun deleteInvoice(invoice: Invoice) = viewModelScope.launch {
        repository.delete(invoice)
    }
    fun deleteInvoice(invoiceWithIC: InvoiceWithIC) = viewModelScope.launch {
        repository.delete(repository.getById(invoiceWithIC.invoice.id))

    }

    // ------------ Queries
    fun getSize() = repository.getSize()
    fun getAveragePrice() = repository.getAveragePrice()
    fun getTodaySize() = repository.getTodaySize()
    fun getTodayAveragePrice() = repository.getTodayAveragePrice()

    // ------------ Invoice Items
    val invoiceItems = repository.getInvoiceItems()
    fun insertInvoiceItem(invoiceItem: InvoiceItem) = viewModelScope.launch {
        repository.insert(invoiceItem)
    }

    // ------------ Invoice Cross Items
    val invoiceCrossItems = repository.getInvoiceCrossItems()
    fun insertInvoiceCrossItems(invoiceCrossItems: InvoiceCrossItems) = viewModelScope.launch {
        repository.insert(invoiceCrossItems)
    }
}
