package sadeghi.mohammad.formbuilder.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import sadeghi.mohammad.formbuilder.dto.FieldDto
import sadeghi.mohammad.formbuilder.dto.request.UpdateFieldRequest
import sadeghi.mohammad.formbuilder.service.FormService

@RestController
@RequestMapping("/forms/{id}/fields")
class FieldController(
    private val formService: FormService,
) {

    @GetMapping("", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun list(@PathVariable id: Long): ResponseEntity<List<FieldDto>> {
        val fields = formService.getAllFields(id)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(fields)
    }

    @PostMapping("", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(
        @PathVariable id: Long,
        @RequestBody @Validated request: List<UpdateFieldRequest>,
    ): ResponseEntity<List<FieldDto>> {
        val updatedForm = formService.updateFields(id, request)
        return updatedForm?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }
}
