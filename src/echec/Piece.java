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
    public Piece(Vector2 position, int couleur)
    {
        this.position = position;
    }
    
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
    
    public abstract List<Vector2> getCasesJouables(Plateau plateau);
    
    @Override
    public boolean equals(Object o)
    {
    	if(this == o)
            return true;
    	
        if(o instanceof Piece)
        {
            Piece p = (Piece)o;
            return (this.position.x == p.position.x && this.position.y == p.position.y);
        }
        else
        {
            return false;
        }
    }
    
    protected Vector2 position;
    protected int couleur;
}
