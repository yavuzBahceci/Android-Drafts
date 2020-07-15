package com.gardener.unit_testing.repository;

import androidx.lifecycle.MutableLiveData;

import com.gardener.unit_testing.models.Note;
import com.gardener.unit_testing.persistence.NoteDao;
import com.gardener.unit_testing.ui.Resource;
import com.gardener.unit_testing.util.InstantExecutorExtension;
import com.gardener.unit_testing.util.LiveDataTestUtil;
import com.gardener.unit_testing.util.TestUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static com.gardener.unit_testing.repository.NoteRepository.DELETE_FAILURE;
import static com.gardener.unit_testing.repository.NoteRepository.DELETE_SUCCESS;
import static com.gardener.unit_testing.repository.NoteRepository.INSERT_FAILURE;
import static com.gardener.unit_testing.repository.NoteRepository.INSERT_SUCCESS;
import static com.gardener.unit_testing.repository.NoteRepository.INVALID_NOTE_ID;
import static com.gardener.unit_testing.repository.NoteRepository.NOTE_TITLE_NULL;
import static com.gardener.unit_testing.repository.NoteRepository.UPDATE_FAILURE;
import static com.gardener.unit_testing.repository.NoteRepository.UPDATE_SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(InstantExecutorExtension.class)
public class NoteRepositoryTest {

    private static final Note NOTE1 = new Note(TestUtil.TEST_NOTE_1);
    // system under test
    private NoteRepository noteRepository;

    @Mock
    private NoteDao noteDao;

    // @BeforeAll  -> works before all test starts, you also need to use @TestInstance annotation for it on Class
    // with @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    // @BeforeEach works before each test starts.

    @BeforeEach
    public void initEach() {
        // MockitoAnnotations.initMocks(this); -- OR below

        noteDao = Mockito.mock(NoteDao.class);
        noteRepository = new NoteRepository(noteDao);
    }

    /*
     * Insert Note.
     * verify the correct method is called.
     * confirm observer is triggered.
     * confirm new rows are inserted.
     * */

    @Test
    void insertNote_returnRows() throws Exception {
        // Arrange
        final Long insertedRow = 1L;
        final Single<Long> returnedData = Single.just(insertedRow);
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData);

        // Act
        final Resource<Integer> returnedValue = noteRepository.insertNote(NOTE1).blockingFirst(); // For flowable blockingFirst - blockingGet for usual data

        // Assert
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        System.out.println("Returned value: " + returnedValue.data);
        assertEquals(Resource.success(1, INSERT_SUCCESS), returnedValue);

        // Or test using RxJava
        noteRepository.insertNote(NOTE1)
                .test()
                .await()
                .assertValue(Resource.success(1, INSERT_SUCCESS));
    }

    /*
     * Insert Note.
     * Failure(return -1)
     * */

    @Test
    void insertNote_returnError() throws Exception {
        // Arrange
        final Long failedInsert = -1L;
        final Single<Long> returnedData = Single.just(failedInsert);
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData);

        // Act
        final Resource<Integer> returnedValue = noteRepository.insertNote(NOTE1).blockingFirst(); // For flowable blockingFirst - blockingGet for usual data

        // Assert
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.error(null, INSERT_FAILURE), returnedValue);

    }

    /*
     * Insert note
     * null Title
     * Confirm throw Exceptions
     * */

    @Test
    void insertNullTitle_throwsException() throws Exception {

        Exception exception = assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                final Note note = new Note(TestUtil.TEST_NOTE_1);
                note.setTitle(null);
                noteRepository.insertNote(note);
            }
        });

        assertEquals(NOTE_TITLE_NULL, exception.getMessage());
    }

    /*
    * Update note
    * verify correct method is called
    * confirm observer is triggered
    * confirm number of rows updated
    * */

    @Test
    void updateNote_returnNumRowsUpdated() throws Exception {
        // Arrange
        final int updatedRow = 1;
        when(noteDao.updateNote(any(Note.class))).thenReturn(Single.just(updatedRow));

        // Act
        final Resource<Integer> returnedValue = noteRepository.updateNote(NOTE1).blockingFirst();

        // Assert
        verify(noteDao).updateNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.success(updatedRow, UPDATE_SUCCESS), returnedValue);
    }

    /*
    * Update note
    * Failure (-1)
    * */
    @Test
    void updateNote_returnFailure() throws Exception {
        // Arrange
        final int failedInsert = -1;
        final Single<Integer> returnedData = Single.just(failedInsert);
        when(noteDao.updateNote(any(Note.class))).thenReturn(returnedData);

        // Act
        final Resource<Integer> returnedValue = noteRepository.updateNote(NOTE1).blockingFirst();

        // Assert
        verify(noteDao).updateNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.error(null, UPDATE_FAILURE), returnedValue);
    }

    /*
    * Update note
    * null title
    * throw exception
    * */
    @Test
    void updateNullTitle_throwsException() throws Exception {

        Exception exception = assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                final Note note = new Note(TestUtil.TEST_NOTE_1);
                note.setTitle(null);
                noteRepository.updateNote(note);
            }
        });

        assertEquals(NOTE_TITLE_NULL, exception.getMessage());
    }

    /*
    * delete note
    * null id
    * throw exception
    * */

    @Test
    void deleteNote_nullId_throwException() throws Exception {
        // Arrange
        Exception exception = assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                final Note note = new Note(TestUtil.TEST_NOTE_1);
                note.setId(-1);
                noteRepository.deleteNote(note);
            }
        });

        assertEquals(INVALID_NOTE_ID, exception.getMessage());
    }
    /*
    * delete note
    * delete success
    * return Resource.success with deleted row
    * */

    @Test
    void deleteNote_deleteSuccess_returnResourceSuccess() throws Exception {
        // Arrange
        final int deletedRow = 1;
        Resource<Integer> successResponse = Resource.success(deletedRow, DELETE_SUCCESS);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil = new LiveDataTestUtil<>();
        when(noteDao.deleteNote(any(Note.class))).thenReturn(Single.just(deletedRow));

        // Act
        Resource<Integer> observedResource = liveDataTestUtil.getValue(noteRepository.deleteNote(NOTE1));

        // Assert
        assertEquals(successResponse, observedResource);
    }

    /*
    * delete note
    * delete failure
    * return Resource.error
    * */
    @Test
    void deleteNote_deleteFailure_returnResourceFailure() throws Exception {
        // Arrange
        final int deletedRow = -1;
        Resource<Integer> errorResponse = Resource.error(null, DELETE_FAILURE);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil = new LiveDataTestUtil<>();
        when(noteDao.deleteNote(any(Note.class))).thenReturn(Single.just(deletedRow));

        // Act
        Resource<Integer> observedResource = liveDataTestUtil.getValue(noteRepository.deleteNote(NOTE1));

        // Assert
        assertEquals(errorResponse, observedResource);
    }

    /*
    * retrieve notes
    * return list of notes
    * */
    @Test
    void getNotes_returnListWithNotes() throws Exception {
        // Arrange
        List<Note> notes = TestUtil.TEST_NOTES_LIST;
        LiveDataTestUtil<List<Note>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<List<Note>> returnedData = new MutableLiveData<>();
        returnedData.setValue(notes);
        when(noteDao.getNotes()).thenReturn(returnedData);

        // Act
        List<Note> observedData = liveDataTestUtil.getValue(noteRepository.getNotes());

        // Assert
        assertEquals(notes, observedData);
    }

    /*
    * retrieve notes
    * return empty list
    * */

    @Test
    void getNotes_ReturnEmptyList() throws Exception {
        // Arrange
        List<Note> notes = new ArrayList<>();
        LiveDataTestUtil<List<Note>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<List<Note>> returnedData = new MutableLiveData<>();
        returnedData.setValue(notes);
        when(noteDao.getNotes()).thenReturn(returnedData);

        // Act
        List<Note> observedData = liveDataTestUtil.getValue(noteRepository.getNotes());

        // Assert
        assertEquals(notes, observedData);
    }
}
