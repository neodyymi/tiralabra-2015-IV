/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import javarsa.JavaRSA;
import javarsa.functions.Keygen;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author vrsaari
 */
public class fileIoTest {

    private File privateFile;
    private File publicFile;
    private File messageFile;
    private Keygen keygen;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        keygen = new Keygen(128);
        keygen.keygen();
    }

    @Test
    public void privateKeyCanBeWrittenToFile() throws IOException {
        privateFile = tempFolder.newFile("privateTest");
        WriteFile.writePrivate(privateFile, keygen);
        final String fileContent = FileUtils.readFileToString(privateFile);
        assertTrue("privateKey can be written", fileContent.contains(JavaRSA.PRIVATE_FILE_BEGIN));
    }

    @Test
    public void publicKeyCanBeWrittenToFile() throws IOException {
        publicFile = tempFolder.newFile("publicTest");
        WriteFile.writePublic(publicFile, keygen);
        final String fileContent = FileUtils.readFileToString(publicFile);
        assertTrue("publicKey can be written", fileContent.contains(keygen.getPublicKey().toString()));
    }

    @Test
    public void messageCanBeWrittenToFile() throws IOException {
        messageFile = tempFolder.newFile("message");
        WriteFile.writeMessage(messageFile, "jotain");
        final String fileContent = FileUtils.readFileToString(messageFile);
        assertTrue("message can be written", fileContent.contains("jotain"));
    }

    @Test(expected = IOException.class)
    public void publicKeyCannotBeWrittenIfException() throws IOException {
        publicFile = tempFolder.newFolder("publicKansio");
        WriteFile.writePublic(publicFile, keygen);
        final String fileContent = FileUtils.readFileToString(publicFile);
        assertFalse("publicKey can be written", fileContent.contains(keygen.getPublicKey().toString()));
    }

    @Test(expected = IOException.class)
    public void privateKeyCannotBeWrittenIfException() throws IOException {
        privateFile = tempFolder.newFolder("privateKansio");
        WriteFile.writePrivate(privateFile, keygen);
        final String fileContent = FileUtils.readFileToString(privateFile);
        assertFalse("publicKey can be written", fileContent.contains(keygen.getPrivateKey().toString()));
    }

    @Test(expected = IOException.class)
    public void messageCannotBeWrittenIfException() throws IOException {
        messageFile = tempFolder.newFolder("messageKansio");
        WriteFile.writeMessage(messageFile, "jotain");
        final String fileContent = FileUtils.readFileToString(messageFile);
        assertFalse("publicKey can be written", fileContent.contains("jotain"));
    }
    
    @Test
    public void messageCanBeReadFromFile() throws IOException {
        messageFile = tempFolder.newFile("message");
        WriteFile.writeMessage(messageFile, "jotain");
        String message = ReadFile.fetchMessage(messageFile.toString());
        assertEquals("jotain", message);
    }
    
    @Test
    public void privateKeyCanBeReadFromFile() throws IOException {
        privateFile = tempFolder.newFile("privateTest");
        WriteFile.writePrivate(privateFile, keygen);
        Keygen fetchedKey = ReadFile.fetchPrivate(privateFile.toString());
        assertEquals(keygen.getPrivateKey(), fetchedKey.getPrivateKey());
    }
    
    @Test
    public void publicKeyCanBeReadFromFile() throws IOException {
        publicFile = tempFolder.newFile("publicTest");
        WriteFile.writePublic(publicFile, keygen);
        Keygen fetchedKey = ReadFile.fetchPublic(publicFile.toString());
        final String fileContent = FileUtils.readFileToString(publicFile);
        System.out.println("publicKeyCanBeReadFromFile " + fileContent);
        assertEquals(keygen.getModulus(), fetchedKey.getModulus());
        assertEquals(keygen.getPublicKey(), fetchedKey.getPublicKey());
    }

}
