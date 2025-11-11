package com.saintjean.operation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

	@Test
	void TestestPositifi() {
		assertTrue(OperationMathematique.estPositif(2));
	}
	
	@Test
	void TestFactorielle() throws FactorielInvalidException {
		System.out.println("Test de la methode factorielle");
		assertEquals(1, OperationMathematique.factorielle(0));
		assertEquals(2, OperationMathematique.factorielle(2));
		assertEquals(6, OperationMathematique.factorielle(3));
		
		assertThrows(FactorielInvalidException.class, () -> op.factorielle(-1));
	}
}
