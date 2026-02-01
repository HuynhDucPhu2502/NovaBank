package me.huynhducphu.nova.card_service.advice.handler;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.extern.slf4j.Slf4j;

import me.huynhducphu.nova.card_service.advice.base.ErrorCode;
import me.huynhducphu.nova.card_service.advice.exception.CustomDataIntegrityViolationException;
import me.huynhducphu.nova.card_service.advice.exception.CustomEntityNotFoundException;
import me.huynhducphu.nova.card_service.advice.exception.ResourceAlreadyExistsException;
import me.huynhducphu.nova.card_service.dto.base.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

/**
 * Admin 11/25/2025
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        var error = ErrorCode.INVALID_KEY;

        String errorMessage = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(error.getCode());
        apiResponse.setMessage(errorMessage);

        return ResponseEntity
                .status(error.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = {
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ApiResponse<Void>> handleBadRequestExceptions(Exception e) {
        var error = ErrorCode.INVALID_KEY;

        String errorMessage = error.getMessage();

        if (e instanceof MethodArgumentTypeMismatchException ex) {
            Class<?> type = ex.getRequiredType();
            String typeName = type != null ? type.getSimpleName() : "a valid type";
            errorMessage = ex.getName() + " must be of type " + typeName;
        }

        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(error.getCode());
        apiResponse.setMessage(errorMessage);

        return ResponseEntity
                .status(error.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodNotAllowed(
    ) {
        var error = ErrorCode.METHOD_NOT_ALLOWED;

        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(error.getCode());
        apiResponse.setMessage(error.getMessage());

        return ResponseEntity
                .status(error.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = CustomEntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleEntityNotFoundException(
            CustomEntityNotFoundException e
    ) {
        var error = ErrorCode.ENTITY_NOT_FOUND;

        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(error.getCode());
        apiResponse.setMessage(e.getMessage());

        return ResponseEntity
                .status(error.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = CustomDataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDataIntegrityViolationException(
            CustomDataIntegrityViolationException e
    ) {
        var error = ErrorCode.DATA_INTEGRITY_VIOLATION;

        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(error.getCode());
        apiResponse.setMessage(e.getMessage());

        return ResponseEntity
                .status(error.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException e
    ) {
        var error = ErrorCode.RESOURCE_ALREADY_EXISTS;
        String message = e.getMessage() == null
                ? error.getMessage() : e.getMessage();

        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(error.getCode());
        apiResponse.setMessage(message);

        return ResponseEntity
                .status(error.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = CallNotPermittedException.class)
    ResponseEntity<ApiResponse<Void>> handleCallNotPermittedException(CallNotPermittedException ex) {
        var error = ErrorCode.SERVICE_UNAVAILABLE;

        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(error.getCode());
        apiResponse.setMessage(error.getMessage());

        return ResponseEntity
                .status(error.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse<Void>> handleRuntimeException(RuntimeException ex) {
        ApiResponse<Void> apiResponse = new ApiResponse<>();

        if ("IGNORE_ME".equals(ex.getMessage())) {
            var error = ErrorCode.ENTITY_NOT_FOUND;
            apiResponse.setCode(error.getCode());
            apiResponse.setMessage("Yêu cầu không hợp lệ hoặc dữ liệu không tồn tại");

            return ResponseEntity
                    .status(error.getStatusCode())
                    .body(apiResponse);
        }

        var error = ErrorCode.UNCATEGORIZED_EXCEPTION;
        apiResponse.setCode(error.getCode());
        apiResponse.setMessage(error.getMessage());

        return ResponseEntity
                .status(error.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<Void>> handleException() {
        var error = ErrorCode.UNCATEGORIZED_EXCEPTION;

        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(error.getCode());
        apiResponse.setMessage(error.getMessage());

        return ResponseEntity
                .status(error.getStatusCode())
                .body(apiResponse);
    }


}