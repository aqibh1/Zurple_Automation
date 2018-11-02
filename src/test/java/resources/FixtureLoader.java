package resources;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;

public class FixtureLoader {

    static String readFile(String path)
    {

        String result = "";

        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

    static HashMap<String,Object> parseFixture(String file_name)
    {
        HashMap<String,Object> result = new HashMap<>();

        String fixture_path = "resources/Fixtures/" + file_name;

        JSONObject parsed_fixture = new JSONObject(readFile(fixture_path));

        Iterator<String> keys = parsed_fixture.keys();

        while(keys.hasNext()) {
            String key = keys.next();
            Object value =  parsed_fixture.get(key);

            result.put(key,value);

        }

        return result;

    }

}
