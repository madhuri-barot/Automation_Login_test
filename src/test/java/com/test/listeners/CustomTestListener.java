package com.test.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

public class CustomTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // Called when a test starts
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Called when a test passes
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Called when a test fails
        System.out.println("Test failed: " + result.getName());

        // Example: If the login test fails, skip the remaining tests
        if (result.getName().equalsIgnoreCase("testValidLogin")) {
            System.out.println("Login test failed. Skipping the remaining tests...");
            throw new SkipException("Skipping further tests after login failure.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Called when a test is skipped
        System.out.println("Test skipped: " + result.getName());
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        // Called when the entire test suite finishes
        System.out.println("Test suite finished.");
    }

    @Override
    public void onStart(org.testng.ITestContext context) {
        // Called when the entire test suite starts
        System.out.println("Test suite started.");
    }
}
