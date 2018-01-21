/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package echec;

import java.util.List;


/**
 *
 * @author Axel
 */
public abstract class Piece
{
    public Piece(Vector2 position, Couleur couleur)
    {
        this.position = position;
        this.couleur = couleur;
    }
    
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
    
    public abstract List<Vector2> getCasesJouables(Plateau plateau);
    
    public String getImage()
    {
        String typePiece = this.getClass().getSimpleName() + this.couleur;
        
        return typePiece;
    }
    
    @Override
    public boolean equals(Object o)
    {
    	if(this == o)
            return true;
    	
        if(o instanceof Piece)
        {
            Piece p = (Piece)o;
            return (this.position.equals(p.position) && this.couleur.equals(p.couleur));
        }
        else
        {
            return false;
        }
    }
    
    protected Vector2 position;
    protected Couleur couleur;
}
