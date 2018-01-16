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
    public Roi(Vector2 position, int couleur)
    {
        super(position, couleur);
    }
    
    @Override
    public List<Vector2> getCasesJouables(Plateau plateau)
    {
    	List<Vector2> casesJouables = new ArrayList<>();
    	int autreCouleur = (couleur + 1) % 2;
    	
    	if(plateau.getCase(new Vector2(position.x - 1, position.y - 1)) == null ||
    		plateau.getCase(new Vector2(position.x - 1, position.y - 1)) != null &&
    		plateau.getCase(new Vector2(position.x - 1, position.y - 1)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector2(position.x - 1, position.y - 1));
    	}
    	
    	if(plateau.getCase(new Vector2(position.x, position.y - 1)) == null ||
    		plateau.getCase(new Vector2(position.x, position.y - 1)) != null &&
    		plateau.getCase(new Vector2(position.x, position.y - 1)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector2(position.x, position.y - 1));
    	}
    	
    	if(plateau.getCase(new Vector2(position.x + 1, position.y - 1)) == null ||
    		plateau.getCase(new Vector2(position.x + 1, position.y - 1)) != null &&
    		plateau.getCase(new Vector2(position.x + 1, position.y - 1)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector2(position.x + 1, position.y - 1));
    	}
    	
    	if(plateau.getCase(new Vector2(position.x + 1, position.y)) == null ||
    		plateau.getCase(new Vector2(position.x + 1, position.y)) != null &&
    		plateau.getCase(new Vector2(position.x + 1, position.y)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector2(position.x + 1, position.y));
    	}
    	
    	if(plateau.getCase(new Vector2(position.x + 1, position.y + 1)) == null ||
    		plateau.getCase(new Vector2(position.x + 1, position.y + 1)) != null &&
    		plateau.getCase(new Vector2(position.x + 1, position.y + 1)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector2(position.x + 1, position.y + 1));
    	}
    	
    	if(plateau.getCase(new Vector2(position.x, position.y + 1)) == null ||
    		plateau.getCase(new Vector2(position.x, position.y + 1)) != null &&
    		plateau.getCase(new Vector2(position.x, position.y + 1)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector2(position.x, position.y + 1));
    	}
    	
    	if(plateau.getCase(new Vector2(position.x - 1, position.y + 1)) == null ||
    		plateau.getCase(new Vector2(position.x - 1, position.y + 1)) != null &&
    		plateau.getCase(new Vector2(position.x - 1, position.y + 1)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector2(position.x - 1, position.y + 1));
    	}
    	
    	if(plateau.getCase(new Vector2(position.x - 1, position.y)) == null ||
    		plateau.getCase(new Vector2(position.x - 1, position.y)) != null &&
    		plateau.getCase(new Vector2(position.x - 1, position.y)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector2(position.x - 1, position.y));
    	}
    	
    	return casesJouables;
    }
}
