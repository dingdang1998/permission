package org.labi.permissionsystem.config.springSecurity;

import org.springframework.core.log.LogMessage;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: permissionsystem
 * @description: test
 * @author: dzp
 * @create: 2021-03-05 15:19
 **/
public class testToken extends TokenBasedRememberMeServices {

    public testToken(String key, UserDetailsService userDetailsService) {
        super(key, userDetailsService);
    }

    @Override
    protected boolean rememberMeRequested(HttpServletRequest request, String parameter) {
        String paramValue = (String) request.getAttribute(parameter);
        if (paramValue != null) {
            if (paramValue.equalsIgnoreCase("true") || paramValue.equalsIgnoreCase("on")
                    || paramValue.equalsIgnoreCase("yes") || paramValue.equals("1")) {
                return true;
            }
        }
        this.logger.debug(
                LogMessage.format("Did not send remember-me cookie (principal did not set parameter '%s')", parameter));
        return false;
    }
}
