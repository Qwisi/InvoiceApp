package com.example.invoiceapp.model.repository

import com.example.invoiceapp.model.entities.invoice.Invoice
import com.example.invoiceapp.model.entities.invoice.InvoiceCrossItems
import com.example.invoiceapp.model.entities.invoice.InvoiceCrossItemsDao
import com.example.invoiceapp.model.entities.invoice.InvoiceDao
import com.example.invoiceapp.model.entities.invoice.InvoiceWithIC
import com.example.invoiceapp.model.entities.invoiceItem.InvoiceItem
import com.example.invoiceapp.model.entities.invoiceItem.InvoiceItemDao
import java.util.Calendar

class InvoiceRepository(
    private val invoiceDao: InvoiceDao,
    private val invoiceItemDao: InvoiceItemDao,
    private val invoiceCrossItemsDao: InvoiceCrossItemsDao
) {

    fun getInvoices() = invoiceDao.getInvoicesWithIC()
    fun getById(id: Int) = invoiceDao.getById(id)
    suspend fun insert(invoice: Invoice) {
        invoiceDao.insert(invoice)
    }
    suspend fun delete(invoice: Invoice){
        invoiceDao.delete(invoice)
    }
    suspend fun delete(invoiceWithIC: InvoiceWithIC){
        //invoiceDao.delete(invoiceWithIC)
    }

    // ---------- Queries
    fun getSize() = invoiceDao.getSize()
    fun getAveragePrice() = invoiceDao.getAveragePrice()

    fun getTodaySize(): Int {
        val (startOfDay, startOfNextDay) = getStartAndEndOfToday()
        return invoiceDao.getSizeByDates(startOfDay, startOfNextDay)
    }

    fun getTodayAveragePrice(): Double {
        val (startOfDay, startOfNextDay) = getStartAndEndOfToday()
        return invoiceDao.getAveragePriceByDates(startOfDay, startOfNextDay)
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


    private fun getStartAndEndOfToday(): Pair<Long, Long> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startOfDay = calendar.timeInMillis

        calendar.add(Calendar.DATE, 1)
        val startOfNextDay = calendar.timeInMillis

        return Pair(startOfDay, startOfNextDay)
    }

}
