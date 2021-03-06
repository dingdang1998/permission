package org.labi.permissionsystem.config.springSecurity;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import java.util.Comparator;
import java.util.List;

/**
 * @program: permissionsystem
 * @description: 自定义会话并发控制器
 * @author: dzp
 * @create: 2021-03-05 10:37
 **/
public class CustomerConcurrentSessionControlAuthenticationStrategy extends ConcurrentSessionControlAuthenticationStrategy {

    private boolean exceptionIfMaximumExceeded = false;

    public CustomerConcurrentSessionControlAuthenticationStrategy(SessionRegistry sessionRegistry) {
        super(sessionRegistry);
    }

    /**
     * 重写源码中的此方法，会出现一端登录，两端的会话同时失效的bug
     *
     * @param sessions
     * @param allowableSessions
     * @param registry
     * @throws SessionAuthenticationException
     */
    @Override
    protected void allowableSessionsExceeded(List<SessionInformation> sessions, int allowableSessions,
                                             SessionRegistry registry) throws SessionAuthenticationException {
        if (this.exceptionIfMaximumExceeded || (sessions == null)) {
            throw new SessionAuthenticationException(
                    this.messages.getMessage("ConcurrentSessionControlAuthenticationStrategy.exceededAllowed",
                            new Object[]{allowableSessions}, "Maximum sessions of {0} for this principal exceeded"));
        }
        sessions.sort(Comparator.comparing(SessionInformation::getLastRequest));
        //写死，默认一端登录，另一端的session失效
        List<SessionInformation> sessionsToBeExpired = sessions.subList(0, 1);
        for (SessionInformation session : sessionsToBeExpired) {
            session.expireNow();
        }
    }
}
