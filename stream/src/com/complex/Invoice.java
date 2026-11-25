/**
 * 
 */
package com.start.test.stream;

/**
 * 
 */
public class Invoice {

    private int invoiceId;
    private int supplierId;
    private String supplierName;
    private double amount;
    private String status;

    public Invoice(int invoiceId, int supplierId, String supplierName,
                   double amount, String status) {
        this.invoiceId = invoiceId;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.amount = amount;
        this.status = status;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}
