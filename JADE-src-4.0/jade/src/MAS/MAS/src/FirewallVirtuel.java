import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.*;

public class FirewallVirtuel {

	public static void main(String[] args) {

		Runtime rt = Runtime.instance();
		ProfileImpl pc=new ProfileImpl(false);
		// maincontainer ==> localhost
		pc.setParameter(ProfileImpl.MAIN_HOST, "localhost");
		AgentContainer ac=rt.createAgentContainer(pc);
		// controlleur agent
		try {
			AgentController agentcontroller=ac.createNewAgent("AgentExterne", "agent.AgentExterne", new Object[]{});
		    agentcontroller.start();
		} catch (StaleProxyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	 
		
		try {
			ac.start();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
