package com.finnbear.chembalancer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * © 2017 Finn Bear All Rights Reserved
 */

public class Main {
	static String numbersString = "0123456789";
	static String lowercasesString = "abcdefghijklmnopqrstuvwxyz";
	static String uppercasesString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static String alphabetString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; 
	static String elementsString = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118";
	static String symbolsString = "H,He,Li,Be,B,C,N,O,F,Ne,Na,Mg,Al,Si,P,S,Cl,Ar,K,Ca,Sc,Ti,V,Cr,Mn,Fe,Co,Ni,Cu,Zn,Ga,Ge,As,Se,Br,Kr,Rb,Sr,Y,Zr,Nb,Mo,Tc,Ru,Rh,Pd,Ag,Cd,In,Sn,Sb,Te,I,Xe,Cs,Ba,La,Ce,Pr,Nd,Pm,Sm,Eu,Gd,Tb,Dy,Ho,Er,Tm,Yb,Lu,Hf,Ta,W,Re,Os,Ir,Pt,Au,Hg,Tl,Pb,Bi,Po,At,Rn,Fr,Ra,Ac,Th,Pa,U,Np,Pu,Am,Cm,Bk,Cf,Es,Fm,Md,No,Lr,Rf,Db,Sg,Bh,Hs,Mt,Ds,Rg,Cn,Nh,Fl,Mc,Lv,Ts,Og";
	static String[] elementsArray;
	static String[] symbolsArray;
	static String namesString = "";
	
	public static void main(String[] args) {
		elementsArray = elementsString.split(",");
		symbolsArray = symbolsString.split(",");
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.print("Enter reaction: ");
			
			String reactionString = scanner.nextLine();
			
			String[] defaultReactions = {
					"C6H12O6+O2 > H2O+CO2",
					"PbN2O6+FeCl3 > PbCl2+FeN2O6",
					"CuCNS+KIO3+HCl>HCN+CuSO4+ICl+H2O+KCl",
					"K4FeC6N6+KMnO4+H2SO4>KHSO4+Fe2S3O12+MnSO4+HNO3+CO2+H2O"
			};
			
			try {
				int index = Integer.parseInt(reactionString);
				System.out.println("Defaulting to " + defaultReactions[index]);
				reactionString = defaultReactions[index];
			} catch (NumberFormatException e) {
				
			}
			
			reactionString = reactionString.replace(" ", "");
			
			String[] reactionHalves = reactionString.split(">");
			
			if (reactionHalves.length == 2) {
				String reactantsString = reactionHalves[0];
				String productsString = reactionHalves[1];
				
				if (reactantsString.equals(productsString)) {
					System.out.println("Warning: Reactants and products input are the same.");
				}
				
				//System.out.println("Loaded reactants: " + reactantsString.replace("+", " + "));
				//System.out.println("Loaded products: " + productsString.replace("+", " + "));
				
				System.out.println("Loaded reaction " + reactantsString.replace("+", " + ") + " > " + productsString.replace("+", " + "));
				
				Map<String, Integer> reactants = new LinkedHashMap<String, Integer>();
				Map<String, Integer> products = new LinkedHashMap<String, Integer>();
				
				if (reactantsString.contains("+")) {
					String[] reactantsArray = reactantsString.split("\\+");
					
					boolean[] duplicateReactantsIndices = new boolean[reactantsArray.length];
					
					for (int i = 0; i < reactantsArray.length; i++) {
						duplicateReactantsIndices[i] = reactants.containsKey(reactantsArray[i]);
						
						reactants.put(reactantsArray[i], 1 + (reactants.containsKey(reactantsArray[i]) ? reactants.get(reactantsArray[i]) : 0)); // TODO: Read initial number
						
						//System.out.println("Now there are " + reactants.get(reactantsArray[i]) + " of " + reactantsArray[i]);
					}
					
					/*
					int duplicateReactantsCount = 0;
					
					for (int i = reactantsArray.length - 1; i >= 0; i--) {
						if (duplicateReactantsIndices[i]) {
							reactants.remove(reactants.keySet().toArray()[i]);
							duplicateReactantsCount++;
						}
					}
					
					if (duplicateReactantsCount > 0) {
						System.out.println("Consolidated " + duplicateReactantsCount + " duplicate reactants.");
					}
					*/
				} else {
					reactants.put(reactantsString, 1); // TODO: Read initial number
				}
				
				if (productsString.contains("+")) {
					String[] productsArray = productsString.split("\\+");
					
					boolean[] duplicateProductsIndices = new boolean[productsArray.length];
					
					for (int i = 0; i < productsArray.length; i++) {
						duplicateProductsIndices[i] = products.containsKey(productsArray[i]);
						
						products.put(productsArray[i], 1 + (products.containsKey(productsArray[i]) ? products.get(productsArray[i]) : 0)); // TODO: Read initial number
					
						//System.out.println("Now there are " + products.get(productsArray[i]) + " of " + productsArray[i]);
					}
					
					/*
					int duplicateProductsCount = 0;
					
					for (int i = productsArray.length - 1; i >= 0; i--) {
						if (duplicateProductsIndices[i]) {
							System.out.println(products.size());
							System.out.println(i);
							products.remove(products.keySet().toArray()[i]);
							duplicateProductsCount++;
						}
					}
					
					if (duplicateProductsCount > 0) {
						System.out.println("Consolidated " + duplicateProductsCount + " duplicate products.");
					}
					*/
				} else {
					products.put(productsString, 1); // TODO: Read initial number
				}
				
				
				
				if (reactants.size() > 0 && products.size() > 0) {
					Map<int[], Integer> reactantsElements = new LinkedHashMap<int[], Integer>();
					Map<int[], Integer> productsElements = new LinkedHashMap<int[], Integer>();
					
					for (String reactantString : reactants.keySet()) {
						reactantsElements.put(countElements(reactantString), reactants.get(reactantString));
					}
					
					for (String productString : products.keySet()) {
						productsElements.put(countElements(productString), products.get(productString));
					}
					
					int[] reactantsElementsInitialTotal = countElementsByFormulaCount(reactantsElements);
					int[] productsElementsInitialTotal = countElementsByFormulaCount(productsElements);
					
					int[] reactantsElementsTotal = reactantsElementsInitialTotal; // Initialization is redundant
					int[] productsElementsTotal = productsElementsInitialTotal;
					
					int iterations = 0;
					
					int searchSpace = 0;
					
					long beginSeconds = System.currentTimeMillis() / 1000;
					long blockBeginMillis = beginSeconds * 1000;
					
					boolean changed = true;
					
					while (!changed || !compareArrayEquality(reactantsElementsTotal = countElementsByFormulaCount(reactantsElements), productsElementsTotal = countElementsByFormulaCount(productsElements))) {
						float probabilitySeed = (float)(0.5 * Math.sin(iterations / (float)5000)) + 0.49f;
						
						changed = false;
						
						if (iterations == 0 || Math.random() < 0.05) {
							reactantsElements.clear();
							productsElements.clear();
							
							for (String reactantString : reactants.keySet()) {
								reactantsElements.put(countElements(reactantString), reactants.get(reactantString));
							}
							
							for (String productString : products.keySet()) {
								productsElements.put(countElements(productString), products.get(productString));
							}
							
							reactantsElementsInitialTotal = countElementsByFormulaCount(reactantsElements);
							productsElementsInitialTotal = countElementsByFormulaCount(productsElements);
							
							reactantsElementsTotal = reactantsElementsInitialTotal;
							productsElementsTotal = productsElementsInitialTotal;
							
							changed = true;
						}
						
						assert reactantsElementsInitialTotal.length == productsElementsInitialTotal.length;
						
						int[] elementsDifferences = new int[reactantsElementsInitialTotal.length];
						
						for (int i = 0; i < reactantsElementsInitialTotal.length; i++) {
							if (reactantsElementsTotal[i] != 0) {
								elementsDifferences[i] = productsElementsTotal[i] - reactantsElementsTotal[i];
								
								//System.out.println(symbolsArray[i] + " " + reactantsElementsTotal[i] + " -> " + productsElementsTotal[i] + " (" + elementsDifferences[i] + ")");							
							}
						}
							
						boolean solved = false;
						
						for (int[] reactantElements : reactantsElements.keySet()) {
							int multiple;	
						
							for (multiple = 2; multiple < searchSpace && !solved; multiple++) {
								int[] multipliedElements = new int[reactantElements.length];
								
								for (int j = 0; j < reactantElements.length && !solved; j++) {
									multipliedElements[j] = reactantElements[j] * reactantsElements.get(reactantElements) * (multiple - 1);
								}
								
								if (compareArrayEquality(multipliedElements, elementsDifferences)) {
									reactantsElements.put(reactantElements, multiple);
									changed = true;
									solved = true;
								} else {
									if (Math.random() < 0.02) {
										reactantsElements.put(reactantElements, 1 + (int)(Math.random() * searchSpace));
										changed = true;
									}
								}
							}
						}
						
						for (int[] productElements : productsElements.keySet()) {
							int multiple;	
						
							for (multiple = 2; multiple < searchSpace && !solved; multiple++) {
								int[] multipliedElements = new int[productElements.length];
								
								for (int j = 0; j < productElements.length && !solved; j++) {
									multipliedElements[j] = productElements[j] * productsElements.get(productElements) * (multiple - 1);
								}
								
								if (compareArrayEquality(multipliedElements, elementsDifferences)) {
									reactantsElements.put(productElements, multiple);
									changed = true;
									solved = true;
								} else {
									if (Math.random() < 0.02) {
										productsElements.put(productElements, 1 + (int)(Math.random() * searchSpace));
										changed = true;
									}
								}
							}
						}
						
						iterations += 1;
						
						if (iterations % 10000 == 0) {
							long currentMillis = System.currentTimeMillis();
							long deltaMillis = currentMillis - blockBeginMillis;
							System.out.println("[" + iterations + " iterations reached in " + deltaMillis / (float)1000 + " seconds (" + Math.round((float)10000 / (deltaMillis / (float)1000)) + " iterations per second).]");
							blockBeginMillis = currentMillis;
						}
						
						// System.out.println(searchSpace);
						
						if (Math.random() < probabilitySeed * 0.5 || searchSpace < 10) {
							if (iterations > 100000 || Math.random() < 0.01) {
								searchSpace += 1;
							}
						} else if (Math.random() < probabilitySeed * 0.05) {
							searchSpace += 10;
						} else if (Math.random() < probabilitySeed * 0.02) {
							searchSpace += 100;
						} else if (Math.random() < probabilitySeed * 0.2) {
							searchSpace = 1;
						}
					}
					
					long currentSeconds = System.currentTimeMillis() / 1000;
					System.out.println("Successfully balanced reaction after " + iterations + " iterations (" + (currentSeconds - beginSeconds) + " seconds).");
					
					String balancedReactantsString = "";
					String balancedProductsString = "";
					
					Object[] reactantsElementsName = reactants.keySet().toArray();
					Object[] productsElementsName = products.keySet().toArray();
		
					Object[] reactantsElementsCount = reactantsElements.values().toArray();
					Object[] productsElementsCount = productsElements.values().toArray();
					
					// Simplify counts
					float maxFactor = 1;
					
					for (int factor = 2; factor < 100; factor++) {
						boolean works = true;
						
						for (int i = 0; i < reactantsElementsCount.length; i++) {
							if ((int)reactantsElementsCount[i] % factor != 0) {
								works = false;
							}
						}
						
						for (int i = 0; i < productsElementsCount.length; i++) {
							if ((int)productsElementsCount[i] % factor != 0) {
								works = false;
							}
						}
						
						if (works && factor > maxFactor) {
							maxFactor = factor;
						}
					}
					
					for (int i = 0; i < reactantsElementsCount.length; i++) {
						reactantsElementsCount[i] = (int)Math.round((int)reactantsElementsCount[i] / maxFactor);
					}
					
					for (int i = 0; i < productsElementsCount.length; i++) {
						productsElementsCount[i] = (int)Math.round((int)productsElementsCount[i] / maxFactor);
					}
					
					//System.out.println(reactantsElementsName.length + ", " + productsElementsName.length + ", " + reactantsElementsCount.length + ", " + productsElementsCount.length);
					
					for (int i = 0; i < reactantsElementsCount.length; i++) {
						if (i != 0) {
							balancedReactantsString += " + ";
						}
						
						int reactantCount = (int)reactantsElementsCount[i];
						
						if (reactantCount > 1) {
							balancedReactantsString += reactantCount + " ";
						}
						
						balancedReactantsString += (String)reactantsElementsName[i];
					}
					
					for (int i = 0; i < productsElementsCount.length; i++) {
						if (i != 0) {
							balancedProductsString += " + ";
						}
						
						int productCount = (int)productsElementsCount[i];
						
						//System.out.println(productCount);
						
						if (productCount > 1) {
							balancedProductsString += productCount + " ";
						}
						
						balancedProductsString += (String)productsElementsName[i];
					}
					
					//System.out.println("Balanced reactants: " + balancedReactantsString);
					//System.out.println("Balanced products: " + balancedProductsString);
					System.out.println("Balanced reaction: " + balancedReactantsString + " > " + balancedProductsString);
				} else {
					System.out.println("There must be at least one reactant and one product, separated by '+' if there are multiple.");
				}
			} else {
				System.out.println("'" + reactionString + "' is an invalid reaction.");
				System.out.println("Please use '>' to separate reactants and products.");
			}
		}
	}
	
	private static boolean compareArrayEquality(int[] array1, int[] array2) {
		if (array1.length == array2.length) {
			for (int i = 0; i < array1.length; i++) {
				if (array1[i] != array2[i]) {
					return false;
				}
			}
			
			return true;
		} else {
			return false;
		}
	}
	
	private static int[] countElementsByFormulaCount(Map<int[], Integer> formulasElements) {
		int[] totalElementsCount = new int[elementsString.length()];
		
		for (int[] formulaElements : formulasElements.keySet()) {
			assert totalElementsCount.length == formulaElements.length;
			
			for (int j = 0; j < formulaElements.length; j++) {
				totalElementsCount[j] += formulaElements[j] * formulasElements.get(formulaElements);
			}
		}
		
		return totalElementsCount;
	}
	
	private static int[] countElements(String formula) {
		int[] elementCount = new int[elementsString.length()];
		
		String symbolBuffer = "";
		String countBuffer = "";
		
		for (int i = 0; i < formula.length(); i++) {
			String character = formula.substring(i, i + 1);
			
			if (alphabetString.contains(character)) {
				if (uppercasesString.contains(character)) {
					if (countBuffer.length() > 0) {
						if (symbolBuffer.length() > 0) {
							int elementIndex = symbolIndex(symbolBuffer);
							int elementAmount = Integer.parseInt(countBuffer);
							
							elementCount[elementIndex] += elementAmount;
							
							symbolBuffer = "";
							countBuffer = "";
						} else {
							System.out.println("Could not parse formula due to number without symbol.");
						}
					} else if (symbolBuffer.length() > 0) {
						int elementIndex = symbolIndex(symbolBuffer);
						int elementAmount = 1; // Since no number specified
						
						elementCount[elementIndex] += elementAmount;
						
						symbolBuffer = "";
						countBuffer = "";
					}
				}
				
				symbolBuffer += character;
			} else if (numbersString.contains(character)) {	
				countBuffer += character;
			} else {
				System.out.println("'" + character + "' is an invalid character.");
			}
		}
		
		if (symbolBuffer.length() != 0) {
			int elementIndex = symbolIndex(symbolBuffer);
			int elementAmount;
			
			if (countBuffer.length() == 0) {
				elementAmount = 1;
			} else {
				elementAmount = Integer.parseInt(countBuffer);
			}
			
			elementCount[elementIndex] += elementAmount;
		}
		
		return elementCount;
	}
	
	private static int symbolIndex(String symbol) {
		int i;
		
		for (i = 0; i < symbolsArray.length + 1; i++) {
			if (i == symbolsArray.length) {
				System.out.println("'" + symbol + "' is an invalid element.");
				System.exit(1);
			}
			
			if (symbol.equals(symbolsArray[i])) {
				break;
			}
		}
		
		return i;
	}
}
