package leonov.ru.translator.di

import androidx.room.Room
import leonov.ru.model.data.SearchResult
import ru.leonov.repository.datasource.RetrofitImplementation
import ru.leonov.repository.datasource.RoomDataBaseImplementation
import ru.leonov.repository.repository.Repository
import ru.leonov.repository.repository.RepositoryImplementation
import ru.leonov.repository.repository.RepositoryImplementationLocal
import ru.leonov.repository.repository.RepositoryLocal
import ru.leonov.repository.room.HistoryDataBase
import ru.leonov.repository.room.HistoryEntity
import leonov.ru.translator.view.main.MainInteractor
import leonov.ru.translator.view.main.MainViewModel
import org.koin.dsl.module
import ru.leonov.history.view.HistoryInteractor
import ru.leonov.history.view.HistoryViewModel

val repository = module {

    single<Repository<List<SearchResult>>> {
        RepositoryImplementation(RetrofitImplementation())
    }

    single<RepositoryLocal<List<HistoryEntity>>> {
        RepositoryImplementationLocal(
            RoomDataBaseImplementation(get())
        )
    }
}

val database = module {
    single {
        Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB")
            .build()
    }
    single { get<HistoryDataBase>().historyDao() }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryInteractor(get(), get()) }
    factory { HistoryViewModel(get()) }
}