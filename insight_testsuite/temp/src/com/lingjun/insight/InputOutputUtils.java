package com.lingjun.insight;

import java.io.*;
import java.util.*;
import com.lingjun.insight.DepartmentStats;
import com.lingjun.insight.ParsingUtils;

/**
 * This class contains the utilities for reading and writing files.
 */

public class InputOutputUtils {

	private static Comparator<Map.Entry<Integer, DepartmentStats>> comp = new Comparator<Map.Entry<Integer, DepartmentStats>>() {
		public int compare(Map.Entry<Integer, DepartmentStats> e1,
				Map.Entry<Integer, DepartmentStats> e2) {
			return e1.getKey() - e2.getKey(); // Sorts by department id
		}
	};

	/* Gets the map from product id to department id. */
	public static Map<String, Integer> getProductToDepartment(String filepath) {
		Map<String, Integer> ProductToDepartment = new HashMap<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] segs = ParsingUtils.parseCsvLine(line, 4);
				if (segs != null) {
					String product = segs[0];
					Integer department = ParsingUtils.tryParseInt(segs[3]);
					if (department == null) {
						System.err
								.println("Cannot parse department id as integer, skip this record: "
										+ line);
						continue;
					}
					// Assumes each product id can have only one department id
					if (!ProductToDepartment.containsKey(product)) {
						ProductToDepartment.put(product, department);
					}
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ProductToDepartment;
	}

	/* Gets the map from department id.to DepartmentStats */
	public static Map<Integer, DepartmentStats> getDepartmentToStats(
			String filepath, Map<String, Integer> ProductToDepartment) {
		BufferedReader br = null;
		Map<Integer, DepartmentStats> departmentToStats = new HashMap<>();
		try {
			br = new BufferedReader(new FileReader(filepath));
			String line = br.readLine(); // Skips the first line as table header
			while ((line = br.readLine()) != null) {
				String[] segs = ParsingUtils.parseCsvLine(line, 4);
				// Skips ill-formatted lines
				if (segs != null) {
					String product = segs[1];
					// Skips product id with unknown associated department id
					if (!ProductToDepartment.containsKey(product)) {
						continue;
					}
					Integer department = ProductToDepartment.get(product);
					Integer reordered = ParsingUtils.tryParseInt(segs[3]);
					if (reordered == null) {
						System.err
								.println("Cannot parse field reordered as integer, skip this record: "
										+ line);
						continue;
					}
					if (!departmentToStats.containsKey(department)) {
						departmentToStats.put(department, new DepartmentStats(
								0, 0));
					}
					DepartmentStats curStats = departmentToStats
							.get(department);
					if (reordered != 0 && reordered != 1) {
						System.err
								.println("The field reordered cannot be anything other than zero or one: "
										+ line);
						continue;
					}
					// Increases "number of orders" by 1.
					curStats.increaseNumOfOrdersByOne();
					// Increases "number of first orders" by 1 if not reordered
					if (reordered == 0) {
						curStats.increaseNumOfFirstOrdersByOne();
					}
				}
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return departmentToStats;
	}

	/* Writes the final DepartmentStats result to the output CSV file */
	public static void writeDepartmentStatsToCsv(
			Map<Integer, DepartmentStats> departmentToStats, String outputPath) {
		BufferedWriter bw = null;
		ArrayList<Map.Entry<Integer, DepartmentStats>> entrySet = new ArrayList<>(
				departmentToStats.entrySet());
		Collections.sort(entrySet, comp); // Sorts by department id
		try {
			bw = new BufferedWriter(new FileWriter(outputPath));
			bw.write("department_id,number_of_orders,number_of_first_orders,percentage");
			bw.newLine();
			for (Map.Entry<Integer, DepartmentStats> entry : entrySet) {
				int numOrder = entry.getValue().getNumOfOrders();
				if (numOrder == 0) {
					continue; // Skips departments with 0 order
				}
				bw.write(entry.getKey() // Department id
						+ ","
						+ numOrder
						+ ","
						+ entry.getValue().getNumOfFirstOrders()
						+ ","
						+ String.format("%.2f", entry.getValue()
								.getFirstOrderPercentage()));
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
