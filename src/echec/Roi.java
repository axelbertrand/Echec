/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package echec;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Axel
 */
public class Roi extends Piece
{
    public Roi(Vector2 position, Couleur couleur)
    {
        super(position, couleur);
    }
    
    @Override
    public List<Vector2> getCoupsPossibles(Plateau plateau)
    {
        List<Vector2> casesJouables = new ArrayList<>();
        
        for(int i = -1; i <= 1; i++)
        {
            for(int j = -1; j <= 1; j++)
            {
                if(i == 0 && j == 0)
                    continue;
                
                Vector2 pos = Vector2.add(position, new Vector2(i, j));
                if(plateau.estCaseValide(pos))
                {
                    Piece p = plateau.getCase(pos);
                    if(p != null)
                    {
                        if(!p.couleur.equals(this.couleur))
                        {
                            casesJouables.add(pos);
                        }
                    }
                    else
                    {
                        casesJouables.add(pos);
                    }
                }
            }
        }

        return casesJouables;
    }
    
    @Override
    public List<Vector2> getCoupsSpeciaux(Plateau plateau)
    {
        return new ArrayList<>();
    }
}
