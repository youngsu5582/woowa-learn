package org.example.woowalearn.acceptance.config;


import org.example.woowalearn.WoowaLearnApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Sql(value = {"/clear.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = WoowaLearnApplication.class)
@TestExecutionListeners(
        value = {AcceptanceTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
public @interface AcceptanceTest {
}
