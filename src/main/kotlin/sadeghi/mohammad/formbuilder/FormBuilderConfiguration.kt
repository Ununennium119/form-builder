package sadeghi.mohammad.formbuilder

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import sadeghi.mohammad.formbuilder.model.BaseModel
import sadeghi.mohammad.formbuilder.repository.BaseRepository

@Configuration
@EntityScan(basePackageClasses = [BaseModel::class])
@EnableJpaRepositories(basePackageClasses = [BaseRepository::class])
class FormBuilderConfiguration
