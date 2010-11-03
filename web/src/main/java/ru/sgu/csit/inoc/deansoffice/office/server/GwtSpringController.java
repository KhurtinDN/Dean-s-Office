package ru.sgu.csit.inoc.deansoffice.office.server;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Properties;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 25, 2010
 * Time: 9:56:58 AM
 */
public abstract class GwtSpringController extends RemoteServiceServlet implements InitializingBean, DisposableBean {
    private String servletName;

    private Properties initParameters = new Properties();

    @Autowired
    private ServletContext servletContext;

    protected GwtSpringController() {
    }

    protected GwtSpringController(String servletName) {
        this(servletName, new Properties());
    }

    protected GwtSpringController(String servletName,
                                  Properties initParameters) {
        Assert.notNull(initParameters, "'initParameters' should be specified");

        this.servletName = servletName;
        this.initParameters = initParameters;
    }

    @RequestMapping(method = {
            RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
            RequestMethod.DELETE, RequestMethod.HEAD,
            RequestMethod.OPTIONS, RequestMethod.TRACE
    })
    public void handleRequest(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws Exception {
        service(httpServletRequest, httpServletResponse);
    }

    public String processCall(String payload) throws SerializationException {
        try {
            RPCRequest rpcRequest = RPC.decodeRequest(payload, getClass());

            // delegate work to the spring injected service
            return RPC.invokeAndEncodeResponse(this, rpcRequest.getMethod(),
                    rpcRequest.getParameters());
        } catch (IncompatibleRemoteServiceException e) {
            return RPC.encodeResponseForFailure(null, e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init(new ServletConfigStub());
    }

    private class ServletConfigStub implements ServletConfig {
        public String getServletName() {
            return servletName;
        }

        public ServletContext getServletContext() {
            return servletContext;
        }

        public String getInitParameter(String paramName) {
            return initParameters.getProperty(paramName);
        }

        public Enumeration getInitParameterNames() {
            return initParameters.keys();
        }
    }
}
