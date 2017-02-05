package youkidkk.util.test.rule;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.RuleChain;
import org.junit.runner.Description;
import org.powermock.api.mockito.PowerMockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youkidkk.util.message.Messages;
import youkidkk.util.message.MessagesFactory;
import youkidkk.util.test.constants.MessageConstants;
import youkidkk.util.test.field.FieldUtil;
import youkidkk.util.test.method.MethodUtil;

import java.util.Arrays;

/**
 * ロギングルールクラス テストクラス
 */
public class LoggingRuleTest {

    /** メッセージ */
    private static final Messages MESSAGES = MessagesFactory.createMessages(LoggingRule.class);

    /** ロガー */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /** ルール : 予期された例外 */
    public ExpectedException thrown = ExpectedException.none();

    /** ルールチェーン */
    @Rule
    public RuleChain ruleChain = RuleChain
            .outerRule(new LoggingRule(this.logger))
            .around(this.thrown);

    /**
     * LoggingRule#LoggingRule() のテストメソッド
     * {@link LoggingRule#LoggingRule()}
     *
     * @throws Exception 例外
     */
    @Test
    public void testLoggingRule() throws Exception {
        LoggingRule instance = new LoggingRule();
        Logger logger = FieldUtil.getPrivateFieldValue(LoggingRule.class, instance, "logger");
        assertThat(logger.getName(), is(LoggingRule.class.getName()));
    }

    /**
     * LoggingRule#LoggingRule(Logger) のテストメソッド
     * {@link LoggingRule#LoggingRule(Logger)}
     *
     * @throws Exception 例外
     */
    @Test
    public void testLoggingRuleWithLoggerArg() throws Exception {
        Logger arglogger = LoggerFactory.getLogger(String.class);
        LoggingRule instance = new LoggingRule(arglogger);
        Logger logger = FieldUtil.getPrivateFieldValue(LoggingRule.class, instance, "logger");
        assertThat(logger, sameInstance(arglogger));
    }

    /** Descriptionのテスト用文字列 */
    private static final String DESCRIPTION_STRING = "Description Test";

    /** ロガーのモック */
    private Logger loggerMock = mock(Logger.class);

    /**
     * LoggingRule#starting(Description) のテストメソッド
     * {@link LoggingRule#starting(Description)}
     *
     * @throws Exception 例外
     */
    @Test
    public void testStarting() throws Exception {
        Description descriptionMock = this.getDescriptionMock();

        LoggingRule instance = new LoggingRule(this.loggerMock);
        MethodUtil.invokePrivateMethodWithArgs(LoggingRule.class, instance, "starting",
                Arrays.asList(descriptionMock), Arrays.asList(Description.class));
        String messageExpected = MESSAGES.get(MessageConstants.START, DESCRIPTION_STRING);
        verify(this.loggerMock, times(1)).info(messageExpected);
    }

    /**
     * LoggingRule#succeeded(Description) のテストメソッド
     * {@link LoggingRule#succeeded(Description)}
     *
     * @throws Exception 例外
     */
    @Test
    public void testSucceeded() throws Exception {
        Description descriptionMock = this.getDescriptionMock();

        LoggingRule instance = new LoggingRule(this.loggerMock);
        MethodUtil.invokePrivateMethodWithArgs(LoggingRule.class, instance, "succeeded",
                Arrays.asList(descriptionMock), Arrays.asList(Description.class));
        String messageExpected = MESSAGES.get(MessageConstants.END_SUCCEEDED, DESCRIPTION_STRING);
        verify(this.loggerMock, times(1)).info(messageExpected);
    }

    /**
     * LoggingRule#failed(Throwable, Description) のテストメソッド
     * {@link LoggingRule#failed(Throwable, Description)}
     *
     * @throws Exception 例外
     */
    @Test
    public void testFailed() throws Exception {
        Description descriptionMock = this.getDescriptionMock();
        Exception exception = new Exception("Exception message");

        LoggingRule instance = new LoggingRule(this.loggerMock);
        MethodUtil.invokePrivateMethodWithArgs(LoggingRule.class, instance, "failed",
                Arrays.asList(exception, descriptionMock),
                Arrays.asList(Throwable.class, Description.class));
        String messageExpected = MESSAGES.get(MessageConstants.END_FAILED, DESCRIPTION_STRING,
                System.lineSeparator(), exception);
        verify(this.loggerMock, times(1)).error(messageExpected);
    }

    /**
     * Description のモックを取得する
     *
     * @return Description のモック
     * @throws Exception 例外
     */
    private Description getDescriptionMock() throws Exception {
        Description descriptionMock = PowerMockito.mock(Description.class);
        PowerMockito.when(descriptionMock, "toString").thenReturn(DESCRIPTION_STRING);
        return descriptionMock;
    }

}
