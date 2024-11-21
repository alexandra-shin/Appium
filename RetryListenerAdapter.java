import java.util.Iterator;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/* This class extends the TestListenerAdapter.Class from TestNG, 
 * which stores all the tests that were ran and you can retrieve these results 
 * using methods like getPassedTests(), getFailedTests().
 * I'm overriding the onFinish(ITestContext context) method here and then check 
 * for the re-tried tests. Then I'm removing re-tried tests from the context before TestNg generates the Report,
 * otherwise those tests will be marked as 'Skipped' and will show up in the statistics.
 */
public class RetryListenerAdapter extends TestListenerAdapter {

    @Override
    public void onFinish(ITestContext context) {
        Iterator<ITestResult> skippedTestCases = context.getSkippedTests().getAllResults().iterator();
        while (skippedTestCases.hasNext()) {
            ITestResult skippedTestCase = skippedTestCases.next();
            ITestNGMethod method = skippedTestCase.getMethod();
            if (context.getSkippedTests().getResults(method).size() > 0) {
                System.out.println("Removing:" + skippedTestCase.getTestClass().toString());
                skippedTestCases.remove();
            }
        }
    }
}