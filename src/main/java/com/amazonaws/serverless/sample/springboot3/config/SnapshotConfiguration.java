package com.amazonaws.serverless.sample.springboot3.config;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.sample.springboot3.StreamLambdaHandler;
import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import java.sql.Connection;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.crac.Context;
import org.crac.Core;
import org.crac.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class SnapshotConfiguration implements Resource {
  private static final Logger LOG = LogManager.getLogger();
  LocalContainerEntityManagerFactoryBean dataSourceBean;

  public SnapshotConfiguration(LocalContainerEntityManagerFactoryBean dataSourceBean) {
    LOG.info("Snapshot config constructor");
    Core.getGlobalContext().register(SnapshotConfiguration.this);
    this.dataSourceBean = dataSourceBean;
  }

  @Override
  public void beforeCheckpoint(Context<? extends Resource> context) throws Exception {
    LOG.info("Before checkpoint");
    DataSource dataSource = dataSourceBean.getDataSource();
    Connection databaseConnection = dataSource.getConnection();

    StreamLambdaHandler.handler.proxy(getAwsProxyRequest(), new MockLambdaContext());

    if (!databaseConnection.isClosed()) {
      LOG.info("Closing connection");
      databaseConnection.close();
    }
  }

  @Override
  public void afterRestore(Context<? extends Resource> context) throws Exception {
    LOG.info("Restoring");
  }

  private static AwsProxyRequest getAwsProxyRequest() {
    final AwsProxyRequest awsProxyRequest = new AwsProxyRequest();
    awsProxyRequest.setHttpMethod(HttpMethod.GET.name());
    awsProxyRequest.setPath("/health");
    awsProxyRequest.setResource("/{proxy+}");
    awsProxyRequest.setBody("");
    awsProxyRequest.setPathParameters(Map.of("proxy", "/health"));
    return awsProxyRequest;
  }

  private static class MockLambdaContext implements com.amazonaws.services.lambda.runtime.Context {

    @Override
    public String getAwsRequestId() {
      return "";
    }

    @Override
    public String getLogGroupName() {
      return "";
    }

    @Override
    public String getLogStreamName() {
      return "";
    }

    @Override
    public String getFunctionName() {
      return "";
    }

    @Override
    public String getFunctionVersion() {
      return "";
    }

    @Override
    public String getInvokedFunctionArn() {
      return "";
    }

    @Override
    public CognitoIdentity getIdentity() {
      return null;
    }

    @Override
    public ClientContext getClientContext() {
      return null;
    }

    @Override
    public int getRemainingTimeInMillis() {
      return 0;
    }

    @Override
    public int getMemoryLimitInMB() {
      return 0;
    }

    @Override
    public LambdaLogger getLogger() {
      return null;
    }
  }

}