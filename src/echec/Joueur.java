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
    
    public void jouer(Vector2 iniPos, Vector2 nouvPos, Plateau plateau, List<Piece> piecesJ2)
    {
        // S'il s'agit d'une pièce de l'adversaire on la mange
        if(piecesJ2.contains(plateau.getCase(nouvPos)))
        {
            piecesJ2.remove(plateau.getCase(nouvPos));
        }
        
        Piece p = plateau.getCase(iniPos);
        plateau.setCase(iniPos, null);
        plateau.setCase(nouvPos, p);
        p.setPosition(nouvPos);
    }
    
    private String nom;
    private List<Piece> pieces;
}
