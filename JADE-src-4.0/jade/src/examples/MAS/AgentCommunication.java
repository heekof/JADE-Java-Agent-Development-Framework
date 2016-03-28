package agent;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
// Appel de la classe ModuleDecision qui permettera d'instancier le singleton.
import module.ModuleDecision;
/*

Class : AgentCommunication cet agent est charge d'organiser les informations recus par les agents  externe

et de les mettre dans le module de d√cison : ModuleDecison  singleton 

Cette classe herite de la classe agent : structure obligatiore car on utilise le middleware JADE

La principale difficulte etant l'actualisation des donnees recus.



middleware : JADE 
author : BENDRISS Jaafar bendriss-jaafar@live.fr UTT 17/04/2014
Hours of work : h 
*/
public class AgentCommunication extends Agent {

// Le message recu sera affecter a cette varianle
			private String messageRecu;
			// Tableau doit etre dynamique !
			private String[]  tableElement;// = new String[5][5];


/*
Setup : est une fonction pr√definie dans le classe Agent : c'est le thread principale de notre agent 

a noter que chaque agent est considerer comme un seul thread afin de minimiser le besoin en ressources

*/
	@Override
	protected void setup(){
		
		System.out.println("Je suis l'Agent de Communication. Mon nom est : "+this.getAID().getName());
		
//		 Ajout de comportement  toutes les 2 sec
//  Code qui s'execute toute les deux secondes 
		addBehaviour(new TickerBehaviour(this,60000) {
			private int compteur=0;
			@Override
			protected void onTick() {
				System.out.println("agent  communication : Ceci est le push N "+compteur+"\n");
				compteur++;
			}
		});

		
	// ajout deuxieme behavior qui lit  les messages de maniere cyclique : boucle infinie 
		addBehaviour(new CyclicBehaviour() {
// i valeur qui compte le nombre de message recu pour des raisons suivi et de debugage
			private int i=0;

			@Override
			public void action() {
			System.out.println(" \n L'agent de communication se bloque en attendant un nouveau message; N"+i);	
			i++;
// La communication entre agent se traite via ACLmessage thanks to youtube tutorial on JADE
			ACLMessage message=receive();
			if(message!= null){
				System.out.println("L'agent de communication a recu un message ! \n");
// Ligne permet de reconnaitre le sender pour traiter en fonction : its for a futur use and for debug reasons
				System.out.println(" The sender is :"+message.getSender().getName()+" \n ");
				messageRecu=message.getContent();
	//			s=A.getInformation(st1);

// tableElement contient les paramtres du message recu, trait√s et organis√©s	
				tableElement = organiserElements(messageRecu);





// Debugage : affichage	
			System.out.print("\n ****  L agent de communication mets a jour le module de decision ***** \n  "); 
// Ecriture dans le module de decision 
			ModuleDecision.getInstance().setAlert(tableElement);

					}
			else{

				block();

					}
			}
/* 

	MaTable est un tableau de deux dimensions qui n'a que l'indice 0:
 c'est a dire que ca taille est egale a : maTable[1][3];


le tableau a deux dimensions: Module[][] est l tableau qui existe dans le module de decision ce tableau
est lu pour qu'il puisse etre comparer avec les nouveaux elements de maTables[] 

return true : ecrire dans le module
	false : ne pas ecrire dans le module 

author : BENDRISS Jaafar bendriss-jaafar@live.fr UTT 17/04/2014

Cette fonction n est plus utilisee

*/

/*
		public boolean checkUpdate(String[] maTable,String[][] Module){
// Si le module de decision est vide alors ne rien faire et retourn√© vrai pour ecrire directement
		if(Module != null && Module.length != 0){
		// Parcours de Module[][]
                for(int i=0;i<Module.length;i++)
		for(int j=i;j<Module[i].length;j++)	
			{      //debugage : affichage
				System.out.print(" \n Je compare "+Module[i][0]+"-"+maTable[0][0]+"  \n "); 
 				// J ai eu un probleme pour comparer deux chaines de caracteres dans  un tableau
// a deux dimensions : ce qui suit est l'astuce pour y remedier 
				String a,b;
				a=Module[i][0];
				b=maTable[0][0];
// Si l'adresse IP est la meme retourne Vrai 
					if( a.equals(b) )
						{
// Dans ce cas il faut voir si les iformations ont chang√© ou pas 
		  				if(compare(Module[i],maTable[0]))
						return false;
						else
							{
// Attention ici on ne veut pas ecrire mais plutot modifier !!
// pour ce faire on choisi d'ecraser l'ancienne valeur
// ***************************************************A faire le 18/04*****************************************	
						//ModuleDecision.setAlert[i]=maTable[0];			
						return false;
							}
						}
			}
							}

// Valider l'ecriture dans le module 
						return true;
										}

*/

// fonction compare les deuc tableaux String							
 public	 boolean compare(String[] T1, String[] T2){return true;}		


/*
cette fonction prends les informations les plus pertinentes d'un ping fait par l'agent externe 

return pointeur sur un tableau String √† 2 dimensions 
author : BENDRISS Jaafar bendriss-jaafar@live.fr UTT 17/04/2014

*/
		public String[] organiserElements(String message){ 
				// declaration initialisations
				String st1,s2,s3,Ip;
				String[][] Table=new String[1][3];
				
				st1="toto";
				st1=message;
				String[] tokens = st1.split(",");
				message=tokens[2];
				s2=tokens[3];
                                 String[] tokens2=s2.split("---");
				s3=tokens2[1];
				String[] Id=s3.split("ping");
				Ip=Id[0];
				st1=Ip+"="+message;
				Table[0][0]=Ip.trim();
				Table[0][1]=message;
				// Oussama doit m'envoyer cette variable 
				Table[0][2]=" 2 Mbps";
								

					return Table[0]; }


		});
	}
	@Override

/*
Fonction definie par defaut :  elle s execute avant la mort (destruction)  de l agent

*/
	protected void takeDown() {
		// TODO Auto-generated method stub
		
		System.out.println("ag com : Destruction de l'agent");
		super.takeDown();
	}




	
}
