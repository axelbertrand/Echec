/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package echec;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        this.joueurActuel = Couleur.BLANC;
        this.plateau = new Plateau();
        this.ia = null;
    }
    
    public Partie(String nomFichier) throws Exception
    {
        System.out.println("nomFichier = " + nomFichier);
        this.joueurActuel = Couleur.BLANC;
        this.plateau = new Plateau(chargerConfiguration(nomFichier));
        this.ia = null;
    }
    
    public Partie(IAAbstraite ia)
    {
        this.joueurActuel = Couleur.BLANC;
        this.plateau = new Plateau();
        this.ia = ia;
    }
    
    public Partie(String nomFichier, IAAbstraite ia) throws Exception
    {
        System.out.println("nomFichier = " + nomFichier);
        this.joueurActuel = Couleur.BLANC;
        this.plateau = new Plateau(chargerConfiguration(nomFichier));
        this.ia = ia;
    }
    
    public Plateau getPlateau()
    {
        return this.plateau;
    }
    
    public Couleur getJoueurActuel()
    {
        return this.joueurActuel;
    }
    
    public IAAbstraite getIA()
    {
        return this.ia;
    }
    
    public boolean jouerTour(Vector2 iniPos, Vector2 nouvPos)
    {
        Piece piece = plateau.getCase(iniPos);

        // La case iniPos est une pièce du joueur
        if(plateau.getPieces(joueurActuel).contains(piece))
        {
            // La pièce jouée par le joueur peut être déplacée à la case nouvPos
            if(piece.getCasesJouables(plateau).contains(nouvPos))
            {
                Couleur j2 = Couleur.getCouleurOpposee(joueurActuel);

                if(plateau.bougerPiece(iniPos, nouvPos))
                    return true;
                
                joueurActuel = j2;
            }
        }
        
        return false;
    }
    
    private List<Piece> chargerConfiguration(String nomFichier) throws FileNotFoundException, Exception
    {
        BufferedReader file = new BufferedReader(new FileReader(nomFichier));
        List<Piece> pieces = new ArrayList<>();

        String ligne;
        Couleur couleur = null;
        while ((ligne = file.readLine()) != null)
        {
            if (ligne.isEmpty())
                break;
            
            String[] inputs = ligne.split(" ");
            String pieceStr = null;
            for(String input : inputs)
            {
                if(input.equalsIgnoreCase("white"))
                {
                    couleur = Couleur.BLANC;
                }
                else if (input.equalsIgnoreCase("black"))
                {
                    couleur = Couleur.NOIR;
                }
                else
                {
                    if(couleur == null)
                    {
                        throw new Exception("Error file");
                    }

                    if(pieceStr == null)
                    {
                        pieceStr = input;
                    }
                    else
                    {
                        Piece p = creerPiece(pieceStr, input, couleur);
                        if(p != null)
                        {
                            pieces.add(p);
                        }
                        pieceStr = null;
                    }   
                }
            }
        }
        
        for(Piece p : pieces)
        {
            System.out.println("piece = " + p.getImageStr() + ", pos = " + p.getPosition());
        }
        
        return pieces;
    }
    
    private Piece creerPiece(String pieceStr, String posStr, Couleur couleur) throws Exception
    {
        Piece p;
        Vector2 v;
        
        switch (posStr.length()) {
            case 1 :
                if(!posStr.equals("0"))
                    throw new Exception("La valeur " + posStr + " pour la position est invalide");
                
                return null;
            case 2 :
                int posX = Integer.parseInt(posStr.substring(1, 2)) - 1;
                int posY = ((int) posStr.charAt(0)) % 'a';
                if(posX < 0 || posX > 7 || posY < 0 || posY > 7)
                    throw new Exception("La position " + posStr + " est en dehors des limites");
                v = new Vector2(posX, posY);
                break;
            default :
                throw new Exception("Trop de caractères pour la position");
        }
        
        if(pieceStr.length() > 1)
        {
            if (pieceStr.matches("k."))
            {
                pieceStr = "c";
            }
            else
            {
                pieceStr = pieceStr.substring(0,1);
            }
        }
        
        switch(pieceStr)
        {
            case "p" :
                p = new Pion(v, couleur);
                break;
            case "t" :
                p = new Tour(v, couleur);
                break;
            case "c" :
                p = new Cavalier(v, couleur);
                break;
            case "b" :
                p = new Fou(v, couleur);
                break;
            case "k" :
                p = new Roi(v, couleur);
                break;
            case "q" :
                p = new Dame(v, couleur);
                break;
            default :
                throw new Exception("Type de pièce invalide");
        }
        
        return p;
    }
    
    private Couleur joueurActuel;
    private Plateau plateau;
    private IAAbstraite ia;
}
