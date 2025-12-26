package me.huynhducphu.nova.account_service.advice.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

/**
 * Admin 11/25/2025
 *
 **/
@Getter
public enum ErrorCode {

    // ===== System / Common ===== 1000 - 1100
    UNCATEGORIZED_EXCEPTION(9999, "Lỗi không xác định", HttpStatus.INTERNAL_SERVER_ERROR),
    METHOD_NOT_ALLOWED(1001, "Phương thức HTTP không được hỗ trợ", HttpStatus.METHOD_NOT_ALLOWED),
    INVALID_KEY(1002, "Cú pháp không hợp lệ", HttpStatus.BAD_REQUEST),

    // ===== Authentication / Authorization ===== 1200 - 1300
    UNAUTHENTICATED(12010, "Chưa xác thực", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1201, "Bạn không có quyền truy cập", HttpStatus.FORBIDDEN),
    INVALID_TOKEN(12002, "Token không hợp lệ hoặc đã hết hạn", HttpStatus.UNAUTHORIZED),
    INVALID_CREDENTIALS(1203, "Tên đăng nhập hoặc mật khẩu không đúng", HttpStatus.UNAUTHORIZED),

    // ===== Data / Persistence ===== 2000 - 2100
    ENTITY_NOT_FOUND(2000, "Không tìm thấy dữ liệu", HttpStatus.NOT_FOUND),
    DATA_INTEGRITY_VIOLATION(2001, "Dữ liệu vi phạm ràng buộc", HttpStatus.CONFLICT),
    RESOURCE_ALREADY_EXISTS(2, "Dữ liệu đã tồn tại", HttpStatus.CONFLICT),
    ;


    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    public static ErrorCode fromCode(int code) {
        for (ErrorCode ec : values()) {
            if (ec.code == code) return ec;
        }
        return UNCATEGORIZED_EXCEPTION;
    }
}
