package com.saintjean.operation;

import java.util.Arrays;

public class OperationMathematique {

	public static boolean estPositif(int nombre) {
		return nombre > 0;
	}
	
	public static int factorielle(int a) throws FactorielInvalidException{
        if(a > 1)
            return a*factorielle(a-1);
        else if(a == 0)
            return 1;
        if(a < 0){
            throw new FactorielInvalidException("Le factoriel d'un nombre negatif n'existe pas");
        }
        return a;                    
    }
	
	public static int[] trier(int[] listes) {
		Arrays.sort(listes);
		return listes;
	}
}
