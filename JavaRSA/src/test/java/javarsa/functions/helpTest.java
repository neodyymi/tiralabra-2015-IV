/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.functions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author vrsaari
 */
public class helpTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream defaultOut = System.out;
//    @Rule
//    public final StandardOutputStreamLog
    
    @Before
    public void setUp() {
        System.setOut(new PrintStream(output));
    }
    
    @After
    public void cleanUp() {
        System.setOut(defaultOut);
    }
    
    @Test
    public void helpPrintsToSystemOut() {
        Help.help();
        assertEquals("Valid commands are: keygen, encrypt, decrypt, import, export and comparison\n", output.toString());
    }

}
