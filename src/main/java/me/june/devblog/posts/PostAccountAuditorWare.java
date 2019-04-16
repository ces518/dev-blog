package me.june.devblog.posts;

import me.june.devblog.accounts.Account;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-14
 * Time: 17:56
 **/
@Service
public class PostAccountAuditorWare implements AuditorAware<Account> {

    @Override
    public Optional<Account> getCurrentAuditor() {
        ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttribute.getRequest().getSession();
        return Optional.ofNullable((Account) session.getAttribute("SESSIONED_ACCOUNT"));
    }
}
