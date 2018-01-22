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
    public List<Vector2> getCoupsPossibles(Plateau plateau)
    {
        List<Vector2> casesJouables = new ArrayList<>();
        
        Vector2 avance1 = new Vector2(0, 1);
        Vector2 avance2 = new Vector2(0, 2);
        Vector2 priseGauche = new Vector2(-1, 1);
        Vector2 priseDroite = new Vector2(1, 1);
        int posDoublePas = 1;

        if(couleur.equals(Couleur.BLANC))
        {
            avance1.y *= -1;
            avance2.y *= -1;
            priseGauche.y *= -1;
            priseDroite.y *= -1;
            posDoublePas = 6;
        }
        
        avance1.add(position);
        avance2.add(position);
        boolean doublePasPossible = false;
        if(plateau.estCaseValide(avance1))
        {
            if(plateau.getCase(avance1) == null)
            {
                casesJouables.add(avance1);
                doublePasPossible = true;
            }
        }

        if(position.y == posDoublePas && doublePasPossible)
        {
            if(plateau.estCaseValide(avance2))
            {
                if(plateau.getCase(avance2) == null)
                {
                    casesJouables.add(avance2);
                }
            }
        }
        
        priseGauche.add(position);
        priseDroite.add(position);
        Vector2 prise = priseGauche;
        for(int i = 0; i < 2; i++)
        {
            if(plateau.estCaseValide(prise))
            {
                Piece p = plateau.getCase(prise);
                if(p != null && !p.couleur.equals(this.couleur))
                {
                    casesJouables.add(prise);
                }
            }
            prise = priseDroite;
        }

        return casesJouables;
    }
    
    @Override
    public List<Vector2> getCoupsSpeciaux(Plateau plateau)
    {
        return new ArrayList<>();
    }
}
