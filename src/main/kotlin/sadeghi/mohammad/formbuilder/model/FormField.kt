package sadeghi.mohammad.formbuilder.model

import jakarta.persistence.*
import sadeghi.mohammad.formbuilder.enumeration.FieldType

@Entity
class FormField : BaseModel {

    @ManyToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    private val form: Form? = null

    @Column(nullable = false)
    private val name: String? = null

    @Column(nullable = false)
    private val label: String? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private val type: FieldType = FieldType.TEXT

    @Column(nullable = false)
    private val defaultValue: String? = null


    constructor(
        name: String,
        label: String,
        type: FieldType,
        defaultValue: String,
    )

    constructor()
}
