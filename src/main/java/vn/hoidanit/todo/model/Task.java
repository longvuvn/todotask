package vn.hoidanit.todo.model;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore // Bỏ qua khi trả về JSON để tránh vòng lặp vô hạn
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PriorityStatus status;

    // Getter cho userId để sử dụng trong JSON
    @JsonProperty("userId")
    public UUID getUserId() {
        return user != null ? user.getId() : null;
    }

    // Getter cho categoryId để sử dụng trong JSON
    @JsonProperty("categoryId")
    public UUID getCategoryId() {
        return category != null ? category.getId() : null;
    }
}