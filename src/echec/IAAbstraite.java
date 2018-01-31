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
public abstract class IAAbstraite
{
    public IAAbstraite(Couleur couleur)
    {
        this.couleur = couleur;
    }
    
    public Couleur getCouleur()
    {
        return this.couleur;
    }
    
    public abstract Vector2[] getCoupIA(Plateau plateau);
    
    protected Couleur couleur;
}
