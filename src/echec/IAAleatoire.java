/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package echec;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Axel
 */
public class IAAleatoire extends IAAbstraite
{
    public IAAleatoire(Couleur couleur)
    {
        super(couleur);
        rnd = new Random();
    }
    
    @Override
    public Vector2[] getCoupIA(Plateau plateau)
    {
        List<Piece> pieces = plateau.getPieces(this.couleur);
        Vector2[] tabPos = new Vector2[2];
        
        int x, y;
        Piece p;
        do
        {
            x = rnd.nextInt(8);
            y = rnd.nextInt(8);
            tabPos[0] = new Vector2(x, y);
            p = plateau.getCase(tabPos[0]);
        }
        while(p == null || !pieces.contains(p));
        
        List<Vector2> coupsJouables = p.getCoupsPossibles(plateau);
        do
        {
            x = rnd.nextInt(8);
            y = rnd.nextInt(8);
            tabPos[1] = new Vector2(x, y);
        }
        while(!coupsJouables.contains(tabPos[1]));
        
        return tabPos;
    }
    
    private Random rnd;
}
