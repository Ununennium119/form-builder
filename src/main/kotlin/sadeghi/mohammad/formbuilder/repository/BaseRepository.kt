package sadeghi.mohammad.formbuilder.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseRepository<T, ID> : JpaRepository<T, ID>
