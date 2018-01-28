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
            new Joueur("Joueur 1", this.plateau.getPieces(Couleur.BLANC)),
            new Joueur("Joueur 2", this.plateau.getPieces(Couleur.NOIR))
        };
    }
    
    public Plateau getPlateau()
    {
        return this.plateau;
    }
    
    public Joueur getJoueurActuel()
    {
        return this.joueurs[this.joueurActuel];
    }
    
    public boolean jouerTour(Vector2 iniPos, Vector2 nouvPos)
    {
        Piece piece = plateau.getCase(iniPos);

        // La case iniPos est une pièce du joueur
        if(getJoueurActuel().getPieces().contains(piece))
        {
            // La pièce jouée par le joueur peut être déplacée à la case nouvPos
            if(piece.getCasesJouables(plateau).contains(nouvPos))
            {
                if(joueurs[joueurActuel].jouer(iniPos, nouvPos, this.plateau, joueurs[joueurActuel ^ 1].getPieces()))
                    return true;
                
                joueurActuel ^= 1;
            }
        }
        
        return false;
    }
    
    private int joueurActuel;
    private Plateau plateau;
    private Joueur[] joueurs;
}
