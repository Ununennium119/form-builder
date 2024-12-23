package sadeghi.mohammad.formbuilder.dto

import sadeghi.mohammad.formbuilder.enumeration.FieldType

data class FieldDto(
    val id: Long,
    val name: String,
    val label: String,
    val type: FieldType,
    val defaultValue: String,
)
