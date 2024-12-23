package sadeghi.mohammad.formbuilder.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import sadeghi.mohammad.formbuilder.dto.response.ValidationErrorResponse

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<ValidationErrorResponse> {
        val errors: MutableList<String> = ArrayList()

        for (fieldError: FieldError in ex.bindingResult.fieldErrors) {
            errors.add("${fieldError.field}: ${fieldError.defaultMessage}")
        }

        val validationErrorResponse = ValidationErrorResponse("Validation Failed", errors)
        return ResponseEntity(validationErrorResponse, HttpStatus.BAD_REQUEST)
    }
}
