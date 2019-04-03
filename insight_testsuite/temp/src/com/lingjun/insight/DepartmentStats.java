package com.lingjun.insight;

/**
 * This class represents the department statistics to calculate.
 */

public class DepartmentStats {
		
	private int numOfOrders;
	private int numOfFirstOrders;
	
	public DepartmentStats(int numOfOrders, int numOfFirstOrders) {
		this.numOfOrders = numOfOrders;
		this.numOfFirstOrders = numOfFirstOrders;
	}
	
	public int getNumOfOrders() {
		return this.numOfOrders;
	}
	
	public int getNumOfFirstOrders() {
		return this.numOfFirstOrders;
	}
	
	public void increaseNumOfOrdersByOne() {
		this.numOfOrders++;
	}
	
	public void increaseNumOfFirstOrdersByOne() {
		this.numOfFirstOrders++;
	}
	
	public double getFirstOrderPercentage() {
		if(numOfOrders == 0) {
			return 0.0; // Returns 0.0 if number of orders is 0.
		}
		return 1.0 * numOfFirstOrders / numOfOrders;
	}
}
