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
public class Partie
{
    public Plateau getEchiquier()
    {
        return this.echiquier;
    }
    
    public Joueur[] getJoueurs()
    {
        return this.joueurs;
    }
    
    public void jouerTour(Vector2 iniPos, Vector2 nouvPos)
    {
        
    }
    
    private int joueurActuel;
    private Plateau echiquier;
    private Joueur[] joueurs = new Joueur[2];
}
