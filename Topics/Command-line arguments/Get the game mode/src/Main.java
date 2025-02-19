import java.util.HashMap;
import java.util.Map;

class Problem {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        //set default value for parameter mode
        map.put("mode","default");
        for(int i = 0; i < args.length; i+=2){
            map.put(args[i], args[i + 1]);
        }

        System.out.println(map.get("mode"));
    }
}