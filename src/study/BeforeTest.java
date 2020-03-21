package study;

import java.util.List;
import java.util.Map;

public class BeforeTest  {
    public < T extends Number, D extends Map > void show( D map, T key ) {
        System.out.println(map.containsKey(key));
    }
}
