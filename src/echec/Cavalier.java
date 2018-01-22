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
public class Cavalier extends Piece
{
    public Cavalier(Vector2 position, Couleur couleur)
    {
        super(position, couleur);
    }
    
    @Override
    public List<Vector2> getCoupsPossibles(Plateau plateau)
    {
        List<Vector2> casesJouables = new ArrayList<>();
        
        Vector2[] posTest = {
            new Vector2(1, 2),
            new Vector2(2, 1),
            new Vector2(-1, 2),
            new Vector2(-2, 1),
            new Vector2(-1, -2),
            new Vector2(-2, -1),
            new Vector2(1, -2),
            new Vector2(2, -1)
        };
        
        for(Vector2 pos : posTest)
        {
            pos.add(position);
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

        return casesJouables;
    }
    
    @Override
    public List<Vector2> getCoupsSpeciaux(Plateau plateau)
    {
        return new ArrayList<>();
    }
}
