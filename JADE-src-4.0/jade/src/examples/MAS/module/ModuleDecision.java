package module;

import java.lang.Object;
import java.lang.System;




/*
Class : cette classe represente le module de decision , c est un singletion , qui ne peut etre instancier 
qu'une seule fois ; Elle contient toutes les informations necessaires sur les Virtuels firewalls connecte au MAS

Par la suite on y integrera des strategies 

functions :
setParametres :
setAlerete :
getParametres :
getAlerte :

Author : BENDRISS JAAFAR le 17/04/2014 bendriss-jaafar@live.fr
hours of work: h


*/
public class ModuleDecision {
	
// la variable S n'a pas d'utilitÃ© particuliere sauf pour des tests elle sera supprimÃ©epar la suite 
	private String S;

//Tab represente  la liste des parametres ainsi que leurs valeures respectives 	
	private String Tab[][];
	private int Nb_VM=2;
	private String Etat_VM [];
	// L'utilisation du mot clé volatile permet, en Java version 5 et supérieur, 
    // permet d'éviter le cas où "Singleton.instance" est non-nul,
    // mais pas encore "réellement" instancié.
    // De Java version 1.2 à 1.4, il est possible d'utiliser la classe ThreadLocal.
    private static volatile ModuleDecision instance = null;
// Constructeur de la classe singleton ModuleDecision
	private ModuleDecision(){

	 Tab=new String[Nb_VM][3];

				}
	
	
	/**
     * Méthode permettant de renvoyer une instance de la classe Singleton
     * @return Retourne l'instance du singleton.
     */
    public final static ModuleDecision getInstance() {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
        //d'éviter un appel coûteux à synchronized, 
        //une fois que l'instanciation est faite.
        if (ModuleDecision.instance == null) {
           // Le mot-clé synchronized sur ce bloc empêche toute instanciation
           // multiple même par différents "threads".
           // Il est TRES important.
           synchronized(ModuleDecision.class) {
             if (ModuleDecision.instance == null) {
            	 ModuleDecision.instance = new ModuleDecision();
             }
           }
        }
        return ModuleDecision.instance;
    }
	/*

Fonction qui a pour but d inserer les parametres recu par l agent de communication  dans Tab 

Sachant que je ne recoit que des lignes, c'est a dire des tableaux unidimensionnels 

Author BENDRISS JAAFAR
*/



	public void setAlert(String Tab[])

	{

//Si c est la premiere fois que je recois des donnees  j'affecte directement la ligne au tableau
		if(this.Tab[0][0] == null)
		{

			  this.Tab[0]=Tab;
		}
		else
		{
			   this.Tab=add(this.Tab,Tab);

		}



	}
/*
Fonction qui Concatene deux tableaux: un deux dimensions avec un unidim



Tab2dim <-- Tab

si l adresse ip n existe pas dans Tab2dim alors l ajouter 
sinon si les element sont differents alors ecraser la ligne par Tab 
sinon ne pas ajouter la ligne 


Author BENDRISS JAAFAR 
*/
	public String[][] add(String[][] Tab2dim,String[] Tab)
{

// Parcours du module 
	for(int i=0;i<Tab2dim.length;i++)
		{

if(Tab2dim[i][0] != null)
   { 
		String a=Tab2dim[i][0];
		String b=Tab[0];		
			if(a.equals(b))// Si la meme @IP alors je verifie les autres parametres 
			{

System.out.println("     meme adresse IP !    ");

		for(int j=1;j<Tab2dim[i].length;j++)
				{
		 a= Tab2dim[i][j];
		 b=Tab[j];
					if(!a.equals(b))// Si qu'un seul element different alors ecraser et sortir
							{
								Tab2dim[i] = Tab;
								return Tab2dim;
							}
				}
				return Tab2dim;

			}
   }	

}

// L adresse ip n existe pas alors je l a rajoute 

// debugage
System.out.println("     Am I Here ?    ");


				Tab2dim[Tab2dim.length-1] = Tab;
				return Tab2dim;

	}



/*
return : la liste des elements stockes
*/
	public String[][] getAlert(){
	   return	this.Tab;
	}


}
