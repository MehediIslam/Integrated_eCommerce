package model;
import java.util.Date;
public class Purchase 
{   
	private int purchaseId;
	private int purchaserId;
	private int supplierId;
	private double totalCharge;
	private double advancePay;
	private int receiverId;
	private Date approxReceiveDate;
	private Date ReceiveDate;
	private String status;
	
	
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public int getPurchaserId() {
		return purchaserId;
	}
	public void setPurchaserId(int purchaserId) {
		this.purchaserId = purchaserId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public double getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(double totalCharge) {
		this.totalCharge = totalCharge;
	}
	public double getAdvancePay() {
		return advancePay;
	}
	public void setAdvancePay(double advancePay) {
		this.advancePay = advancePay;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public Date getApproxReceiveDate() {
		return approxReceiveDate;
	}
	public void setApproxReceiveDate(Date approxReceiveDate) {
		this.approxReceiveDate = approxReceiveDate;
	}
	public Date getReceiveDate() {
		return ReceiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		ReceiveDate = receiveDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
