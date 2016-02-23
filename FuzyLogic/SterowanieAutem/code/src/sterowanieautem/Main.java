
package sterowanieautem;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.Gpr;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

/** Przykladowe uzycia programu w konsoli
 	// Load from 'FCL' file
		String fileName = "src/fcl/tipper.fcl";
		FIS fis = FIS.load(fileName, true);
		if (fis == null) { // Error while loading?
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}

		// Show ruleset
		FunctionBlock functionBlock = fis.getFunctionBlock(null);
		JFuzzyChart.get().chart(functionBlock);

		// Set inputs
		functionBlock.setVariable("service", 3);
		functionBlock.setVariable("food", 7);

		// Evaluate 
		functionBlock.evaluate();

		// Show output variable's chart
		Variable tip = functionBlock.getVariable("tip");
		JFuzzyChart.get().chart(tip, tip.getDefuzzifier(), true);
		Gpr.debug("poor[service]: " + functionBlock.getVariable("service").getMembership("poor"));

		// Print ruleSet
		System.out.println(functionBlock);
		System.out.println("TIP:" + functionBlock.getVariable("tip").getValue());
 
 
 **/


public class Main {

   
    public static void main(String[] args) {
        // Load from 'FCL' file
      String fileName = "src/fcl/auto.fcl";
      FIS fis = FIS.load(fileName, true);
       if (fis == null) { // Error while loading?
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}

		// Show ruleset
    FunctionBlock functionBlock = fis.getFunctionBlock(null);
    
	
		JFuzzyChart.get().chart(functionBlock);

		// Set inputs
		functionBlock.setVariable("rx", 20);
                functionBlock.setVariable("ry", 50);
		functionBlock.evaluate();
                
                Variable tipx = functionBlock.getVariable("ster_os_x");
                Variable tipy = functionBlock.getVariable("ster_os_y");
                
		JFuzzyChart.get().chart(tipx, tipx.getDefuzzifier(), true);
                JFuzzyChart.get().chart(tipy, tipy.getDefuzzifier(), true);
		Gpr.debug("mala[rx]: " + functionBlock.getVariable("rx").getMembership("mala"));
                
                

		// Print ruleSet
		System.out.println(functionBlock);
		System.out.println("TIP:" + functionBlock.getVariable("ster_os_x").getValue());
                
    }
    /**
      String fileName = "src/fcl/ogrzewanie.fcl";
      FIS fis = FIS.load(fileName, true);
       if (fis == null) { // Error while loading?
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}

		// Show ruleset
    FunctionBlock functionBlock = fis.getFunctionBlock(null);
    
	
		JFuzzyChart.get().chart(functionBlock);

		// Set inputs
		functionBlock.setVariable("temperatura", 23);
		functionBlock.evaluate();
                
                Variable tip = functionBlock.getVariable("ogrzewaniepokoju");
		JFuzzyChart.get().chart(tip, tip.getDefuzzifier(), true);
		Gpr.debug("mala[temperatura]: " + functionBlock.getVariable("temperatura").getMembership("mala"));

		// Print ruleSet
		System.out.println(functionBlock);
		System.out.println("TIP:" + functionBlock.getVariable("ogrzewaniepokoju").getValue());
     **/
    
}
