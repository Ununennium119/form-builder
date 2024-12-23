package sadeghi.mohammad.formbuilder.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import sadeghi.mohammad.formbuilder.dto.FormDto
import sadeghi.mohammad.formbuilder.dto.request.CreateUpdateFormRequest
import sadeghi.mohammad.formbuilder.service.FormService
import java.net.URI

@RestController
@RequestMapping("/forms")
class FormController(
    private val formService: FormService,
) {

    @GetMapping("/", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun list(): ResponseEntity<List<FormDto>> {
        val forms = formService.getAll()
        return ResponseEntity.ok(forms)
    }

    @PostMapping("/", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody @Validated request: CreateUpdateFormRequest): ResponseEntity<FormDto> {
        val createdForm = formService.create(request)
        return ResponseEntity.created(URI.create("/forms/${createdForm.id}")).body(createdForm)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<FormDto> {
        val form = formService.getById(id)
        return form?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PutMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(
        @PathVariable id: Long,
        @RequestBody @Validated request: CreateUpdateFormRequest,
    ): ResponseEntity<FormDto> {
        val updatedForm = formService.update(id, request)
        return updatedForm?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        formService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{id}/publish")
    fun publish(@PathVariable id: Long): ResponseEntity<Unit> {
        return if (formService.publish(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/published")
    fun getPublished(): ResponseEntity<List<FormDto>> {
        val forms = formService.getPublished()
        return ResponseEntity.ok(forms)
    }
}
