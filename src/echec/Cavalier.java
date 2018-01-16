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
public class Cavalier extends Piece
{
    public Cavalier(Vector2 position, int couleur)
    {
        super(position, couleur);
    }
    
    @Override
    public List<Vector2> getCasesJouables(Plateau plateau)
    {
    	List<Vector2> casesJouables = new ArrayList<>();
    	int autreCouleur = (couleur + 1) % 2;
    	
    	if(plateau.getCase(new Vector(position.x - 2, position.y - 1)) == null ||
    		plateau.getCase(new Vector(position.x - 2, position.y - 1)) != null &&
    		plateau.getCase(new Vector(position.x - 2, position.y - 1)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector(position.x - 2, position.y - 1));
    	}
    	
    	if(plateau.getCase(new Vector(position.x - 1, position.y - 2)) == null ||
    		plateau.getCase(new Vector(position.x - 1, position.y - 2)) != null &&
    		plateau.getCase(new Vector(position.x - 1, position.y - 2)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector(position.x - 1, position.y - 2));
    	}
    	
    	if(plateau.getCase(new Vector(position.x + 1, position.y - 2)) == null ||
    		plateau.getCase(new Vector(position.x + 1, position.y - 2)) != null &&
    		plateau.getCase(new Vector(position.x + 1, position.y - 2)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector(position.x + 1, position.y - 2));
    	}
    	
    	if(plateau.getCase(new Vector(position.x + 2, position.y - 1)) == null ||
    		plateau.getCase(new Vector(position.x + 2, position.y - 1)) != null &&
    		plateau.getCase(new Vector(position.x + 2, position.y - 1)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector(position.x + 2, position.y - 1));
    	}
    	
    	if(plateau.getCase(new Vector(position.x + 2, position.y + 1)) == null ||
    		plateau.getCase(new Vector(position.x + 2, position.y + 1)) != null &&
    		plateau.getCase(new Vector(position.x + 2, position.y + 1)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector(position.x + 2, position.y + 1));
    	}
    	
    	if(plateau.getCase(new Vector(position.x + 1, position.y + 2)) == null ||
    		plateau.getCase(new Vector(position.x + 1, position.y + 2)) != null &&
    		plateau.getCase(new Vector(position.x + 1, position.y + 2)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector(position.x + 1, position.y + 2));
    	}
    	
    	if(plateau.getCase(new Vector(position.x - 1, position.y + 2)) == null ||
    		plateau.getCase(new Vector(position.x - 1, position.y + 2)) != null &&
    		plateau.getCase(new Vector(position.x - 1, position.y + 2)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector(position.x - 1, position.y  + 2));
    	}
    	
    	if(plateau.getCase(new Vector(position.x - 2, position.y + 1)) == null ||
    		plateau.getCase(new Vector(position.x - 2, position.y + 1)) != null &&
    		plateau.getCase(new Vector(position.x - 2, position.y + 1)).couleur == autreCouleur)
    	{
    		casesJouables.add(new Vector(position.x - 2, position.y + 1));
    	}

    	return casesJouables;
    }
}
