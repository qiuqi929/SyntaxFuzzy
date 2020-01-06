package tree;


import initial.Initialize;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class PythonTreeParserTest {

    @Before
    public void before() {
        Initialize.initialTypePool();
        try {
            Initialize.initialOperatorPool("python");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGenerateMethod() {
        System.out.println(PythonTreeParser.generateMethod());
    }
}
