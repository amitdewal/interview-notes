/**
 * 
 */
package com.start.test.stream;

/**
 * 
 */
public class Loan {

	private int loanId;
	private int memberId;
	private String memberName;
	private double fee;
	private String status;

	public Loan(int loanId, int memberId, String memberName, double fee, String status) {
		this.loanId = loanId;
		this.memberId = memberId;
		this.memberName = memberName;
		this.fee = fee;
		this.status = status;
	}

	public int getLoanId() {
		return loanId;
	}

	public int getMemberId() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public double getFee() {
		return fee;
	}

	public String getStatus() {
		return status;
	}
}