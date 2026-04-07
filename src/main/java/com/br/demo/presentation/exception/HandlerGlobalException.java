package com.br.demo.presentation.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.AuthenticationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.demo.domain.exception.BusinessException;
import com.br.demo.domain.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class HandlerGlobalException {

        // 🔹 400 - Validation Error (já é o seu)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidation(
                        MethodArgumentNotValidException ex,
                        HttpServletRequest request) {

                List<String> errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(field -> field.getField() + ": " + field.getDefaultMessage())
                                .toList();

                var response = new ErrorResponse(
                                LocalDateTime.now(),
                                400,
                                "Validation Error",
                                errors,
                                request.getRequestURI());

                return ResponseEntity.badRequest().body(response);
        }

        // 🔹 400 - Regra de negócio
        @ExceptionHandler(BusinessException.class)
        public ResponseEntity<ErrorResponse> handleBusiness(
                        BusinessException ex,
                        HttpServletRequest request) {

                var response = new ErrorResponse(
                                LocalDateTime.now(),
                                400,
                                "Business Rule Error",
                                List.of(ex.getMessage()),
                                request.getRequestURI());

                return ResponseEntity.badRequest().body(response);
        }

        // 🔹 404 - Recurso não encontrado
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleNotFound(
                        ResourceNotFoundException ex,
                        HttpServletRequest request) {

                var response = new ErrorResponse(
                                LocalDateTime.now(),
                                404,
                                "Resource Not Found",
                                List.of(ex.getMessage()),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // 🔹 401 - Não autenticado
        @ExceptionHandler(AuthenticationException.class)
        public ResponseEntity<ErrorResponse> handleUnauthorized(
                        AuthenticationException ex,
                        HttpServletRequest request) {

                var response = new ErrorResponse(
                                LocalDateTime.now(),
                                401,
                                "Unauthorized",
                                List.of("Usuário não autenticado"),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // 🔹 403 - Sem permissão
        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ErrorResponse> handleForbidden(
                        AccessDeniedException ex,
                        HttpServletRequest request) {

                var response = new ErrorResponse(
                                LocalDateTime.now(),
                                403,
                                "Forbidden",
                                List.of("Acesso negado"),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        // 🔹 409 - Conflito (ex: duplicidade)
        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<ErrorResponse> handleConflict(
                        DataIntegrityViolationException ex,
                        HttpServletRequest request) {

                var response = new ErrorResponse(
                                LocalDateTime.now(),
                                409,
                                "Conflict",
                                List.of("Violação de integridade de dados"),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        // 🔹 500 - Erro genérico (fallback)
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGeneric(
                        Exception ex,
                        HttpServletRequest request) {

                var response = new ErrorResponse(
                                LocalDateTime.now(),
                                500,
                                "Internal Server Error",
                                List.of("Erro inesperado na aplicação"),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
}
