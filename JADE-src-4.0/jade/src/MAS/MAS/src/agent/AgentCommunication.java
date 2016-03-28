package agent;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
//import module.ModuleDecision;


public class AgentCommunication extends Agent {
	@Override
	protected void setup(){
		
		System.out.println("Je suis l'Agent de Communication. Mon nom est : "+this.getAID().getName());
		
		// Ajout de comportement 
		addBehaviour(new TickerBehaviour(this,20000) {
			private int compteur=0;
			@Override
			protected void onTick() {
				System.out.println("tentative de communication "+compteur);
				compteur++;
			}
		});
	
		
	// ajout deuxieme behav
		addBehaviour(new CyclicBehaviour() {
			private int i=0;			
			@Override
			public void action() {
			System.out.println("Attente de message N"+i);	
			i++;
			ACLMessage message=receive();
			if(message!= null){
				System.out.println("rception de notification "+message.getContent());
				//ModuleDecision.getInstance().setAlert(message.getContent());
			}
			else{
				block();
			}
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
