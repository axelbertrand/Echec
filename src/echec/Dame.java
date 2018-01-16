/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package echec;

/**
 *
 * @author Axel
 */
public class Dame extends Piece
{
    public Dame(Vector2 position, int couleur)
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
    		if(plateau.getCase(new Vector(i, position.y)) == null)
    		{
    			casesJouables.add(new Vector(i, position.y));
    		}
    		else if(plateau.getCase(new Vector(i, position.y)) != null &&
    			plateau.getCase(new Vector(i, position.y)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector(i, position.y));
    			break;
    		}
    	}
    	
    	for(int i = position.x - 1; i > 0; i--)
    	{
    		if(plateau.getCase(new Vector(i, position.y)) == null)
    		{
    			casesJouables.add(new Vector(i, position.y));
    		}
    		else if(plateau.getCase(new Vector(i, position.y)) != null &&
    			plateau.getCase(new Vector(i, position.y)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector(i, position.y));
    			break;
    		}
    	}
    	
    	for(int i = position.y + 1; i < 7; i++)
    	{
    		if(plateau.getCase(new Vector(position.x, i)) == null)
    		{
    			casesJouables.add(new Vector(position.x, i));
    		}
    		else if(plateau.getCase(new Vector(position.x, i)) != null &&
    			plateau.getCase(new Vector(position.x, i)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector(position.x, i));
    			break;
    		}
    	}
    	
    	for(int i = position.y - 1; i > 0; i--)
    	{
    		if(plateau.getCase(new Vector(position.x, i)) == null)
    		{
    			casesJouables.add(new Vector(position.x, i));
    		}
    		else if(plateau.getCase(new Vector(position.x, i)) != null &&
    			plateau.getCase(new Vector(position.x, i)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector(position.x, i));
    			break;
    		}
    	}
    	
    	for(int i = position.x + 1, j = position.y + 1; i < 7 || j < 7; i++, j++)
    	{
    		if(plateau.getCase(new Vector(i, j)) == null)
    		{
    			casesJouables.add(new Vector(i, j));
    		}
    		else if(plateau.getCase(new Vector(i, j)) != null &&
    			plateau.getCase(new Vector(i, j)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector(i, j));
    			break;
    		}
    	}
    	
    	for(int i = position.x - 1, j = position.y - 1; i > 0 || j > 0; i--, j--)
    	{
    		if(plateau.getCase(new Vector(i, j)) == null)
    		{
    			casesJouables.add(new Vector(i, j));
    		}
    		else if(plateau.getCase(new Vector(i, j)) != null &&
    			plateau.getCase(new Vector(i, j)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector(i, j));
    			break;
    		}
    	}
    	
    	for(int i = position.x + 1, j = position.y - 1; i < 7 || i > 0; i++, j--)
    	{
    		if(plateau.getCase(new Vector(i, j)) == null)
    		{
    			casesJouables.add(new Vector(i, j));
    		}
    		else if(plateau.getCase(new Vector(i, j)) != null &&
    			plateau.getCase(new Vector(i, j)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector(i, j));
    			break;
    		}
    	}
    	
    	for(int i = position.x - 1, j = position.y + 1; i > 0 || i < 7; i--, j++)
    	{
    		if(plateau.getCase(new Vector(i, j)) == null)
    		{
    			casesJouables.add(new Vector(i, j));
    		}
    		else if(plateau.getCase(new Vector(i, j)) != null &&
    			plateau.getCase(new Vector(i, j)).couleur == autreCouleur)
    		{
    			casesJouables.add(new Vector(i, j));
    			break;
    		}
    	}
    	
    	return casesJouables;
    }
}
