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
        this.joueurs = new Joueur[] {
            new Joueur("Joueur 1", new Piece[] {
                new Pion(new Vector2(0, 6), 0),
                new Pion(new Vector2(1, 6), 0),
                new Pion(new Vector2(2, 6), 0),
                new Pion(new Vector2(3, 6), 0),
                new Pion(new Vector2(4, 6), 0),
                new Pion(new Vector2(5, 6), 0),
                new Pion(new Vector2(6, 6), 0),
                new Pion(new Vector2(7, 6), 0),

                new Tour(new Vector2(0, 7), 0),
                new Tour(new Vector2(7, 7), 0),

                new Cavalier(new Vector2(1, 7), 0),
                new Cavalier(new Vector2(6, 7), 0),
                
                new Fou(new Vector2(2, 7), 0),
                new Fou(new Vector2(5, 7), 0),

                new Dame(new Vector2(3, 7), 0),

                new Roi(new Vector2(4, 7), 0)
            }),
            new Joueur("Joueur 1", new Piece[] {
                new Pion(new Vector2(0, 1), 1),
                new Pion(new Vector2(1, 1), 1),
                new Pion(new Vector2(2, 1), 1),
                new Pion(new Vector2(3, 1), 1),
                new Pion(new Vector2(4, 1), 1),
                new Pion(new Vector2(5, 1), 1),
                new Pion(new Vector2(6, 1), 1),
                new Pion(new Vector2(7, 1), 1),

                new Tour(new Vector2(0, 0), 1),
                new Tour(new Vector2(7, 0), 1),

                new Cavalier(new Vector2(1, 0), 1),
                new Cavalier(new Vector2(6, 0), 1),
                
                new Fou(new Vector2(2, 0), 1),
                new Fou(new Vector2(5, 0), 1),

                new Dame(new Vector2(3, 0), 1),

                new Roi(new Vector2(4, 0), 1)
            })
        };
        
        List<Piece> piecesPlateau = new ArrayList<>(joueurs[0].getPieces());
        piecesPlateau.addAll(joueurs[1].getPieces());
        this.plateau = new Plateau(piecesPlateau);
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
