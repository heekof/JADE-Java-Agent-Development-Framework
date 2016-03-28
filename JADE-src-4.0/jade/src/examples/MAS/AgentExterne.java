package agent;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import module.ModuleDecision;
import java.io.BufferedReader;
import java.io.InputStreamReader;




public class AgentExterne extends Agent{


	@Override
		protected void setup(){
			
			System.out.println("Je suis l'Agent Externe. Mon nom est : "+this.getAID().getName());
		
	
	addBehaviour(new TickerBehaviour(this,10000) {
		
		@Override
		protected void onTick() {
			ACLMessage message = new ACLMessage(ACLMessage.INFORM);
			message.addReceiver(new AID("agentCommunication",AID.ISLOCALNAME));
			message.setContent("DOnnees relatives au Firewall virtuel");
			send(message);
			System.out.println("message envoye");
			AgentExterne A = new AgentExterne();
			A.NETWORK();
			
		}
	});		
	
	
	
	
	
	
	
	}
		@Override
		protected void takeDown() {
			// TODO Auto-generated method stub
			
			System.out.println("Destruction de l'agent");
			super.takeDown();
		}
		
	



        public void CPU(){
                 String s;
                    try {
                        Process ps = Runtime.getRuntime().exec("cat /proc/loadavg");
                        BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
                        while((s = br.readLine()) != null) {
                            System.out.println(s);
                        }
                    }
                    catch( Exception ex ) {
                        System.out.println(ex.toString());
                    }
        }
        public void NETWORK(){
            String s;
                    try {
                        Process ps = Runtime.getRuntime().exec("ping -c 2 -q 127.0.0.1 ");
                        BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
                        while((s = br.readLine()) != null) {
                            System.out.println(s);
                        }
                    }
                    catch( Exception ex ) {
                        System.out.println(ex.toString());
                    }




        }





}
