/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package echec;

/**
 *
 * @author Axel
 */
public class Joueur
{
	public Joueur(String nom, List<Piece> pieces)
    {
        this.nom = nom;
        this.pieces = pieces;
    }
	
    public Joueur(String nom, Piece[] pieces)
    {
        this.nom = nom;
        this.pieces = new ArrayList<>(pieces);
    }
    
    public Piece[] getPieces()
    {
        return this.pieces;
    }
    
    public void jouer(Vector2 iniPos, Vector2 nouvPos, Plateau plateau, Joueur j2)
    {
    	Piece piece = plateau.getCase(iniPos);

    	// La case iniPos est une pi�ce du joueur
    	if(pieces.contains(piece))
    	{
    		// La pi�ce jou�e par le joueur peut �tre d�plac�e � la case nouvPos
    		if(piece.getCasesJouables(plateau).contains(nouvPos))
    		{
    			// S'il s'agit d'une pi�ce de l'adversaire on la mange
    			if(j2.getPieces().contains(plateau.getCase(nouvPos)))
    			{
    				j2.remove(plateau.getCase(nouvPos));
    				plateau.setcase(nouvPos, null);
    			}
    			// Sinon on d�place simplement la pi�ce jou�e
    			else
    			{
    				plateau.setCase(iniPos, null);
    				plateau.setCase(nouvPos, piece);
    			}
    		}
    	}
    }
    
    private String nom;
    private List<Piece> pieces;
}