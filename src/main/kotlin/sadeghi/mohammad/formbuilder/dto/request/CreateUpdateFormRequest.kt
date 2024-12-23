package sadeghi.mohammad.formbuilder.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import sadeghi.mohammad.formbuilder.enumeration.SubmitMethod

data class CreateUpdateFormRequest(
    @field:NotBlank(message = "Form name must not be blank")
    @field:Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    val name: String,

    @field:NotNull(message = "Submit method cannot be null")
    val submitMethod: SubmitMethod,

    @field:NotBlank(message = "Submit address must not be blank")
    @field:Size(max = 255, message = "Submit address cannot be longer than 255 characters")
    val submitAddress: String
)
