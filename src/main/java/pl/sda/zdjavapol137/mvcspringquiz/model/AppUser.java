package pl.sda.zdjavapol137.mvcspringquiz.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = false)
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @ToString.Exclude
    @Getter(AccessLevel.PRIVATE)
    private String hashedPassword;

    @Column(unique = true)
    private String emailAddress;

    private boolean enabled;

    private String roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // rola: USER, authority: ROLE_USER
        // roles: USER ADMIN OWNER
        // getAuthorities(): [ROLE_USER, ROLE_ADMIN, ROLE_OWNER]
        return Arrays.stream(roles.split(" "))
                .map(a -> new SimpleGrantedAuthority("ROLE_" + a))
                .toList();
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    public String getUsername() {
        return emailAddress;
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
        return enabled;
    }
}
