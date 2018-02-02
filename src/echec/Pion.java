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
    protected List<Vector2> getCoupsPossibles(Plateau plateau)
    {
        List<Vector2> casesJouables = new ArrayList<>();
        
        Vector2 avance1 = new Vector2(-1, 0);
        Vector2 avance2 = new Vector2(-2, 0);
        Vector2 priseGauche = new Vector2(-1, -1);
        Vector2 priseDroite = new Vector2(-1, 1);

        if(couleur.equals(Couleur.BLANC))
        {
            avance1.x *= -1;
            avance2.x *= -1;
            priseGauche.x *= -1;
            priseDroite.x *= -1;
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

        if(!aBouge && doublePasPossible)
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
    protected List<Vector2> getCoupsSpeciaux(Plateau plateau)
    {
        List<Vector2> casesJouables = new ArrayList<>();
        
        if((couleur.equals(Couleur.BLANC) && position.x != 4) || (couleur.equals(Couleur.NOIR) && position.x != 3))
            return casesJouables;
        
        Vector2 enPassantGauche = new Vector2(-1, -1);
        Vector2 enPassantDroite = new Vector2(-1, 1);
        Vector2 posGauche = new Vector2(0, -1);
        Vector2 posDroite = new Vector2(0, 1);
        
        if(couleur.equals(Couleur.BLANC))
        {
            enPassantGauche.x *= -1;
            enPassantDroite.x *= -1;
        }
        
        Vector2 pos = Vector2.add(position, posGauche);
        Vector2 enPassant = Vector2.add(position, enPassantGauche);
        for(int i = 0; i < 2; i++)
        {
            if(plateau.estCaseValide(enPassant))
            {
                if (plateau.getCase(enPassant) == null)
                {
                    if (plateau.estCaseValide(pos))
                    {
                        Piece p = plateau.getCase(pos);
                        if (p instanceof Pion)
                        {
                            if (!p.getCouleur().equals(couleur))
                            {
                                if(((Pion) p).doublePas)
                                {
                                    casesJouables.add(enPassant);
                                }
                            }
                        }
                    }
                }
            }
            pos = Vector2.add(position, posDroite);
            enPassant = Vector2.add(position, enPassantDroite);
        }
        
        return casesJouables;
    }
    
    public void setDoublePas(boolean dp)
    {
        this.doublePas = dp;
    }
    
    private boolean doublePas = false;
}
