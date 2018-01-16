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
    public Tour(Vector2 position, int couleur)
    {
        super(position, couleur);
    }
    
    @Override
    public List<Vector2> getCasesJouables(Plateau plateau)
    {
    	List<Vector2> casesJouables = new ArrayList<>();
    	int autreCouleur = (couleur + 1) % 2;
    	
    	for(int i = position.x + 1; i < 7; i++)
    	{
    		if(plateau.getCase(new Vector2(i, position.y)) == null)
    		{
    			casesJouables.add(new Vector2(i, position.y));
    		}
    		else if(plateau.getCase(new Vector2(i, position.y)) != null &&
    			plateau.getCase(new Vector2(i, position.y)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector2(i, position.y));
    			break;
    		}
    	}
    	
    	for(int i = position.x - 1; i > 0; i--)
    	{
    		if(plateau.getCase(new Vector2(i, position.y)) == null)
    		{
    			casesJouables.add(new Vector2(i, position.y));
    		}
    		else if(plateau.getCase(new Vector2(i, position.y)) != null &&
    			plateau.getCase(new Vector2(i, position.y)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector2(i, position.y));
    			break;
    		}
    	}
    	
    	for(int i = position.y + 1; i < 7; i++)
    	{
    		if(plateau.getCase(new Vector2(position.x, i)) == null)
    		{
    			casesJouables.add(new Vector2(position.x, i));
    		}
    		else if(plateau.getCase(new Vector2(position.x, i)) != null &&
    			plateau.getCase(new Vector2(position.x, i)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector2(position.x, i));
    			break;
    		}
    	}
    	
    	for(int i = position.y - 1; i > 0; i--)
    	{
    		if(plateau.getCase(new Vector2(position.x, i)) == null)
    		{
    			casesJouables.add(new Vector2(position.x, i));
    		}
    		else if(plateau.getCase(new Vector2(position.x, i)) != null &&
    			plateau.getCase(new Vector2(position.x, i)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector2(position.x, i));
    			break;
    		}
    	}
    	
    	return casesJouables;
    }
}
