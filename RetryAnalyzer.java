import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/* Here is simple retry logic which overrides the following method: 
 * public boolean retry(ITestResult result). 
 * This class retries tests that failed until they pass or report final failures.
 */

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private final int MAX_RETRY_COUNT = 3;

    @Override
    public boolean retry(ITestResult testResult) {
        if (testResult.isSuccess()) {
            return false;
        } else {
            if (++count <= MAX_RETRY_COUNT) {
                testResult.setStatus(ITestResult.FAILURE);
                return true;
            }
        }
        testResult.setStatus(ITestResult.FAILURE);
        return false;
    }
}