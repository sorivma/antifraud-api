import com.fasterxml.jackson.annotation.JsonInclude

open class NoEntityResponse(
    val message: String,
    val id: String,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val entity: String? = null
)