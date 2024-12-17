package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVUtil {
    private final Map<String, String> csvData;

    public CSVUtil(String filePath) {
        this.csvData = readCsvAsKeyValuePairs(filePath);
    }

    // Reads the CSV and stores header-value pairs in a Map
    private Map<String, String> readCsvAsKeyValuePairs(String filePath) {
        CSVReader reader = null;
        String[] header;
        String[] values;
        Map<String, String> resultMap = new HashMap<>();

        try {
            reader = new CSVReader(new FileReader(filePath));
            header = reader.readNext();
            values = reader.readNext();

            if (header != null && values != null) {
                for (int i = 0; i < header.length; i++) {
                    resultMap.put(header[i], values[i]);
                }
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException("Error reading the CSV file", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error closing the CSVReader: " + e.getMessage());
                }
            }
        }

        return resultMap;
    }

    // Fetches the value for a given key (header)
    public String getValueByKey(String key) {
        return csvData.get(key);
    }
}
