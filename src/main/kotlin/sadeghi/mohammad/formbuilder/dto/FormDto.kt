package sadeghi.mohammad.formbuilder.dto

import sadeghi.mohammad.formbuilder.enumeration.SubmitMethod

data class FormDto(
    val id: Long,
    val name: String,
    val isPublished: Boolean,
    val fields: List<FieldDto>,
    val submitMethod: SubmitMethod,
    val submitAddress: String,
)
