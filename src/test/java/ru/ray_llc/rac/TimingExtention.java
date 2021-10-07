package ru.ray_llc.rac;

/*
 * @author Alexandr.Yakubov
 **/

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class TimingExtention implements BeforeTestExecutionCallback, AfterTestExecutionCallback, BeforeAllCallback, AfterAllCallback {
  public static final Logger log = LoggerFactory.getLogger("result");

  private StopWatch stopWatch;

  @Override
  public void afterAll(ExtensionContext extensionContext) {
    log.info('\n' + stopWatch.prettyPrint() + '\n');
  }

  @Override
  public void afterTestExecution(ExtensionContext extensionContext) {
    stopWatch.stop();
    log.info("Stop stopWatch  - time to run="+stopWatch.getLastTaskInfo().getTimeMillis());
  }

  @Override
  public void beforeAll(ExtensionContext extensionContext) {
    stopWatch = new StopWatch("Execution time of " + extensionContext.getRequiredTestClass().getSimpleName());
  }

  @Override
  public void beforeTestExecution(ExtensionContext extensionContext) {
    String testName = extensionContext.getDisplayName();
    log.info("Start " + testName);
    stopWatch.start(testName);
  }
}
