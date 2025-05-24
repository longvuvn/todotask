package vn.hoidanit.todo.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;
    private String errorCode;
    private LocalDateTime timestamp = LocalDateTime.now();
}
