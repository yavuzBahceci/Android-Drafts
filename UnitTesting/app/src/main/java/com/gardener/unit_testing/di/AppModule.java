package com.gardener.unit_testing.di;

import android.app.Application;

import androidx.room.Room;

import com.gardener.unit_testing.persistence.NoteDao;
import com.gardener.unit_testing.persistence.NoteDatabase;
import com.gardener.unit_testing.repository.NoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.gardener.unit_testing.persistence.NoteDatabase.DATABASE_NAME;

@Module
public class AppModule {

    @Singleton
    @Provides
    static NoteDatabase provideNoteDatabase(Application application) {
        return Room.databaseBuilder(
                application,
                NoteDatabase.class,
                DATABASE_NAME
                ).build();
    }

    @Singleton
    @Provides
    static NoteDao provideNoteDao(NoteDatabase noteDatabase) {
        return noteDatabase.getNoteDao();
    }

    @Singleton
    @Provides
    static NoteRepository provideNoteRepository(NoteDao noteDao) {
        return new NoteRepository(noteDao);
    }

}
