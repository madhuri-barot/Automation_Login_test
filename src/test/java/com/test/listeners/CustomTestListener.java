package com.test.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener implements ITestListener {

    private static boolean hasFailedTests = false;

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test failed: " + result.getName());
        hasFailedTests = true;
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test passed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ Test skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🔚 Test suite finished.");

        if (hasFailedTests) {
            System.out.println("❗ Some tests failed. Failing build.");
            System.exit(1); // Fail build in GitHub Actions
        } else {
            System.out.println("🎉 All tests passed successfully.");
        }
    }
}
