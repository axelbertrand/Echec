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
    
    public String getNom()
    {
        return this.nom;
    }
    
    public List<Piece> getPieces()
    {
        return this.pieces;
    }
    
    public boolean jouer(Vector2 iniPos, Vector2 nouvPos, Plateau plateau, List<Piece> piecesJ2)
    {
        // S'il s'agit d'une pièce de l'adversaire on la mange
        Piece p = plateau.getCase(nouvPos);
        if(piecesJ2.contains(p))
        {
            piecesJ2.remove(p);
        }
        
        return plateau.bougerPiece(iniPos, nouvPos, piecesJ2);
    }
    
    private String nom;
    private List<Piece> pieces;
}
