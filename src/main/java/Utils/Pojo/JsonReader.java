package Utils.Pojo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

public class JsonReader {

    public <object> List<object> readData(String filePath, TypeReference<List<object>> typeRef){
        ObjectMapper mapper = new ObjectMapper();
        try {
        return mapper.readValue(new File(filePath),typeRef);

        } catch (Exception e){
           throw new RuntimeException("failed to read json file :" + filePath, e);
        }
    }
}
