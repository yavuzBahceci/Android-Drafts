package com.gardener.unit_testing.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {

    public static final String TIMESTAMP_1 = "05-2019";
    public static final String TIMESTAMP_2 = "04-2019";


    /*
    * Compare two notes are equal.
    * */

    @Test
    void areNotesEqual_identicalProperties_returnTrue() throws Exception{
        // Arrange
        Note note1 = new Note("Note #1", "This is note #1", TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Note #1", "This is note #1", TIMESTAMP_1);
        note2.setId(1);

        // Act

        // Assert
        assertEquals(note1, note2);
        System.out.println("The notes are equal!");
    }
    /*
    * Compare notes with two different ids
    * */

    @Test
    void areNotesEqual_differentIds_returnFalse() throws Exception{
        // Arrange
        Note note1 = new Note("Note #1", "This is note #1", TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Note #1", "This is note #1", TIMESTAMP_1);
        note2.setId(2);

        // Act

        // Assert
        assertNotEquals(note1, note2);
        System.out.println("The notes are not equal.");
    }

    /*
    * Compare two notes with different timestamps
    * */

    @Test
    void areNotesEqual_differentTimestamps_returnTrue() throws Exception{
        // Arrange
        Note note1 = new Note("Note #1", "This is note #1", TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Note #1", "This is note #1", TIMESTAMP_2);
        note2.setId(1);

        // Act

        // Assert
        assertEquals(note1, note2);
        System.out.println("The notes are equal!");
    }
    /*
    * Compare two notes with different titles
    * */

    @Test
    void areNotesEqual_differentTitles_returnFalse() throws Exception{
        // Arrange
        Note note1 = new Note("Note #1", "This is note #1", TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Note #2", "This is note #1", TIMESTAMP_1);
        note2.setId(1);

        // Act

        // Assert
        assertNotEquals(note1, note2);
        System.out.println("The notes are not equal!");
    }
    /*
    * Compare two notes with different contents
    * */
}
