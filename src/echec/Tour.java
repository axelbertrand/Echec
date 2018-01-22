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
public class Tour extends Piece
{
    public Tour(Vector2 position, Couleur couleur)
    {
        super(position, couleur);
    }
    
    @Override
    public List<Vector2> getCoupsPossibles(Plateau plateau)
    {
        List<Vector2> casesJouables = new ArrayList<>();

        casesJouables.addAll(getCasesDirection(plateau, new Vector2[] {
            new Vector2(0, 1),
            new Vector2(1, 0),
            new Vector2(0, -1),
            new Vector2(-1, 0)
        }));

        return casesJouables;
    }
    
    @Override
    public List<Vector2> getCoupsSpeciaux(Plateau plateau)
    {
        return new ArrayList<>();
    }
}
