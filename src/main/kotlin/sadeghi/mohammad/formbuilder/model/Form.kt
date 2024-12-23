package sadeghi.mohammad.formbuilder.model

import jakarta.persistence.*
import sadeghi.mohammad.formbuilder.dto.FormDto
import sadeghi.mohammad.formbuilder.dto.request.CreateUpdateFormRequest
import sadeghi.mohammad.formbuilder.dto.request.UpdateFieldRequest
import sadeghi.mohammad.formbuilder.enumeration.SubmitMethod

@Entity
class Form : BaseModel {

    @Column(nullable = false)
    private var name: String? = null

    @Column(nullable = false)
    private var isPublished: Boolean = false

    @OneToMany(
        mappedBy = "form",
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    private var fields: List<Field> = listOf()

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private var submitMethod: SubmitMethod = SubmitMethod.GET

    @Column(nullable = false)
    private var submitAddress: String? = null


    constructor(
        name: String,
        isPublished: Boolean,
        fields: List<Field>,
        submitMethod: SubmitMethod,
        submitAddress: String,
    ) {
        this.name = name
        this.isPublished = isPublished
        this.fields = fields
        this.submitMethod = submitMethod
        this.submitAddress = submitAddress
    }

    constructor()

    fun toDto(): FormDto {
        return FormDto(
            id = this.id!!,
            name = this.name!!,
            isPublished = this.isPublished,
            fields = this.fields.map { it.toDto() },
            submitMethod = this.submitMethod,
            submitAddress = this.submitAddress!!,
        )
    }

    fun update(request: CreateUpdateFormRequest) {
        this.name = request.name
        this.submitMethod = request.submitMethod
        this.submitAddress = request.submitAddress
    }

    fun publish() {
        this.isPublished = !this.isPublished
    }

    fun updateFields(request: List<UpdateFieldRequest>) {
        this.fields = request.map {
            Field(
                form = this,
                name = it.name,
                label = it.label,
                type = it.type,
                defaultValue = it.defaultValue,
            )
        }
    }
}
