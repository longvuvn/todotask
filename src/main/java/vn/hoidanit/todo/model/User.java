package vn.hoidanit.todo.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.hoidanit.todo.enums.UserStatus;

@Entity
@Table(name = "\"user\"")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Column(nullable = false, updatable = false)
    private Instant createdTime;

    @Column(nullable = false)
    private Instant updatedTime;

    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = UserStatus.ACTIVE; // Gán trạng thái mặc định là ACTIVE
        }
        this.createdTime = Instant.now(); // Gán thời gian hiện tại
        this.updatedTime = Instant.now(); // Gán thời gian hiện tại
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedTime = Instant.now(); // Cập nhật thời gian khi chỉnh sửa
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Task> tasks;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
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