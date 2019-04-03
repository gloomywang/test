package com.lingjun.insight;

public class ParsingUtils {

	/*
	 * Parses the given string to an integer. Returns null if the parsing is not
	 * possible.
	 */
	public static Integer tryParseInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/*
	 * Parses the given CSV line to expected number of segments. Returns null if
	 * the parsing is not possible.
	 */
	public static String[] parseCsvLine(String line, int expectedNumOfSegs) {
		String[] segsBySimpleSplit = line.split(",");
		// Trivial parsing case.
		if (segsBySimpleSplit.length == expectedNumOfSegs) {
			return segsBySimpleSplit;
		}
		// Non-trivial parsing case where there are commas inside quotes.
		String[] result = new String[expectedNumOfSegs];
		int i = 0, j = 0;
		for (; i < segsBySimpleSplit.length && j < expectedNumOfSegs; i++, j++) {
			if (segsBySimpleSplit[i].startsWith("\"")) {
				StringBuilder sb = new StringBuilder("");
				while (i < segsBySimpleSplit.length
						&& !segsBySimpleSplit[i].endsWith("\"")) {
					sb.append(segsBySimpleSplit[i]).append(",");
					i++;
				}
				if (i == segsBySimpleSplit.length) {
					result[j] = sb.toString();
				} else {
					result[j] = sb.append(segsBySimpleSplit[i]).toString();
				}
			} else {
				result[j] = segsBySimpleSplit[i];
			}
		}
		// Returns null if parsing cannot complete successfully.
		if (j != expectedNumOfSegs || i != segsBySimpleSplit.length ) {
			System.err.println("Cannot parse the CSV line (" + line + ") into "
					+ expectedNumOfSegs + " segments!");
			return null;
		}
		return result;
	}

}
