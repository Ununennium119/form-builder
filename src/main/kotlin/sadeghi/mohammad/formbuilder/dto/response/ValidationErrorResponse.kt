package sadeghi.mohammad.formbuilder.dto.response

data class ValidationErrorResponse(
    val message: String,
    val errors: List<String>
)
