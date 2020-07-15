package com.gardener.unit_testing.di;

import com.gardener.unit_testing.ui.note.NoteActivity;
import com.gardener.unit_testing.ui.noteslist.NotesListsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract NotesListsActivity contributeNotesListActivity();

    @ContributesAndroidInjector
    abstract NoteActivity contributeNoteActivity();
}
