package com.amazonaws.serverless.sample.springboot3.config;

import com.amazonaws.serverless.proxy.model.ApiGatewayRequestIdentity;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyRequestContext;
import com.amazonaws.serverless.sample.springboot3.StreamLambdaHandler;
import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.crac.Context;
import org.crac.Core;
import org.crac.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class SnapshotConfiguration implements Resource {
  private static final Logger log = LogManager.getLogger();
  LocalContainerEntityManagerFactoryBean dataSourceBean;

  public SnapshotConfiguration(LocalContainerEntityManagerFactoryBean dataSourceBean) {
    log.info("Snapshot config constructor");
    Core.getGlobalContext().register(SnapshotConfiguration.this);
    this.dataSourceBean = dataSourceBean;
  }

  @Override
  public void beforeCheckpoint(Context<? extends Resource> context) throws Exception {
    log.info("Before checkpoint");
    StreamLambdaHandler.handler.proxy(getAwsProxyRequest(), new MockLambdaContext());
  }

  private AwsProxyRequest getAwsProxyRequest() {
    ApiGatewayRequestIdentity identity = new ApiGatewayRequestIdentity();
    identity.setApiKey("foo");
    identity.setAccountId("foo");
    identity.setAccessKey("foo");

    AwsProxyRequestContext reqCtx = new AwsProxyRequestContext();
    reqCtx.setPath("/health");
    reqCtx.setStage("default");
    reqCtx.setAuthorizer(null);
    reqCtx.setIdentity(identity);

    AwsProxyRequest req = new AwsProxyRequest();
    req.setHttpMethod("GET");
    req.setPath("/health");
    req.setBody("");
    req.setRequestContext(reqCtx);

    return req;
  }

  @Override
  public void afterRestore(Context<? extends Resource> context) throws Exception {
    log.info("Restoring");
  }

  private static class MockLambdaContext implements com.amazonaws.services.lambda.runtime.Context {

    @Override
    public String getAwsRequestId() {
      return "gfdgdfg";
    }

    @Override
    public String getLogGroupName() {
      return "sdfdsg";
    }

    @Override
    public String getLogStreamName() {
      return "sdgdfg";
    }

    @Override
    public String getFunctionName() {
      return "gdfgdfg";
    }

    @Override
    public String getFunctionVersion() {
      return "null";
    }

    @Override
    public String getInvokedFunctionArn() {
      return "null";
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