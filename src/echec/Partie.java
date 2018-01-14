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
    public Partie()
    {
        this.joueurActuel = 0;
        this.plateau = new Plateau();
        this.joueurs = new Joueur[] {
            new Joueur("Joueur 1", new Piece[] {
                new Pion(new Vector2(0, 6)),
                new Pion(new Vector2(1, 6)),
                new Pion(new Vector2(2, 6)),
                new Pion(new Vector2(3, 6)),
                new Pion(new Vector2(4, 6)),
                new Pion(new Vector2(5, 6)),
                new Pion(new Vector2(6, 6)),
                new Pion(new Vector2(7, 6)),

                new Tour(new Vector2(0, 7)),
                new Tour(new Vector2(7, 7)),

                new Cavalier(new Vector2(1, 7)),
                new Cavalier(new Vector2(6, 7)),
                new Fou(new Vector2(2, 7)),

                new Fou(new Vector2(5, 7)),

                new Dame(new Vector2(3, 7)),

                new Roi(new Vector2(4, 7))
            }),
            new Joueur("Joueur 1", new Piece[] {
                new Pion(new Vector2(0, 1)),
                new Pion(new Vector2(1, 1)),
                new Pion(new Vector2(2, 1)),
                new Pion(new Vector2(3, 1)),
                new Pion(new Vector2(4, 1)),
                new Pion(new Vector2(5, 1)),
                new Pion(new Vector2(6, 1)),
                new Pion(new Vector2(7, 1)),

                new Tour(new Vector2(0, 0)),
                new Tour(new Vector2(7, 0)),

                new Cavalier(new Vector2(1, 0)),
                new Cavalier(new Vector2(6, 0)),
                new Fou(new Vector2(2, 0)),

                new Fou(new Vector2(5, 0)),

                new Dame(new Vector2(3, 0)),

                new Roi(new Vector2(4, 0))
            })
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
        
    }
    
    private int joueurActuel;
    private Plateau plateau;
    private Joueur[] joueurs;
}
