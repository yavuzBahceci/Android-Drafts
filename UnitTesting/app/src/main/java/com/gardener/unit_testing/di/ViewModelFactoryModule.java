package com.gardener.unit_testing.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gardener.unit_testing.ui.note.NoteViewModel;
import com.gardener.unit_testing.ui.noteslist.NotesListViewModel;
import com.gardener.unit_testing.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel.class)
    public abstract ViewModel bindNoteViewModel(NoteViewModel noteViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NotesListViewModel.class)
    public abstract ViewModel bindNotesListViewModel(NotesListViewModel notesListViewModel);
}
