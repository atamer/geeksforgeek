import java.util.Arrays;

/**
 * Created by Macintosh on 04/12/16.
 */
public class FiniteAutomata {

    static int TF[][];

    public static int getNextState(String pat, int M, int state, int x) {
        // If the character c is same as next character in pattern,
        // then simply increment state
        if (state < M && x == pat.charAt(state))
            return state + 1;

        int ns, i; // ns stores the result which is next state

        // ns finally contains the longest prefix which is also suffix
        // in "pat[0..state-1]c"

        // Start from the largest possible value and stop when you find
        // a prefix which is also suffix
        for (ns = state; ns > 0; ns--) {
            if (pat.charAt(ns - 1) == x) {
                for (i = 0; i < ns - 1; i++) {
                    if (pat.charAt(i) != pat.charAt(state - ns + 1 + i))
                        break;
                }
                if (i == ns - 1)
                    return ns;
            }
        }

        return 0;
    }

    public static void computeTF(String pat, int M, int TF[][]) {
        int state, x;
        for (state = 0; state <= M; ++state)
            for (x = 0; x < 256; ++x)
                TF[state][x] = getNextState(pat, M, state, x);
    }

    public static void search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        TF = new int[M + 1][256];
        for(int i  = 0 ; i < TF.length ; i++){
            Arrays.fill(TF[i],-1);
        }

        computeTF(pat, M, TF);

        // Process txt over FA.
        int i, state = 0;
        for (i = 0; i < N; i++) {
            state = TF[state][txt.charAt(i)];
            if (state == M) {
                System.out.println("\n Pattern found at index"+(i - M + 1));
            }
        }
    }


    public static void main(String[] args) throws Exception {
        String txt = "AABAACAADAABAAABAA";
        String pat = "AABA";
        search(pat, txt);

    }
}
