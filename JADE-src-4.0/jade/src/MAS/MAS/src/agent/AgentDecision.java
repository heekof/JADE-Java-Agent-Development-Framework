package agent;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
//import module.ModuleDecision;

public class AgentDecision extends Agent{
	

	@Override
	protected void setup(){
		
		System.out.println("Je suis l'Agent de dcision. Mon nom est : "+this.getAID().getName());
		
		addBehaviour( new TickerBehaviour(this,5000) {
			private String Var=null;
			private String Ajour = null;
			@Override
			public void onTick() {
				
				System.out.println("L'agent de dcision lit le module de Dcision !");
				Var=ModuleDecision.getInstance().getAlert();
				if(Var != "" && Var  != Ajour){
				System.out.println("Mise  jour la var est : "+Var);
				Ajour=Var;
				}
				else {
					System.out.println("Pas de mise  jour !");
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
