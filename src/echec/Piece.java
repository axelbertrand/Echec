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
public abstract class Piece
{
    public Piece(Vector2 position, Couleur couleur)
    {
        this.position = position;
        this.couleur = couleur;
    }
    
    public Vector2 getPosition()
    {
        return this.position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }
    
    public Couleur getCouleur()
    {
        return this.couleur;
    }
    
    public String getImageStr()
    {
        String typePiece = this.getClass().getSimpleName() + this.couleur;
        
        return typePiece;
    }
    
    public List<Vector2> getCasesJouables(Plateau plateau)
    {
        List<Vector2> coups = getCoupsPossibles(plateau);
        coups.addAll(getCoupsSpeciaux(plateau));
        
        return coups;
    }
    
    protected List<Vector2> getCasesDirection(Plateau plateau, Vector2[] directions)
    {
        List<Vector2> cases = new ArrayList<>();

        Vector2 pos;
        for(Vector2 direction : directions)
        {
            if(direction.x == 0 && direction.y == 0)
                continue;
            
            pos = Vector2.add(this.position, direction);
            while(plateau.estCaseValide(pos))
            {
                Piece p = plateau.getCase(pos);
                if(p != null)
                {
                    if(!p.couleur.equals(this.couleur))
                    {
                        cases.add(pos);
                    }
                    break;
                }
                cases.add(new Vector2(pos));
                pos.add(direction);
            }
        }
        
        return cases;
    }
    
    protected abstract List<Vector2> getCoupsPossibles(Plateau plateau);
    protected abstract List<Vector2> getCoupsSpeciaux(Plateau plateau);
    
    @Override
    public boolean equals(Object o)
    {
    	if(this == o)
            return true;
    	
        if(o == null || !(o instanceof Piece))
            return false;
        
        Piece p = (Piece)o;
        return (this.position.equals(p.position) && this.couleur.equals(p.couleur));
    }
    
    protected Vector2 position;
    protected Couleur couleur;
}
