
import org.apache.commons.csv.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CSVLoader {

    public static List<Map<String, String>> loadCSV(String filePath) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        ) {
            for (CSVRecord record : csvParser) {
                Map<String, String> row = new HashMap<>();
                for (String header : csvParser.getHeaderMap().keySet()) {
                    row.put(header, record.get(header));
                }
                data.add(row);
            }
        }

        return data;
    }
}
