package com.gardener.unit_testing.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gardener.unit_testing.models.Note;

@Database(
        entities = {
                Note.class
        },
        version = 1
)
public abstract class NoteDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "notes_db";

    public abstract NoteDao getNoteDao();
}
