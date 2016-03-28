package agent;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
//import module.ModuleDecision;

public class AgentExterne extends Agent{


	@Override
		protected void setup(){
			
			System.out.println("Je suis l'Agent Externe. Mon nom est : "+this.getAID().getName());
		
	
	addBehaviour(new TickerBehaviour(this,30000) {
		
		@Override
		protected void onTick() {
			ACLMessage message = new ACLMessage(ACLMessage.INFORM);
			message.addReceiver(new AID("agentCommunication",AID.ISLOCALNAME));
			message.setContent("DOnnes relatives au Firewall virtuel");
			send(message);
			System.out.println("message envoy");
			
		}
	});		
	
	
	
	
	
	
	
	}
		@Override
		protected void takeDown() {
			// TODO Auto-generated method stub
			
			System.out.println("Destruction de l'agent");
			super.takeDown();
		}
		
	

}
