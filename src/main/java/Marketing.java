import java.util.Arrays;

/**
 * Created by Macintosh on 29/11/16.
 */
public class Marketing {


    public static void main(String[] args) throws Exception {

        String input[] = {"1 4", "2", "3", "0", ""};

        Integer inputNumber[][] = new Integer[input.length][];
        int i = 0;
        for (String s : input) {
            inputNumber[i++] = Arrays.stream(s.split("\\s")).mapToInt(m -> m.equals("") ? null : Integer.valueOf(m))
                    .boxed().toArray(l -> new Integer[l]);
        }

        Integer products[] = new Integer[input.length];
        Arrays.fill(products, 0);


    }



}
