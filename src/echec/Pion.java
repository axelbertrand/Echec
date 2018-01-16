/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package echec;


public class Pion extends Piece
{
    public Pion(Vector2 position, int couleur)
    {
        super(position, couleur);
    }
    
    @Override
    public List<Vector2> getCasesJouables(Plateau plateau)
    {
        List<Vector2> casesJouables = new ArrayList<>();
        
    	if(couleur == Joueur.BLANC)
    	{
    		if(plateau.getCase(new Vector2(position.x, position.y - 1)) == null)
        	{
    			caseJouables.add(new Vector2(position.x, position.y - 1));
        	}
        	
        	if(position.y == 6 && plateau.getCase(new Vector2(position.x, position.y - 2)) == null)
        	{
        		caseJouables.add(new Vector2(position.x, position.y - 2));
        	}
        	
        	if(plateau.getCase(new Vector2(position.x - 1, position.y - 1)) != null &&
        		plateau.getCase(new Vector2(position.x - 1, position.y - 1)).couleur == 1)
        	{
        		caseJouables.add(new Vector2(position.x - 1, position.y - 1));
        	}
        	
        	if(plateau.getCase(new Vector2(position.x + 1, position.y - 1)) != null &&
        		plateau.getCase(new Vector2(position.x + 1, position.y - 1)).couleur == 1)
        	{
        		caseJouables.add(new Vector2(position.x + 1, position.y - 1));
        	}
    	}
    	else
    	{
    		if(plateau.getCase(new Vector2(position.x, position.y + 1)) == null)
        	{
    			caseJouables.add(new Vector2(position.x, position.y + 1));
        	}
        	
        	if(position.y == 1 && plateau.getCase(new Vector2(position.x, position.y + 2)) == null)
        	{
        		caseJouables.add(new Vector2(position.x, position.y + 2));
        	}
        	
        	if(plateau.getCase(new Vector2(position.x - 1, position.y + 1)) != null &&
        		plateau.getCase(new Vector2(position.x - 1, position.y + 1)).couleur == 0)
        	{
        		caseJouables.add(new Vector2(position.x - 1, position.y + 1));
        	}
        	
        	if(plateau.getCase(new Vector2(position.x + 1, position.y + 1)) != null &&
        		plateau.getCase(new Vector2(position.x + 1, position.y + 1)).couleur == 0)
        	{
        		caseJouables.add(new Vector2(position.x + 1, position.y + 1));
        	}
    	}
    	
    	return caseJouables;
    }
}
