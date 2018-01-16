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
public class Plateau
{    
    public Plateau(List<Piece> pieces)
    {
    	this.grille = new Piece[8][8];
    	
    	for(Piece piece : pieces)
    	{
    		this.grille[piece.getPosition().x][piece.getPosition().y] = piece;
    	}
    }
    
    public Piece getCase(Vector2 pos)
    {
        return this.grille[pos.x][pos.y];
    }
    
    public void setCase(Vector2 pos, Piece piece)
    {
    	this.grille[pos.x][pos.y] = piece;
    }
    
    private Piece[][] grille;
}
