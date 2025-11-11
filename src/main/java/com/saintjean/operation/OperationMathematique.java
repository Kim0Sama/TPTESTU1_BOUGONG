package com.saintjean.operation;

public class OperationMathematique {

	public static boolean estPositif(int nombre) {
		return nombre > 0;
	}
	
	public static int factorielle(int a) throws IllegalParamISIException{
        if(a > 1)
            return a*factorielle(a-1);
        else if(a == 0)
            return 1;
        if(a < 0){
            throw new IllegalParamISIException("Le factoriel d'un nombre negatif n'existe pas, Il faut un nombre strictement positif");
        }
        return a;                    
    }
}
