package youkidkk.util.test.rule;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * <h1>ロギングルールクラス</h1>
 * <p>テストの開始時等でログ出力を行う。</p>
 * <h2>使用例</h2>
 * <pre>
 * private Logger logger = LoggerFactory.getLogger(this.getClass());
 *
 * public ExpectedException thrown = ExpectedException.none();
 *
 * &#064;Rule
 * public RuleChain ruleChain = RuleChain
 *         .outerRule(new WatcherLogRule(this.logger))
 *         .around(this.thrown);
 * </pre>
 */
public class LoggingRule extends TestWatcher {

    /** メッセージパターン : 開始時 */
    public static final String MSG_PATTERN_START = "Test Case : {0} - Start.";

    /** メッセージパターン : 成功時 */
    public static final String MSG_PATTERN_SUCCEEDED = "Test Case : {0} - Succeeded.";

    /** メッセージパターン : 失敗時 */
    public static final String MSG_PATTERN_FAILED = "Test Case : {0} - Failed.{1}{2}";

    /** ロガー */
    protected Logger logger;

    /**
     * コンストラクタ
     */
    public LoggingRule() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * コンストラクタ
     * @param logger ロガー
     */
    public LoggingRule(Logger logger) {
        this.logger = logger;
    }

    /**
     * (非 Javadoc)
     * @see org.junit.rules.TestWatcher#starting(org.junit.runner.Description)
     */
    @Override
    protected void starting(Description description) {
        // 開始時にログを出力する
        this.logger.info(
                MessageFormat.format(MSG_PATTERN_START, description));
    }

    /**
     * (非 Javadoc)
     * @see org.junit.rules.TestWatcher#succeeded(org.junit.runner.Description)
     */
    @Override
    protected void succeeded(Description description) {
        // 成功時にログを出力する
        this.logger.info(
                MessageFormat.format(MSG_PATTERN_SUCCEEDED, description));
    }

    /**
     * (非 Javadoc)
     * @see org.junit.rules.TestWatcher#failed(java.lang.Throwable, org.junit.runner.Description)
     */
    @Override
    protected void failed(Throwable th, Description description) {
        // 失敗時にログを出力する
        this.logger.error(
                MessageFormat.format(MSG_PATTERN_FAILED,
                        description, System.lineSeparator(), th.toString()));
    }

}
