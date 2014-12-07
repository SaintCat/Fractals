/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.geom.Line2D;
import javafx.scene.shape.Line;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.saintcat.client.fractals.FractalController;
import ru.saintcat.client.fractals.MatrixOperations;

/**
 *
 * @author Chernyshov
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({})
public class MatTest extends TestCase {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMax() {
        double d = FractalController.arg(0, 1);
    }

    @Test
    public void testMax2() {
        double[][] mA = new double[][]{{2, 1, 4}, {-4, 0.5, 3}};
        double[][] mB = new double[][]{{0, 1}, {-2, 5}, {-1, 9}};
        double[][] res = FractalController.mul(mA, mB);
        double[][] needed = new double[][]{{-6, 43}, {-4, (double) 51 / 2}};
        for (int i = 0; i < needed.length; i++) {
            for (int j = 0; j < needed[0].length; j++) {
                //System.out.println(res[i][j]);
                assertEquals(needed[i][j], res[i][j]);
            }
        }
    }

    @Test
    public void testRotatePoint() {
        double[][] line = new double[][]{{1, 0}};
        double[][] rot = MatrixOperations.createRotationMatrix(Math.PI / 2);
        double[][] res = FractalController.mul(line, rot);
        double[][] needed = new double[][]{{0, -1}};
        for (int i = 0; i < needed.length; i++) {
            for (int j = 0; j < needed[0].length; j++) {
//                System.out.println("rot"+j + " "+ (double)((int)res[i][j]));
                assertEquals(needed[i][j], (double) ((int) res[i][j]));
            }
        }
    }

    @Test
    public void testRotate() {
        double[][] line = new double[][]{{1, 1}, {2, 2}};
        double[][] rot = MatrixOperations.createRotationMatrix(Math.PI);
        double[][] res = FractalController.mul(line, rot);
        FractalController.printArray(res);
        double[][] needed = new double[][]{{1, 1}, {1, 0}};
        for (int i = 0; i < needed.length; i++) {
            for (int j = 0; j < needed[0].length; j++) {
                System.out.println("rot" + j + " " + (double) ((int) res[i][j]));
//                assertEquals(needed[i][j], (double)((int)res[i][j]));
            }
        }
    }

    @Test
    public void testPointDistance() {
        Line2D line = new Line2D.Double(1, 1, 3, 1);
        double d = line.getP1().distance(line.getP2());
        assertEquals(d, 2.0);
    }

    @Test
    public void testScale() {
    }

    @Test
    public void testLineSize() {
        Line line = new Line(0, 3, 4, 0);
        assertTrue(FractalController.lineLenght(line) == 5.0);
    }
}