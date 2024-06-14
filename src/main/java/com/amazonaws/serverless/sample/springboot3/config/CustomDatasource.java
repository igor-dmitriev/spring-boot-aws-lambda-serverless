package com.amazonaws.serverless.sample.springboot3.config;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.springframework.util.StopWatch;

public class CustomDatasource implements DataSource {
  private static final org.apache.logging.log4j.Logger log = LogManager.getLogger();

  private final DataSource realDataSource;

  public CustomDatasource(DataSource realDataSource) {
    this.realDataSource = realDataSource;
  }

  @Override
  public Connection getConnection() throws SQLException {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Connection connection = realDataSource.getConnection();
    stopWatch.stop();
    log.info("getConnection elapsed time {}", stopWatch.getTotalTimeMillis());
    return connection;
  }

  @Override
  public Connection getConnection(String username, String password) throws SQLException {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Connection connection = realDataSource.getConnection(username, password);
    stopWatch.stop();
    log.info("getConnection with username and password elapsed time {}", stopWatch.getTotalTimeMillis());
    return connection;
  }

  @Override
  public PrintWriter getLogWriter() throws SQLException {
    return realDataSource.getLogWriter();
  }

  @Override
  public void setLogWriter(PrintWriter out) throws SQLException {
    realDataSource.setLogWriter(out);
  }

  @Override
  public void setLoginTimeout(int seconds) throws SQLException {
    realDataSource.setLoginTimeout(seconds);
  }

  @Override
  public int getLoginTimeout() throws SQLException {
    return realDataSource.getLoginTimeout();
  }

  @Override
  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    return realDataSource.getParentLogger();
  }

  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return realDataSource.unwrap(iface);
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return realDataSource.isWrapperFor(iface);
  }
}
