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
public class Plateau
{   
    public Plateau()
    {
        this.grille = new Piece[8][8];

        this.grille[7][0] = new Tour(new Vector2(7, 0), Couleur.BLANC);
        this.grille[7][1] = new Cavalier(new Vector2(7, 1), Couleur.BLANC);
        this.grille[7][2] = new Fou(new Vector2(7, 2), Couleur.BLANC);
        this.grille[7][3] = new Dame(new Vector2(7, 3), Couleur.BLANC);
        this.grille[7][4] = new Roi(new Vector2(7, 4), Couleur.BLANC);
        this.grille[7][5] = new Fou(new Vector2(7, 5), Couleur.BLANC);
        this.grille[7][6] = new Cavalier(new Vector2(7, 6), Couleur.BLANC);
        this.grille[7][7] = new Tour(new Vector2(7, 7), Couleur.BLANC);
        for(int i = 0; i < 8; i++)
        {
            this.grille[6][i] = new Pion(new Vector2(6, i), Couleur.BLANC);
        }
        
        this.grille[0][0] = new Tour(new Vector2(0, 0), Couleur.NOIR);
        this.grille[0][1] = new Cavalier(new Vector2(0, 1), Couleur.NOIR);
        this.grille[0][2] = new Fou(new Vector2(0, 2), Couleur.NOIR);
        this.grille[0][3] = new Dame(new Vector2(0, 3), Couleur.NOIR);
        this.grille[0][4] = new Roi(new Vector2(0, 4), Couleur.NOIR);
        this.grille[0][5] = new Fou(new Vector2(0, 5), Couleur.NOIR);
        this.grille[0][6] = new Cavalier(new Vector2(0, 6), Couleur.NOIR);
        this.grille[0][7] = new Tour(new Vector2(0, 7), Couleur.NOIR);
        for(int i = 0; i < 8; i++)
        {
            this.grille[1][i] = new Pion(new Vector2(1, i), Couleur.NOIR);
        }
    }
    
    public Plateau(List<Piece> pieces)
    {
        this.grille = new Piece[8][8];

        for(Piece piece : pieces)
        {
            this.grille[piece.getPosition().x][piece.getPosition().y] = piece;
        }
    }
    
    public boolean estCaseValide(Vector2 pos)
    {
        return (pos.x >= 0 && pos.x < 8 && pos.y >= 0 && pos.y < 8);
    }
    
    public Piece getCase(Vector2 pos)
    {
        return this.grille[pos.x][pos.y];
    }
    
    public void setCase(Vector2 pos, Piece piece)
    {
        this.grille[pos.x][pos.y] = piece;
    }
    
    public boolean estMenacee(Vector2 pos)
    {
        if(this.estCaseValide(pos))
        {
            if(this.getCase(pos) != null)
            {
                Couleur couleur = this.getCase(pos).getCouleur();

                for(int i = 0; i < 8; i++)
                {
                    for(int j = 0; j < 8; j++)
                    {
                        Piece p = this.getCase(new Vector2(i, j));
                        if(p != null)
                        {
                            if(!p.getCouleur().equals(couleur))
                            {
                                List<Vector2> casesMenacees = p.getCasesJouables(this);
                                if(casesMenacees.contains(pos))
                                {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    public boolean bougerPiece(Vector2 iniPos, Vector2 nouvPos, List<Piece> piecesJ2)
    {
        if(this.getCase(nouvPos) instanceof Roi)
        {
            return true;
        }
        
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                Piece piece = this.grille[i][j];
                if(piece instanceof Pion)
                {
                    ((Pion) piece).setDoublePas(false);
                }
            }
        }
        
        Piece p = this.getCase(iniPos);
        
        // Gestion coups spéciaux du roi
        if(p instanceof Roi && Math.abs(iniPos.y - nouvPos.y) == 2)
        {
            // Grand Roque
            if(iniPos.y - nouvPos.y > 0)
            {
                bougerPiece(Vector2.add(nouvPos, new Vector2(0, -2)), Vector2.add(nouvPos, new Vector2(0, 1)), piecesJ2);
            }
            // Petit Roque
            else
            {
                bougerPiece(Vector2.add(nouvPos, new Vector2(0, 1)), Vector2.add(nouvPos, new Vector2(0, -1)), piecesJ2);
            }
        }
        
        // Coups spéciaux du pion
        else if(p instanceof Pion)
        {
            // Double pas
            if(Math.abs(iniPos.x - nouvPos.x) == 2)
            {
                ((Pion) p).setDoublePas(true);
            }
            // Prise en passant
            else if(iniPos.x == 3 || iniPos.x == 4)
            {
                if(iniPos.y != nouvPos.y)
                {
                    if(this.getCase(nouvPos) == null)
                    {
                        Vector2 posPrise = new Vector2(iniPos.x, nouvPos.y);
                        piecesJ2.remove(this.getCase(posPrise));
                        this.setCase(posPrise, null);
                    }
                }
            }
        }
        
        this.setCase(iniPos, null);
        this.setCase(nouvPos, p);
        p.setPosition(nouvPos);
        
        return false;
    }
    
    public List<Piece> getPieces(Couleur couleur)
    {
        List<Piece> pieces = new ArrayList<>();
        
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                Piece p = this.grille[i][j];
                if(p != null && p.getCouleur().equals(couleur))
                {
                    pieces.add(p);
                }
            }
        }
        
        return pieces;
    }
    
    private Piece[][] grille;
}
