package sadeghi.mohammad.formbuilder.model

import jakarta.persistence.*
import sadeghi.mohammad.formbuilder.enumeration.SubmitMethod

@Entity
class Form : BaseModel {

    @Column(nullable = false)
    private val name: String? = null

    @Column(nullable = false)
    private val isPublished: Boolean = false

    @OneToMany(
        mappedBy = "form",
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    private val fields: List<FormField> = listOf()

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private val submitMethod: SubmitMethod = SubmitMethod.GET

    @Column(nullable = false)
    private val submitAddress: String? = null


    constructor(
        name: String,
        isPublished: Boolean,
        fields: List<FormField>,
        submitMethod: SubmitMethod,
        submitAddress: String,
    )

    constructor()
}
