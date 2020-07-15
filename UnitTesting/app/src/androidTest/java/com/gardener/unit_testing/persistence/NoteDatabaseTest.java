package com.gardener.unit_testing.persistence;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.gardener.unit_testing.models.Note;
import com.gardener.unit_testing.util.TestUtil;

import org.junit.After;
import org.junit.Before;

public abstract class NoteDatabaseTest {

    // system under test
    protected NoteDatabase noteDatabase;

    public NoteDao getNoteDao() {
        return noteDatabase.getNoteDao();
    }

    @Before
    public void init() {
        noteDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                NoteDatabase.class
        ).build();
    }

    @After
    public void finish() {
        noteDatabase.close();
    }
}
