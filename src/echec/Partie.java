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
                new Pion(new Vector2(6, 0), Couleur.BLANC),
                new Pion(new Vector2(6, 1), Couleur.BLANC),
                new Pion(new Vector2(6, 2), Couleur.BLANC),
                new Pion(new Vector2(6, 3), Couleur.BLANC),
                new Pion(new Vector2(6, 4), Couleur.BLANC),
                new Pion(new Vector2(6, 5), Couleur.BLANC),
                new Pion(new Vector2(6, 6), Couleur.BLANC),
                new Pion(new Vector2(6, 7), Couleur.BLANC),

                new Tour(new Vector2(7, 0), Couleur.BLANC),
                new Tour(new Vector2(7, 7), Couleur.BLANC),

                new Cavalier(new Vector2(7, 1), Couleur.BLANC),
                new Cavalier(new Vector2(7, 6), Couleur.BLANC),
                
                new Fou(new Vector2(7, 2), Couleur.BLANC),
                new Fou(new Vector2(7, 5), Couleur.BLANC),

                new Dame(new Vector2(7, 3), Couleur.BLANC),

                new Roi(new Vector2(7, 4), Couleur.BLANC)
            }),
            new Joueur("Joueur 1", new Piece[] {
                new Pion(new Vector2(1, 0), Couleur.NOIR),
                new Pion(new Vector2(1, 1), Couleur.NOIR),
                new Pion(new Vector2(1, 2), Couleur.NOIR),
                new Pion(new Vector2(1, 3), Couleur.NOIR),
                new Pion(new Vector2(1, 4), Couleur.NOIR),
                new Pion(new Vector2(1, 5), Couleur.NOIR),
                new Pion(new Vector2(1, 6), Couleur.NOIR),
                new Pion(new Vector2(1, 7), Couleur.NOIR),

                new Tour(new Vector2(0, 0), Couleur.NOIR),
                new Tour(new Vector2(0, 7), Couleur.NOIR),

                new Cavalier(new Vector2(0, 1), Couleur.NOIR),
                new Cavalier(new Vector2(0, 6), Couleur.NOIR),
                
                new Fou(new Vector2(0, 2), Couleur.NOIR),
                new Fou(new Vector2(0, 5), Couleur.NOIR),

                new Dame(new Vector2(0, 3), Couleur.NOIR),

                new Roi(new Vector2(0, 4), Couleur.NOIR)
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
