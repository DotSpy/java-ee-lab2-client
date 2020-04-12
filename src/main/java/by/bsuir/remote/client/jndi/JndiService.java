package by.bsuir.remote.client.jndi;

import by.bsuir.jee.CounterBean;
import by.bsuir.jee.DefaultMessageService;
import by.bsuir.jee.MessageService;
import by.bsuir.jee.RemoteCounter;
import by.bsuir.jee.TodoCrud;
import by.bsuir.jee.repository.HibernateTodoCrud;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JndiService {

  private String moduleName = "remote-0.1.5";
  @Bean
  public TodoCrud todoCrud() throws NamingException {
    Context ctx = createInitialContext();
    String appName = "";
    String distinctName = "";
    String beanName = HibernateTodoCrud.class.getSimpleName();
    String viewClassName = TodoCrud.class.getName();
    return (TodoCrud) ctx.lookup(
        "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
  }

  @Bean
  public RemoteCounter lookupRemoteStatefulCounter() throws NamingException {
    Context ctx = createInitialContext();
    String appName = "";
    String distinctName = "";
    String beanName = CounterBean.class.getSimpleName();
    String viewClassName = RemoteCounter.class.getName();
    return (RemoteCounter) ctx.lookup(
        "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName
            + "?stateful");
  }

  @Bean
  public MessageService lookupMessageService() throws NamingException {
    Context ctx = createInitialContext();
    String appName = "";
    String distinctName = "";
    String beanName = DefaultMessageService.class.getSimpleName();
    String viewClassName = MessageService.class.getName();
    return (MessageService) ctx.lookup(
        "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
  }

  private static Context createInitialContext() throws NamingException {
    Properties jndiProperties = new Properties();
    jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
    jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
    jndiProperties.put("jboss.naming.client.ejb.context", true);
    return new InitialContext(jndiProperties);
  }
}
