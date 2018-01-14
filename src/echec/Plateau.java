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
    public Plateau()
    {
        this.grille = new Piece[8][8];
    }
    
    public int getCaseCliquee(Vector2 pos)
    {
        return 0;
    }
    
    private Piece[][] grille;
}
