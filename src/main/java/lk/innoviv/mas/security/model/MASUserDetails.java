package lk.innoviv.mas.security.model;

import lk.innoviv.mas.persistence.model.Admin;
import lk.innoviv.mas.persistence.model.BEUser;
import lk.innoviv.mas.persistence.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MASUserDetails implements UserDetails {
    private final User user;

    private final Admin admin;

    private final BEUser beUser;

    private final Collection<? extends GrantedAuthority> authorities;

    private MASUserDetails(User user, Admin admin, BEUser beUser) {
        this.user = user;
        this.admin = admin;
        this.beUser = beUser;
        this.authorities = user.getAuthorities();
    }

    public MASUserDetails(User user) {
        this(user, null, null);
    }

    public MASUserDetails(Admin admin) {
        this(admin.getUser(), admin, null);
    }

    public MASUserDetails(BEUser beUser) {
        this(beUser.getUser(), null, beUser);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
