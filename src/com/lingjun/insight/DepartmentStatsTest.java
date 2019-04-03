package com.lingjun.insight;

import static org.junit.Assert.*;

import org.junit.Test;

public class DepartmentStatsTest {

	@Test
	public void testGetNumOfOrders() {
		DepartmentStats stats = new DepartmentStats(5, 2);
		int actual = stats.getNumOfOrders(); 
	    int expects = 5;
	    assertEquals(expects, actual);
	}

	@Test
	public void testGetNumOfFirstOrders() {
		DepartmentStats stats = new DepartmentStats(5, 2);
		int actual = stats.getNumOfFirstOrders(); 
	    int expects = 2;
	    assertEquals(expects, actual);
	}

	@Test
	public void testIncreaseNumOfOrdersByOne() {
		DepartmentStats stats = new DepartmentStats(5, 2);
		stats.increaseNumOfOrdersByOne();
		int actual = stats.getNumOfOrders(); 
	    int expects = 6;
	    assertEquals(expects, actual);
	}

	@Test
	public void testIncreaseNumOfFirstOrderByOne() {
		DepartmentStats stats = new DepartmentStats(5, 2);
		stats.increaseNumOfFirstOrdersByOne();
		int actual = stats.getNumOfFirstOrders(); 
	    int expects = 3;
	    assertEquals(expects, actual);
	}

	@Test
	public void testGetFirstOrderRatio() {
		DepartmentStats stats = new DepartmentStats(5, 2);
		double actual = stats.getFirstOrderPercentage(); 
	    double expects = 0.4;
	    assertEquals(expects, actual, 0.0001);
	}
	
	@Test
	public void testGetFirstOrderRatioWithZeroNumOfOrders() {
		DepartmentStats stats = new DepartmentStats(0, 0);
		double actual = stats.getFirstOrderPercentage(); 
	    double expects = 0.0;
	    assertEquals(expects, actual, 0.0001);
	}
}
