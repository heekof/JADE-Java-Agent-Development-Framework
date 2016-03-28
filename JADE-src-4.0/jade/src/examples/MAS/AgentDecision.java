
package agent;
import jade.core.Agent;
import java.lang.Object;
import java.io.BufferedReader;
import java.io.InputStreamReader;




//import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
//import jade.lang.acl.ACLMessage;
import module.ModuleDecision;




/*

Class : AgentDecision cet agent est le plus important de notre MAS (System-multi agent) elle a pour
foncrion de prendre la decision de bifurquer le flux ou comment et avec quelle strategie le bifurquer

On parle alors de Load Balancing 

Cette classe herite de la classe agent : structure obligatiore car on utilise le middleware JADE

La principale difficulte etant  la fonction qui gere l'algorithme, la strategie et le decisionel 



middleware : JADE
author : BENDRISS Jaafar bendriss-jaafar@live.fr UTT 17/04/2014

*/



public class AgentDecision extends Agent{
	
// Variable, tableau deux dimensions pour la liste de priorité : voir documentation pour plus d'informations
	private String[][] ListePriorite=null; 
	// classe singleton qui represente le module calcul/Algorithme
// C'est cette classe qui gere la strategie a adoptee et l'application de l'algorithme 
	private static class ModuleCalcul{
/** Constructeur privé */
	  private static volatile ModuleCalcul instance = null;
	private ModuleCalcul()
	{}
 
	/** Instance unique pré-initialisée */
	private static ModuleCalcul INSTANCE = new ModuleCalcul();
 
	/** Point d'accès pour l'instance unique du singleton */


  public final static ModuleCalcul getInstance() {
        //Le "Double-Checked Singleton"/"Singleton doublement v▒rifi▒" permet
        //d'▒viter un appel co▒teux ▒ synchronized,
        //une fois que l'instanciation est faite.
        if (ModuleCalcul.instance == null) {
           // Le mot-cl▒ synchronized sur ce bloc emp▒che toute instanciation
           // multiple m▒me par diff▒rents "threads".
           // Il est TRES important.
           synchronized(ModuleCalcul.class) {
             if (ModuleCalcul.instance == null) {
                 ModuleCalcul.instance = new ModuleCalcul();
             }
           }
        }
        return ModuleCalcul.instance;
    }


	// fonction pour debugage
	public void affiche(){System.out.print("\n Je suis le singleton Module Calcul \n"); }
	
	// cette fonction repond a la question quivante : Quel formule doit-on utiliser ?
	public String formuleLoadBalancing(String[] ligneListe){ return " formule "; }
	// cette fonction repond a la question quivante : Quel pourcentage doit-on affecter a cette machine ?
	public String pourcentageLoadBalancing(String[] ligneListe){ return " X % "; }


	}

	public void listePriorite(String entree){

	


		}
	@Override
/*
Setup : est une fonction pr▒definie dans le classe Agent : c'est le thread principale de notre agent

a noter que chaque agent est considerer comme un seul thread afin de minimiser le besoin en ressources

*/

	protected void setup(){
		// Debugage et connaitre le nom de l agent executer
		System.out.println("Processus Agent de décision My name is : "+this.getAID().getName());
		// lecture et mise à jour de la liste de priorirté toute les X secondes
		addBehaviour( new TickerBehaviour(this,5000) {
		// variable Var tableau deux dimension : retient la liste des parametres du module de decision
			private String[][] Var=null;
		// Ajour variable temoin pour l'affichage seulement des nouvelles valeurs
			private String Ajour = null;
			@Override
			public void onTick() {
				

	// debugage
			System.out.println("\n L'agent de decision : Lecture  le module de Decision \n");
			
// Lecture du module de decision 
				Var=ModuleDecision.getInstance().getAlert();
				if(Var != null && Var[0][1]  != Ajour){
				//System.out.println("Debug : \n Mise à jour par l'agent de decision la var es \n  var01: "+Var[0][1]+" \n  var00 "+Var[0][0]+" \n  var02 "+Var[0][2]+"\n");
				Ajour=Var[0][1];
				//AgentDecison A=new AgentDecision();
				//A.listePriorite(Var);
				
				fonctionPriorite(Var);
				
				
				
				}
				else {
					System.out.println("Agent de decision : Update not found ");
					
					AgentDecision A = new AgentDecision(); 
					//System.out.println(A.Memory()+" Mo");
					A.CPU();
					// mettre dans la liste de priorite de l agent les parametres lu du module de 
// de decision, cependant ces derneires doivent etre organisees, typiquement on ajoute deux nouveaux champs 
// grace a la classe interne MoculeCalcul, Note et formule 
					if(Var != null )				
						ListePriorite=fonctionPriorite(Var);

					//A.NETWORK();
				}
			}

/*

cette fonction est decrite plus haut 

return : Tableau a chaine de caracteres sur deux dimensions 

Author : BENDRISS Jaafar le 17/04/2014 bendriss-jaafar@live.fr at LIP6
*/
			public String[][] fonctionPriorite(String[][] Var){ 
// Initialistion du singleton  MoculeCalcul
			AgentDecision.ModuleCalcul O =  new AgentDecision.ModuleCalcul();			
// Initialisation de la fonction de priorite 
         		ListePriorite = new String[Var.length][5];
			int i,j;    
// parcours du tableau Var ou se trouve les elements brutes 
	for(i=0;i<Var.length;i++){ for(j=0;j<Var[i].length;j++) 
	  if(Var[i][j] != null){ 
	
// traitement liste de priorite :
// Simple affectation
	ListePriorite[i][j]  = Var[i][j]; 

	  System.out.println("Ceci est ma fonction   var "+i+" "+j+"  "+ListePriorite[i][j]+"  \n ");
	 }


// Ici l'ajout des deux champs decrit un peu plus haut 
	ListePriorite[0][3]  = O.formuleLoadBalancing(Var[0]);
	ListePriorite[0][4]  = O.pourcentageLoadBalancing(Var[0]);
}// fermeture boucle i

// ici retour  du pointeur
return ListePriorite;

}// fin fonction Priorite
	});

}        
// Max Memory de la machine java	
	public String  MaxMemory(){
		 /* This will return Long.MAX_VALUE if there is no preset limit */
		
	    long maxMemory = Runtime.getRuntime().maxMemory();
	    /* Maximum amount of memory the JVM will attempt to use */
	    return ("Maximum memory (bytes): " + 
	        (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));
	}
	public String totalMemory(){
		int Mega=1048576;
		 /* Total memory currently available to the JVM */
	    return ("Total memory available to JVM (bytes): " + 
	        Runtime.getRuntime().totalMemory()/Mega);
	    //********************************************************************************
		
	}
	@Override
	protected void takeDown() {
		// TODO Auto-generated method stub
		
		System.out.println("Destruction de l'agent");
		super.takeDown();
	}
	
	//***********************************************************************
	
/*
fonction qui execute une commande systeme afin de connaitre la moyenne du CPU sur 1,5,et 15 minutes
elle donne aussi l id de la derneire appli ouverte
Author : source internet forum  modifier par BENDRISS Jaafar
*/
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

/*


fonction qui execute la commande system ping afin de connaitre l'etat du  reseau
Authour (source) : forum developper modification BENDRISS JAAFAR

*/
	public void NETWORK(){
	    String s;
                    try {
                        Process ps = Runtime.getRuntime().exec("ping -c 5 -q 127.0.0.1 ");
                        BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
                        while((s = br.readLine()) != null) {
                            System.out.println(s);
                        }
                    }
                    catch( Exception ex ) {
                        System.out.println(ex.toString());
                    }




	}	
	
	//************************************************************************
	

}

// END
