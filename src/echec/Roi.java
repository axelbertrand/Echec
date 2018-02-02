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
    protected List<Vector2> getCoupsPossibles(Plateau plateau)
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
    protected List<Vector2> getCoupsSpeciaux(Plateau plateau)
    {
        List<Vector2> casesJouables = new ArrayList<>();
        
        if(aBouge)
            return casesJouables;
        
        Vector2 posD1 = Vector2.add(position, new Vector2(0, 1));
        Vector2 posD2 = Vector2.add(position, new Vector2(0, 2));
        Vector2 posDTour = Vector2.add(position, new Vector2(0, 3));
        
        Vector2 posG1 = Vector2.add(position, new Vector2(0, -1));
        Vector2 posG2 = Vector2.add(position, new Vector2(0, -2));
        Vector2 posG3 = Vector2.add(position, new Vector2(0, -3));
        Vector2 posGTour = Vector2.add(position, new Vector2(0, -4));
        
        // Petit Roque
        if(plateau.estCaseValide(posD1) && plateau.estCaseValide(posD2))
        {
            if(plateau.getCase(posD1) == null && plateau.getCase(posD2) == null)
            {
                // Ajouter condition : si les cases ne sont pas menacées
                
                if(plateau.estCaseValide(posDTour))
                {
                    Piece p = plateau.getCase(posDTour);
                    if(p instanceof Tour && !p.aBouge)
                    {
                        casesJouables.add(posD2);
                    }
                }
            }
        }
        
        // Petit Roque
        if(plateau.estCaseValide(posG1) && plateau.estCaseValide(posG2) && plateau.estCaseValide(posG3))
        {
            if(plateau.getCase(posG1) == null && plateau.getCase(posG2) == null && plateau.getCase(posG3) == null)
            {
                // Ajouter condition : si les cases ne sont pas menacées
                
                if(plateau.estCaseValide(posGTour))
                {
                    Piece p = plateau.getCase(posGTour);
                    if(p instanceof Tour && !p.aBouge)
                    {
                        casesJouables.add(posG2);
                    }
                }
            }
        }
        
        return casesJouables;
    }
}
