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
    public Joueur(String nom, Piece[] pieces)
    {
        this.nom = nom;
        this.pieces = pieces;
    }
    
    public Joueur(String nom)
    {
        this(nom, new Piece[16]);
    }
    
    public Joueur()
    {
        this("Joueur");
    }
    
    public Piece[] getPieces()
    {
        return this.pieces;
    }
    
    public void jouer(Vector2 iniPos, Vector2 nouvPos)
    {
        
    }
    
    private String nom;
    private Piece[] pieces;
}
