package vn.hoidanit.todo.dto;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hoidanit.todo.enums.PriorityStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private String title;
    private String note;
    private boolean completed;
    private Instant deadline;
    private PriorityStatus status;
    private UUID userId;
    private UUID categoryId;

}