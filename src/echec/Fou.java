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
    public Fou(Vector2 position, int couleur)
    {
        super(position, couleur);
    }
    
    @Override
    public List<Vector2> getCasesJouables(Plateau plateau)
    {
    	List<Vector2> casesJouables = new ArrayList<>();
    	int autreCouleur = (couleur + 1) % 2;
    	
    	for(int i = position.x + 1, j = position.y + 1; i < 7 || j < 7; i++, j++)
    	{
    		if(plateau.getCase(new Vector2(i, j)) == null)
    		{
    			casesJouables.add(new Vector2(i, j));
    		}
    		else if(plateau.getCase(new Vector2(i, j)) != null &&
    			plateau.getCase(new Vector2(i, j)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector2(i, j));
    			break;
    		}
    	}
    	
    	for(int i = position.x - 1, j = position.y - 1; i > 0 || j > 0; i--, j--)
    	{
    		if(plateau.getCase(new Vector2(i, j)) == null)
    		{
    			casesJouables.add(new Vector2(i, j));
    		}
    		else if(plateau.getCase(new Vector2(i, j)) != null &&
    			plateau.getCase(new Vector2(i, j)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector2(i, j));
    			break;
    		}
    	}
    	
    	for(int i = position.x + 1, j = position.y - 1; i < 7 || i > 0; i++, j--)
    	{
    		if(plateau.getCase(new Vector2(i, j)) == null)
    		{
    			casesJouables.add(new Vector2(i, j));
    		}
    		else if(plateau.getCase(new Vector2(i, j)) != null &&
    			plateau.getCase(new Vector2(i, j)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector2(i, j));
    			break;
    		}
    	}
    	
    	for(int i = position.x - 1, j = position.y + 1; i > 0 || i < 7; i--, j++)
    	{
    		if(plateau.getCase(new Vector2(i, j)) == null)
    		{
    			casesJouables.add(new Vector2(i, j));
    		}
    		else if(plateau.getCase(new Vector2(i, j)) != null &&
    			plateau.getCase(new Vector2(i, j)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector2(i, j));
    			break;
    		}
    	}
    	
    	return casesJouables;
    }
}
