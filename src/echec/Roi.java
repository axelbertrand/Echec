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
public class Roi extends Piece
{
    public Roi(Vector2 position)
    {
        super(position);
    }
    
    @Override
    public Vector2[] getCasesJouables()
    {
        return null;
    }
}
