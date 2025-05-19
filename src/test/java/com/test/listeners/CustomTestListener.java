package com.test.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        System.out.println("Exiting test suite early due to failure.");
        System.exit(1);  // This stops execution and fails GitHub Actions
    }
}
