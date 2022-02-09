package io.cloudcraftz.chiron.csv.file;

import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author tuhin
 *
 */

@Slf4j
@Component
public class CSVFileGen {

	
	public static void writeDataAtOnce(List<String[]> data) {
		
		String filePath = "D:\\TUHIN FILE\\Prouct.csv";
		// first create file object for file placed at location
		// specified by filepath
		File file = new File(filePath);

		try {
			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);

			// create CSVWriter object filewriter object as parameter
			CSVWriter writer = new CSVWriter(outputfile);

			writer.writeAll(data);

			// closing writer connection
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
