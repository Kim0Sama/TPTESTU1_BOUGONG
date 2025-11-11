package com.saintjean.operation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class TestOperationMathematique {
	
	private OperationMathematique op = new OperationMathematique();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
 
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}


	static Stream<org.junit.jupiter.params.provider.Arguments> fournirTableaux(){
		return Stream.of(
				org.junit.jupiter.params.provider.Arguments.of(new int[] {5,2,1}, new int[] {1,2,5}),
				org.junit.jupiter.params.provider.Arguments.of(new int[] {6,9,8}, new int[] {6,8,9}),
				org.junit.jupiter.params.provider.Arguments.of(new int[] {15,20,1}, new int[] {1,15,20})
				);
	}
	
	@ParameterizedTest
	@MethodSource("fournirTableaux")
	void testTrier(int[] input, int[] expected) {
		assertArrayEquals(expected, op.trier(input));
	}
	@Test
	void TestestPositif() {
		assertTrue(OperationMathematique.estPositif(2));
	}
	
	@Test
	void TestFactorielle() throws IllegalParamISIException {
		System.out.println("Test de la methode factorielle");
		assertEquals(1, OperationMathematique.factorielle(0));
		assertEquals(2, OperationMathematique.factorielle(2));
		assertEquals(6, OperationMathematique.factorielle(3));
		
		assertThrows(IllegalParamISIException.class, () -> op.factorielle(-1));
		assertThrows(IllegalParamISIException.class, () -> op.factorielle(-5));
	}
	
	static Stream<org.junit.jupiter.params.provider.Arguments> fournirTableaux1() {
        return Stream.of(
            org.junit.jupiter.params.provider.Arguments.of(new double[]{1, 2, 3, 4, 5}, 5),
            org.junit.jupiter.params.provider.Arguments.of(new double[]{10, 7, 9}, 10),
            org.junit.jupiter.params.provider.Arguments.of(new double[]{-3, -7, -1, -5}, -1),
            org.junit.jupiter.params.provider.Arguments.of(new double[]{42}, 42)
        );
    }


    @ParameterizedTest
    @MethodSource("fournirTableaux1")
    void testTrouverMax(double[] input, double attendu) {
        assertEquals(attendu, op.maxNumba(input));
    }
}
