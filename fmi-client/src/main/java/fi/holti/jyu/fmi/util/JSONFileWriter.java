package fi.holti.jyu.fmi.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

public class JSONFileWriter {

	public static void writeObjectsAsJSONFile(List<?> list, String fileName) {
		try {
			Gson gson = new Gson();
			FileWriter fileWriter = new FileWriter(fileName);
			fileWriter.write(gson.toJson(list));
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
