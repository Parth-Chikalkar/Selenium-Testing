        package Utils;

        import org.json.JSONArray;
        import org.json.JSONObject;

        import java.nio.file.Files;
        import java.nio.file.Paths;

        public class JsonFileReaderUtil {
            public static JSONArray returnJsonArrayfrom(String path){
                try {
        String data = new String(Files.readAllBytes(Paths.get(path)));
        return new JSONArray(data);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                    return null;
                }
            }
        }
