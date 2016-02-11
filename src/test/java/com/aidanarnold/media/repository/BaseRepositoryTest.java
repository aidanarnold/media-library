package com.aidanarnold.media.repository;

import com.aidanarnold.media.config.RepositoryConfig;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class BaseRepositoryTest {

    @Autowired
    private DataSource dataSource;

    private String databaseProductName = "HSQL Database Engine";

    @Test
    public void loads() throws SQLException {

        assertThat(dataSource.getConnection().getMetaData().getDatabaseProductName()).isEqualTo(databaseProductName);
    }
}
