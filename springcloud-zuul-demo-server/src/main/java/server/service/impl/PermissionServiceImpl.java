package server.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import server.service.PermissionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        String requestUrl = request.getRequestURI();
        List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        if (CollectionUtils.isEmpty(grantedAuthorityList)) {
            return false;
        }
        boolean hasPermission = false;
        if (principal != null) {
            for (SimpleGrantedAuthority authority : grantedAuthorityList) {
                if (antPathMatcher.match(authority.getAuthority(), requestUrl)) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return true;
    }
}
