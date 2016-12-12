import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Macintosh on 25/11/16.
 */
public class DynaProc {

    public static Map<String, Long> cacheMap = new HashMap<>();

    public static void main(String[] args) throws Exception {

        // dynamic();
        dynamic2();

    }

    public static void dynamic2() throws Exception {


        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/Macintosh/Downloads/test")));
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line != null && !line.equals("")) {

                Scanner sc = new Scanner(line);
                String ss = sc.next();
                String ss1 = sc.next();
                int x = Integer.valueOf(ss.split(",")[0]);
                int y = Integer.valueOf(ss1.split(",")[0]);

                String avoidStr = line.substring(line.indexOf('{') + 1, line.indexOf('}')).replace("\"", "").replace(", ", ",");
                String avoids[] = new String[0];
                if (avoidStr.length() > 0) {
                    avoids = avoidStr.split(",");
                }


                String retStr = line.substring(line.indexOf('}') + 1).trim();
                long retlong = Long.valueOf(retStr);
                //String[] avoids = {"0 0 0 1", "6 6 5 6"};
                // String[] avoids = {"0 0 1 0", "1 2 2 2", "1 1 2 1"};
                // String[] avoids = {};
                List<Integer[]> avoidsI = new ArrayList<>();

                for (String s : avoids) {
                    avoidsI.add(Arrays.asList(s.split("\\s")).stream().mapToInt(i -> Integer.valueOf(i)).boxed()
                            .toArray(size -> new Integer[size]));
                }


                long ret = recursive2(0, 0, 0, 0, x, y, avoidsI);

                    System.out.println(line + ":" + ret + (ret == retlong));
                sc.close();
                cacheMap = new HashMap<>();
            }

        }


        String[] avoids = {"0 0 0 1", "6 6 5 6"};
        // String[] avoids = {"0 0 1 0", "1 2 2 2", "1 1 2 1"};
        // String[] avoids = {};
        List<Integer[]> avoidsI = new ArrayList<>();

        for (String s : avoids) {
            avoidsI.add(Arrays.asList(s.split("\\s")).stream().mapToInt(i -> Integer.valueOf(i)).boxed()
                    .toArray(size -> new Integer[size]));
        }


        System.out.println(recursive2(0, 0, 0, 0, 6, 6, avoidsI));

    }

    public static long recursive2(int oldx, int oldy, int x, int y, int maxx, int maxy, List<Integer[]> avoids) {


        if (!checkAvailable(avoids, oldx, oldy, x, y)) {
            return 0;
        } else {
            Long ret = cacheMap.get(x + "_" + y);
            if (ret != null) {
                return ret;
            } else {
                if (x == maxx && y == maxy) {
                    return 1;
                } else if (x == maxx) {
                    ret = recursive2(x, y, x, y + 1, maxx, maxy, avoids);
                } else if (y == maxy) {
                    ret = recursive2(x, y, x + 1, y, maxx, maxy, avoids);
                } else {
                    long ret1 = recursive2(x, y, x + 1, y, maxx, maxy, avoids);
                    long ret2 = recursive2(x, y, x, y + 1, maxx, maxy, avoids);
                    ret = ret1 + ret2;
                }
                cacheMap.put(x + "_" + y, ret);
                return ret;
            }
        }
    }

    private static boolean checkAvailable(List<Integer[]> avoids, int x, int y, int x2, int y2) {
        for (Integer[] integers : avoids) {
            if ((integers[0].equals(x) && integers[1].equals(y) && integers[2].equals(x2) && integers[3].equals(y2)) || (integers[0].equals(x2) && integers[1].equals(y2) && integers[2].equals(x) && integers[3].equals(y))) {
                return false;
            }
        }
        return true;
    }
}