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
public abstract class Piece
{
    public Piece(Vector2 position)
    {
        this.position = position;
    }
    
    public Piece()
    {
        this(new Vector2(0, 0));
    }
    
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
    
    public Vector2[] getCasesJouables()
    {
        return null;
    }
    
    private Vector2 position;
}
