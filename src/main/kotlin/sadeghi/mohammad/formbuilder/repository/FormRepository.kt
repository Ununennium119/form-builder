package sadeghi.mohammad.formbuilder.repository

import sadeghi.mohammad.formbuilder.model.Form

interface FormRepository : BaseRepository<Form, Long> {

    fun findAllByIsPublished(isPublished: Boolean): List<Form>
}
