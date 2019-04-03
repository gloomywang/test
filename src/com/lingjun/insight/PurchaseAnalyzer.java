package com.lingjun.insight;

import com.lingjun.insight.InputOutputUtils;
import java.util.*;

public class PurchaseAnalyzer {

	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			// Checks if args's length is as expected
			System.out.println("Expect 3 input files, but found " + args.length);
			System.exit(1);
		}
		// Gets mapping from product id to department id
		Map<String, Integer> ProductToDepartment = InputOutputUtils
				.getProductToDepartment(args[1]);
		// Gets mapping from department id to DepartmentStats
		Map<Integer, DepartmentStats> departmentToStats = InputOutputUtils
				.getDepartmentToStats(args[0], ProductToDepartment);
		// Outputs the final result to CSV file
		InputOutputUtils.writeDepartmentStatsToCsv(departmentToStats, args[2]);
	}

}
