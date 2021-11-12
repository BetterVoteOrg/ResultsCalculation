package com.bettervote.resultcalculation;
import java.io.IOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaFunctionHandlerTest {

    private static String PLURALITYInput;
    private static String RCVInput;
    private static String STARInput;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
    	PLURALITYInput = "{choices: [\"Jim\", \"Pam\", \"Michael\"], votes: [[0], [0], [1], [1], [2], [2]], system: \"PLURALITY\"}";
        RCVInput = "{choices: [\"Jim\", \"Pam\", \"Michael\"], votes: [[0], [0], [1], [1], [2], [2]], system: \"RCV\"}";
        STARInput = "{choices: [\"Jim\", \"Pam\", \"Michael\", \"Kevin\"], votes: [[3, 3, 1, 2], [3, 3, 1, 2], [3, 3, 1, 2], [3, 3, 1, 2], [3, 3, 1, 2], [2, 2, 2, 1], [2, 2, 2, 1], [2, 2, 2, 1], [5, 4, 0, 3], [4, 5, 1, 3], [4, 0, 0, 5], [0, 2, 0, 5], [0, 2, 0, 5]], system: \"STAR\"}";
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");
        return ctx;
    }

    @Test
    public void testLambdaFunctionHandler() {
        LambdaFunctionHandler handler = new LambdaFunctionHandler();
        Context ctx = createContext();
        
        String PLURALITYoutput = handler.handleRequest(PLURALITYInput, ctx);
        String PLURALITYExpected = "{\n"
        		+ "  \"elected\": \"Tie between Jim, Pam and Michael\",\n"
        		+ "  \"analysis\": \"Jim got 2 votes.\\nPam got 2 votes.\\nMichael got 2 votes.\\n\"\n"
        		+ "}";
        String STARoutput = handler.handleRequest(STARInput, ctx);
        String STARExpected = "{\n"
        		+ "  \"elected\": \"Pam\",\n"
        		+ "  \"analysis\": \"Jim received a score of 34.\\nPam received a score of 34.\\nMichael received a score of 12.\\nKevin received a score of 34.\\nTo break a tie for first place, an elimination round was held where Jim scored lowest on 2 ballots, Pam scored lowest on 1 ballots, and Kevin scored lowest on 10 ballots. Therefore Kevin was eliminated from consideration in the runoff.\\nIn the runoff between Jim and Pam, Jim received 2 votes and Pam received 3 votes.\\n\"\n"
        		+ "}";
//      String RCVoutput = handler.handleRequest(RCVInput, ctx);
        
        
//        System.out.println(PLURALITYoutput);
//        System.out.println(PLURALITYExpected);
//      System.out.println(STARoutput);
//      System.out.println(STARExpected);
        // TODO: validate output here if needed.
        Assert.assertEquals(PLURALITYExpected, PLURALITYoutput);
        Assert.assertEquals(STARExpected, STARoutput);
    }
}
