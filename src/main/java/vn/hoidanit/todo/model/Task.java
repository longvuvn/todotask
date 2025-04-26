package vn.hoidanit.todo.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;
import vn.hoidanit.todo.enums.PriorityStatus;

@Entity
@Table(name = "\"task\"")
@Data
public class Task {
    @Id
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
    private User user; // Liên kết với User

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // Liên kết với Category

    @Enumerated(EnumType.STRING) // Lưu enum dưới dạng chuỗi (LOW, MEDIUM, HIGH)
    @Column(nullable = false)
    private PriorityStatus status;

    // Constructor phù hợp
    public Task(String title, String note, boolean completed, Instant deadline, PriorityStatus status) {
        this.title = title;
        this.note = note;
        this.completed = completed;
        this.deadline = deadline;
        this.status = status;
    }
}
