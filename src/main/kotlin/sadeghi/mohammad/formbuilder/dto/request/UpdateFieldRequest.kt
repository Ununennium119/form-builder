package sadeghi.mohammad.formbuilder.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import sadeghi.mohammad.formbuilder.enumeration.FieldType

data class UpdateFieldRequest(
    @field:NotBlank(message = "Name must not be blank")
    @field:Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    val name: String,

    @field:NotBlank(message = "Label must not be blank")
    @field:Size(min = 1, max = 100, message = "Label must be between 1 and 100 characters")
    val label: String,

    @field:NotNull(message = "Field type must not be null")
    val type: FieldType,

    @field:NotBlank(message = "Default value must not be blank")
    @field:Size(max = 255, message = "Default value must be less than or equal to 255 characters")
    val defaultValue: String
)
