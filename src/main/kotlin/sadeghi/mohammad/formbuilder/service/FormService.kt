package sadeghi.mohammad.formbuilder.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import sadeghi.mohammad.formbuilder.dto.FieldDto
import sadeghi.mohammad.formbuilder.dto.FormDto
import sadeghi.mohammad.formbuilder.dto.request.CreateUpdateFormRequest
import sadeghi.mohammad.formbuilder.dto.request.UpdateFieldRequest
import sadeghi.mohammad.formbuilder.model.Form
import sadeghi.mohammad.formbuilder.repository.FormRepository

@Service
class FormService(
    private val formRepository: FormRepository,
) {

    fun getById(id: Long): FormDto? {
        return formRepository.findByIdOrNull(id)?.toDto()
    }

    fun getAll(): List<FormDto> {
        return formRepository.findAll().map { it.toDto() }
    }

    fun create(request: CreateUpdateFormRequest): FormDto {
        val form = Form(
            name = request.name,
            isPublished = false,
            fields = listOf(),
            submitMethod = request.submitMethod,
            submitAddress = request.submitAddress,
        )
        val createdForm = formRepository.save(form)
        return createdForm.toDto()
    }

    fun update(id: Long, request: CreateUpdateFormRequest): FormDto? {
        val form = formRepository.findByIdOrNull(id) ?: return null
        form.update(request)
        val updatedForm = formRepository.save(form)
        return updatedForm.toDto()
    }

    fun deleteById(id: Long) {
        formRepository.deleteById(id)
    }

    fun publish(id: Long): Boolean {
        val form = formRepository.findByIdOrNull(id) ?: return false
        form.publish()
        formRepository.save(form)
        return true
    }

    fun getAllFields(id: Long): List<FieldDto>? {
        val form = formRepository.findByIdOrNull(id) ?: return null
        return form.toDto().fields
    }

    fun updateFields(id: Long, request: List<UpdateFieldRequest>): List<FieldDto>? {
        val form = formRepository.findByIdOrNull(id) ?: return null
        form.updateFields(request)
        val updatedForm = formRepository.save(form)
        return updatedForm.toDto().fields
    }
}
