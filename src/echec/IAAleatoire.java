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
        List<Vector2> coupsJouables;
        int i = 0;
        int maxIteration = 10;
        do
        {
            x = rnd.nextInt(8);
            y = rnd.nextInt(8);
            tabPos[0] = new Vector2(x, y);
            p = plateau.getCase(tabPos[0]);
            if(p != null)
            {
                coupsJouables = p.getCoupsPossibles(plateau);
                do
                {
                    x = rnd.nextInt(8);
                    y = rnd.nextInt(8);
                    tabPos[1] = new Vector2(x, y);
                    i++;
                }
                while(!coupsJouables.contains(tabPos[1]) || i < maxIteration);
            }
        }
        while(!pieces.contains(p));
        
        return tabPos;
    }
    
    private Random rnd;
}
