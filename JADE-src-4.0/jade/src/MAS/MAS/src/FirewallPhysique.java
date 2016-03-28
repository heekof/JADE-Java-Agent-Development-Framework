import jade.core.ProfileImpl;
//import module.ModuleDecision;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.*;
public class FirewallPhysique {

	public static void main(String[] args) {
		
		
		Runtime rt=Runtime.instance();
		Properties p=new ExtendedProperties();
		// true = main container
		p.setProperty("gui","true");
		
		ProfileImpl pc=new ProfileImpl(p);
		
		AgentContainer container=rt.createMainContainer(pc);
		// pour crer un agent il faut cre un controlleur !!
		try {
			AgentController agentController=container.createNewAgent("agentCommunication","agent.AgentCommunication",new Object[]{});
			AgentController agentControllerdeux=container.createNewAgent("agentDecision","agent.AgentDecision",new Object[]{});
			
			
			
			agentController.start();
			agentControllerdeux.start();
		
			   
			// Il ne faut pas copier un singleton dans une variable locale sauf dans les cas d'optimisations extrmes.
			  // ModuleDecision.getInstance().setParametres(String S,String[] Note_Link_VM,String[] Note_Perf_VM,int Nb_VM,String[] Etat_VM
		
		} catch (StaleProxyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			container.start();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
