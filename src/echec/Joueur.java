/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package echec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        this.pieces = new ArrayList<>(Arrays.asList(pieces));
    }
    
    public List<Piece> getPieces()
    {
        return this.pieces;
    }
    
    public void jouer(Vector2 iniPos, Vector2 nouvPos, Plateau plateau, Joueur j2)
    {
    	Piece piece = plateau.getCase(iniPos);

    	// La case iniPos est une pièce du joueur
    	if(pieces.contains(piece))
    	{
    		// La pièce jouée par le joueur peut être déplacée à la case nouvPos
    		if(piece.getCasesJouables(plateau).contains(nouvPos))
    		{
    			// S'il s'agit d'une pi�ce de l'adversaire on la mange
    			if(j2.getPieces().contains(plateau.getCase(nouvPos)))
    			{
    				j2.getPieces().remove(plateau.getCase(nouvPos));
    				plateau.setCase(nouvPos, null);
    			}
    			// Sinon on déplace simplement la piàce jouée
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
