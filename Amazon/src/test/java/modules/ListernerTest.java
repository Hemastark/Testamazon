package modules;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import baseSetup.Operations;

public class ListernerTest implements ITestListener {
	
	Operations operate = new Operations();

	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Method " + result.getName() + " has Failed .");
		String methodName = result.getName().toString().trim();
		operate.screenshot(methodName);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}
	
	

}
