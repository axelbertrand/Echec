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
public class Partie
{
    public Partie()
    {
        this.joueurActuel = 0;
        this.plateau = new Plateau();
        this.joueurs = new Joueur[] {
            new Joueur("Joueur 1", this.plateau.getPieces(Couleur.BLANC)),
            new Joueur("Joueur 1", this.plateau.getPieces(Couleur.NOIR))
        };
    }
    
    public Plateau getPlateau()
    {
        return this.plateau;
    }
    
    public Joueur[] getJoueurs()
    {
        return this.joueurs;
    }
    
    public void jouerTour(Vector2 iniPos, Vector2 nouvPos)
    {
        joueurs[joueurActuel].jouer(iniPos, nouvPos, this.plateau, joueurs[(joueurActuel + 1) % 2]);
        joueurActuel = (joueurActuel + 1) % 2;
    }
    
    private int joueurActuel;
    private Plateau plateau;
    private Joueur[] joueurs;
}
