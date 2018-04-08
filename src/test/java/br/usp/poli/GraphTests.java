package br.usp.poli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.usp.poli.utils.AssertExtended;
import br.usp.poli.utils.GraphUtil;
import br.usp.poli.utils.Point;
import br.usp.poli.utils.TestsUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class GraphTests {
	
	private static final Double sqrt200 = 10*Math.sqrt(2);
	private static final Double sqrt500 = 10*Math.sqrt(5);
	
	private List<Point> refListExpected;
	
	@Before
	public void loadContext() {
		//Mocka pontos de referencia fixos
		Point p1 = new Point(0D,0D);
		Point p2 = new Point(10D,0D);
		Point p3 = new Point(10D,10D);
		
		refListExpected = new ArrayList<Point>();
		refListExpected.add(p1);
		refListExpected.add(p2);
		refListExpected.add(p3);	
	}
	
	@Test
	public void getReferenceTriangleTest() {
		
		//{ a, b, c }
		Double[] sidesArray = new Double[] {10D, 10D, sqrt200};
		List<Double> sidesList = Arrays.asList(sidesArray);
		List<Point> referenceActual = GraphUtil.getReferenceTriangle(sidesList);
		
		for (Point p : referenceActual) {
			p = TestsUtil.trimPointToPrecision(p);
		}
		
		AssertExtended.assertPointListEquals(refListExpected, referenceActual);
	}
	
	@Test
	public void getRelativePositionTest_Quadrant1() {
		
		Point pxExpected = new Point(0D,10D);

		//{ r1, r2, r3 }
		Double[] distancesArray = new Double[] {10D, sqrt200, 10D};
		List<Double> distancesList = Arrays.asList(distancesArray);
		
		Point pxActual = GraphUtil.getRelativePosition(refListExpected, distancesList);
		pxActual = TestsUtil.trimPointToPrecision(pxActual);
		
		AssertExtended.assertPointEquals(pxExpected, pxActual);
	}
	
	@Test
	public void getRelativePositionTest_Quadrant2() {
		
		Point pxExpected = new Point(-10D,10D);

		//{ r1, r2, r3 }
		Double[] distancesArray = new Double[] {sqrt200, sqrt500, 20D};
		List<Double> distancesList = Arrays.asList(distancesArray);
		
		Point pxActual = GraphUtil.getRelativePosition(refListExpected, distancesList);
		pxActual = TestsUtil.trimPointToPrecision(pxActual);
		
		AssertExtended.assertPointEquals(pxExpected, pxActual);
	}
	
	@Test
	public void getRelativePositionTest_Quadrant3() {
		
		Point pxExpected = new Point(0D,-10D);

		//{ r1, r2, r3 }
		Double[] distancesArray = new Double[] {10D, sqrt200, sqrt500};
		List<Double> distancesList = Arrays.asList(distancesArray);
		
		Point pxActual = GraphUtil.getRelativePosition(refListExpected, distancesList);
		pxActual = TestsUtil.trimPointToPrecision(pxActual);
		
		AssertExtended.assertPointEquals(pxExpected, pxActual);
	}
	
}
