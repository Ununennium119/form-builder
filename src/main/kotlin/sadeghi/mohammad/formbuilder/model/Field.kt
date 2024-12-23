package sadeghi.mohammad.formbuilder.model

import jakarta.persistence.*
import sadeghi.mohammad.formbuilder.dto.FieldDto
import sadeghi.mohammad.formbuilder.enumeration.FieldType

@Entity
class Field : BaseModel {

    @ManyToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    var form: Form? = null

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false)
    var label: String? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: FieldType = FieldType.TEXT

    @Column(nullable = false)
    var defaultValue: String? = null


    constructor(
        form: Form,
        name: String,
        label: String,
        type: FieldType,
        defaultValue: String,
    ) {
        this.form = form
        this.name = name
        this.label = label
        this.type = type
        this.defaultValue = defaultValue
    }

    constructor()


    fun toDto(): FieldDto {
        return FieldDto(
            id = this.id!!,
            name = this.name!!,
            label = this.label!!,
            type = this.type,
            defaultValue = this.defaultValue!!,
        )
    }
}
