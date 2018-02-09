package lk.innoviv.mas.security.model;

import lk.innoviv.mas.persistence.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class Security {
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_BEUSER = "ROLE_BEUSER";

    public static boolean isAdmin(User user) {
        return user.hasAuthority(Authority.ROLE_ADMIN);
    }

    public static boolean isBEUser(User user) {
        return user.hasAuthority(Authority.ROLE_BEUSER);
    }

    public enum Authority {
        ROLE_ADMIN,
        ROLE_BEUSER;

        private GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(name());

        public GrantedAuthority toGrantedAuthority() {
            return grantedAuthority;
        }

        public boolean matches(Authentication authentication) {
            return authentication.getAuthorities().contains(toGrantedAuthority());
        }
    }
}
