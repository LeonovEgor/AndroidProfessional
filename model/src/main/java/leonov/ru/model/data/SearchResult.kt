package leonov.ru.model.data

import com.google.gson.annotations.Expose

class SearchResult(
    @Expose val text: String?,
    @Expose val meanings: List<Meanings>?
)
