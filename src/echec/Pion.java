/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package echec;

import java.util.ArrayList;
import java.util.List;


public class Pion extends Piece
{
    public Pion(Vector2 position, Couleur couleur)
    {
        super(position, couleur);
    }
    
    @Override
    public List<Vector2> getCasesJouables(Plateau plateau)
    {
        List<Vector2> casesJouables = new ArrayList<>();

        if(couleur.equals(Couleur.BLANC))
        {
            if(plateau.getCase(new Vector2(position.x, position.y - 1)) == null)
            {
                casesJouables.add(new Vector2(position.x, position.y - 1));
            }

            if(position.y == 6 && plateau.getCase(new Vector2(position.x, position.y - 2)) == null)
            {
                casesJouables.add(new Vector2(position.x, position.y - 2));
            }

            if(plateau.getCase(new Vector2(position.x - 1, position.y - 1)) != null &&
                plateau.getCase(new Vector2(position.x - 1, position.y - 1)).couleur.equals(Couleur.NOIR))
            {
                casesJouables.add(new Vector2(position.x - 1, position.y - 1));
            }

            if(plateau.getCase(new Vector2(position.x + 1, position.y - 1)) != null &&
                plateau.getCase(new Vector2(position.x + 1, position.y - 1)).couleur.equals(Couleur.NOIR))
            {
                casesJouables.add(new Vector2(position.x + 1, position.y - 1));
            }
        }
        else
        {
            if(plateau.getCase(new Vector2(position.x, position.y + 1)) == null)
            {
                casesJouables.add(new Vector2(position.x, position.y + 1));
            }

            if(position.y == 1 && plateau.getCase(new Vector2(position.x, position.y + 2)) == null)
            {
                casesJouables.add(new Vector2(position.x, position.y + 2));
            }

            if(plateau.getCase(new Vector2(position.x - 1, position.y + 1)) != null &&
                plateau.getCase(new Vector2(position.x - 1, position.y + 1)).couleur.equals(Couleur.BLANC))
            {
                casesJouables.add(new Vector2(position.x - 1, position.y + 1));
            }

            if(plateau.getCase(new Vector2(position.x + 1, position.y + 1)) != null &&
                plateau.getCase(new Vector2(position.x + 1, position.y + 1)).couleur.equals(Couleur.BLANC))
            {
                casesJouables.add(new Vector2(position.x + 1, position.y + 1));
            }
        }

        return casesJouables;
    }
}
