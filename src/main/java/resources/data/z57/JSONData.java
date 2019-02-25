package resources.data.z57;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONData<T> {

	protected Object setDataMapper(String pFileName,Class<T> pClass) {
		T t = null;
		ObjectMapper mapper = new ObjectMapper();
		 try {
			   t= mapper.readValue(new File(System.getProperty("user.dir")+ pFileName),pClass);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return t;
	
	}
}
