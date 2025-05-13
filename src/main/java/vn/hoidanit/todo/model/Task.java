package vn.hoidanit.todo.model;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Data;
import vn.hoidanit.todo.enums.PriorityStatus;

@Entity
@Table(name = "\"task\"")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String note;

    private boolean completed;

    @Column(nullable = true)
    private Instant deadline;

    @Column(nullable = false, updatable = false)
    private Instant createdTime;

    @Column(nullable = false)
    private Instant updatedTime;

    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = PriorityStatus.MEDIUM; // Giá trị mặc định
        }
        this.createdTime = Instant.now(); // Gán thời gian hiện tại
        this.updatedTime = Instant.now(); // Gán thời gian hiện tại
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedTime = Instant.now(); // Cập nhật thời gian khi chỉnh sửa
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PriorityStatus status;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID userId;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID categoryId;

}
