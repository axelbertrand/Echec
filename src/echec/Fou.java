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
public class Fou extends Piece
{
    public Fou(Vector2 position, Couleur couleur)
    {
        super(position, couleur);
    }
    
    @Override
    protected List<Vector2> getCoupsPossibles(Plateau plateau)
    {
        List<Vector2> casesJouables = new ArrayList<>();

        casesJouables.addAll(getCasesDirection(plateau, new Vector2[] {
            new Vector2(1, 1),
            new Vector2(1, -1),
            new Vector2(-1, -1),
            new Vector2(-1, 1)
        }));

        return casesJouables;
    }
    
    @Override
    protected List<Vector2> getCoupsSpeciaux(Plateau plateau)
    {
        return new ArrayList<>();
    }
}
